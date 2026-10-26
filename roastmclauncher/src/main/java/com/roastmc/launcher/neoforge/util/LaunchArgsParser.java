package com.roastmc.launcher.neoforge.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import lombok.SneakyThrows;

/**
 * @author Mgazul
 * @date 2025/9/29 18:27
 */
public class LaunchArgsParser {

    private static final String ADD_OPENS_PREFIX = "--add-opens ";
    private static final String ADD_EXPORTS_PREFIX = "--add-exports ";
    private static final String CLASSPATH_ARG = "-classpath";
    /** Set by the unified launcher when Sinytra Connector is installed. */
    public static final String CONNECTOR_PROPERTY = "roastmc.connector";
    private static final String SYSTEM_PROPERTY_PREFIX = "-D";
    private static final String MODULE_SEPARATOR = "=";
    private static final String PACKAGE_SEPARATOR = "/";

    private static final MethodHandles.Lookup IMPL_LOOKUP;

    static {
        export(ModuleLayer.boot().findModule("java.base").orElseThrow(), "jdk.internal.module", LaunchArgsParser.class.getModule());
        IMPL_LOOKUP = MethodHandles.lookup();
    }

    @SneakyThrows
    private static void addExports(List<String> exports) {
        var modulesCl = IMPL_LOOKUP.findClass("jdk.internal.module.Modules");
        MethodHandle implAddExportsToAllUnnamedMH = IMPL_LOOKUP.findStatic(modulesCl, "addExportsToAllUnnamed", MethodType.methodType(void.class, Module.class, String.class));

        processModuleDirectives(exports, ModuleDirectiveType.EXPORT, implAddExportsToAllUnnamedMH);
    }

    @SneakyThrows
    private static void addOpens(List<String> opens) {
        var modulesCl = IMPL_LOOKUP.findClass("jdk.internal.module.Modules");
        MethodHandle implAddOpensToAllUnnamedMH = IMPL_LOOKUP.findStatic(modulesCl, "addOpensToAllUnnamed", MethodType.methodType(void.class, Module.class, String.class));

        processModuleDirectives(opens, ModuleDirectiveType.OPEN, implAddOpensToAllUnnamedMH);
    }

    private static ModuleDirectiveData parseModuleDirective(String directive) {
        String[] all = directive.split(MODULE_SEPARATOR, 2);
        if (all.length < 2) {
            return null;
        }

        String[] source = all[0].split(PACKAGE_SEPARATOR, 2);
        if (source.length < 2) {
            return null;
        }
        return new ModuleDirectiveData(source[0], source[1], all[1]);
    }

    private static void processModuleDirectives(List<String> directives, ModuleDirectiveType directiveType, MethodHandle implAddExtraToAllUnnamedMH) {
        directives.forEach(directive -> {
            ModuleDirectiveData data = parseModuleDirective(directive);
            if (data != null) {
                ModuleLayer.boot().findModule(data.module).ifPresent(m -> {
                    try {
                        if ("ALL-UNNAMED".equals(data.target)) {
                            implAddExtraToAllUnnamedMH.invokeWithArguments(m, data.packages);
                        } else {
                            ModuleLayer.boot().findModule(data.target).ifPresent(to -> {
                                if (directiveType == ModuleDirectiveType.OPEN) {
                                    open(m, data.packages, to);
                                } else if (directiveType == ModuleDirectiveType.EXPORT) {
                                    export(m, data.packages, to);
                                }
                            });
                        }
                    } catch (Throwable t) {
                        throw new RuntimeException(t);
                    }
                });
            }
        });
    }

    public enum ModuleDirectiveType {
        OPEN,
        EXPORT
    }

    private static void export(Module module, String pkg, Module to) {
        JarLoader.inst.redefineModule(module, Set.of(), Map.of(pkg, Set.of(to)), Map.of(), Set.of(), Map.of());
    }

    private static void open(Module module, String pkg, Module to) {
        JarLoader.inst.redefineModule(module, Set.of(), Map.of(), Map.of(pkg, Set.of(to)), Set.of(), Map.of());
    }

    @SneakyThrows
    public static void init(List<String> args) {
        List<String> opens = new ArrayList<>();
        List<String> exports = new ArrayList<>();

        String classpath = null;

        for (int i = 0; i < args.size(); i++) {
            String arg = args.get(i);
            if (arg.startsWith("-")) {
                if (arg.equals(CLASSPATH_ARG)) {
                    if (i + 1 < args.size()) {
                        classpath = args.get(i + 1);
                        i++;
                    }
                } else if (arg.startsWith(ADD_OPENS_PREFIX)) {
                    opens.add(arg.substring(ADD_OPENS_PREFIX.length()).trim());
                } else if (arg.startsWith(ADD_EXPORTS_PREFIX)) {
                    exports.add(arg.substring(ADD_EXPORTS_PREFIX.length()).trim());
                } else if (arg.startsWith(SYSTEM_PROPERTY_PREFIX)) {
                    var split = arg.substring(2).split("=", 2);
                    System.setProperty(split[0], split.length > 1 ? split[1] : "");
                }
            }
        }

        if (classpath != null) {
            if (Boolean.getBoolean(CONNECTOR_PROPERTY)) {
                classpath = withConnectorLibraries(classpath);
            }
            System.setProperty("java.class.path", classpath);
            loadModules(classpath);
        }

        addOpens(opens);
        addExports(exports);
    }

    /**
     * The neoforge jar bundles complete copies (including Paper's patched classes) of the authlib and
     * brigadier packages, so that patched and original classes live in one module. Sinytra Connector turns the
     * authlib and brigadier library jars into modules of their own, which then export the same packages as the
     * neoforge module; any module that reads both (such as those Connector generates for Fabric mods) fails
     * module resolution.
     *
     * With Connector in use, move those packages out of the neoforge jar into jars named like the original
     * libraries and put those on the classpath instead of the originals. Every package is then in exactly one
     * module, and Connector still finds the library jars it expects.
     */
    static String withConnectorLibraries(String classpath) throws IOException {
        List<String> entries = new ArrayList<>(Arrays.asList(classpath.split(File.pathSeparator)));
        // FML loads the neoforge jar from the libraries folder itself, it is not on the classpath
        Path universal = findUniversalJar(Paths.get("libraries", "net", "neoforged", "neoforge"));
        if (universal == null) {
            return classpath;
        }
        Path dir = Paths.get("libraries", "roastmc", "connector");
        Files.createDirectories(dir);

        // library path fragment -> package prefix inside the neoforge jar
        Map<String, String> split = new LinkedHashMap<>();
        split.put("/com/mojang/authlib/", "com/mojang/authlib/");
        split.put("/com/mojang/brigadier/", "com/mojang/brigadier/");

        List<Split> splits = new ArrayList<>();
        for (int i = 0; i < entries.size(); i++) {
            String normalized = entries.get(i).replace('\\', '/');
            for (Map.Entry<String, String> e : split.entrySet()) {
                if (normalized.contains(e.getKey())) {
                    Path original = Paths.get(entries.get(i));
                    Path patched = dir.resolve(original.getFileName().toString());
                    splits.add(new Split(e.getValue(), original, patched));
                    entries.set(i, patched.toString());
                }
            }
        }
        if (splits.isEmpty()) {
            return classpath;
        }
        mergeLibraries(universal, splits);
        return String.join(File.pathSeparator, entries);
    }

    private record Split(String prefix, Path original, Path patched) {
    }

    private static Path findUniversalJar(Path root) throws IOException {
        if (!Files.isDirectory(root)) {
            return null;
        }
        try (var files = Files.walk(root, 3)) {
            return files.filter(p -> p.getFileName().toString().endsWith("-universal.jar")).findFirst().orElse(null);
        }
    }

    /**
     * Writes, for each split, a jar with everything from the original library jar except the bundled package
     * (the original classes of that package are superseded), plus the bundled package from the neoforge jar, which
     * includes the patched classes. The bundled package is then removed from the neoforge jar.
     */
    private static void mergeLibraries(Path universal, List<Split> splits) throws IOException {
        for (Split split : splits) {
            try (ZipFile probe = new ZipFile(universal.toFile())) {
                // Already split on an earlier start and the neoforge jar was not extracted again: keep what we built
                if (Collections.list(probe.entries()).stream().noneMatch(e -> e.getName().startsWith(split.prefix()))
                        && Files.exists(split.patched())) {
                    continue;
                }
            }
            try (ZipFile original = new ZipFile(split.original().toFile());
                 ZipFile bundled = new ZipFile(universal.toFile());
                 ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(split.patched()))) {
                for (ZipEntry entry : Collections.list(original.entries())) {
                    if (!entry.getName().startsWith(split.prefix())) {
                        copyEntry(original, entry, out);
                    }
                }
                for (ZipEntry entry : Collections.list(bundled.entries())) {
                    if (entry.getName().startsWith(split.prefix())) {
                        copyEntry(bundled, entry, out);
                    }
                }
            }
        }

        Path stripped = universal.resolveSibling(universal.getFileName() + ".tmp");
        boolean removed = false;
        try (ZipFile in = new ZipFile(universal.toFile());
             ZipOutputStream out = new ZipOutputStream(Files.newOutputStream(stripped))) {
            for (ZipEntry entry : Collections.list(in.entries())) {
                if (splits.stream().anyMatch(s -> entry.getName().startsWith(s.prefix()))) {
                    removed = true;
                } else {
                    copyEntry(in, entry, out);
                }
            }
        }
        if (removed) {
            Files.move(stripped, universal, StandardCopyOption.REPLACE_EXISTING);
        } else {
            Files.deleteIfExists(stripped);
        }
    }

    private static void copyEntry(ZipFile from, ZipEntry entry, ZipOutputStream to) throws IOException {
        to.putNextEntry(new ZipEntry(entry.getName()));
        if (!entry.isDirectory()) {
            try (InputStream is = from.getInputStream(entry)) {
                is.transferTo(to);
            }
        }
        to.closeEntry();
    }

    public static void loadModules(String modulePath) {
        Arrays.stream(modulePath.split(File.pathSeparator))
                .map(Paths::get)
                .forEach(JarLoader::loadJar);
    }

    private record ModuleDirectiveData(String module, String packages, String target) {
    }
}
