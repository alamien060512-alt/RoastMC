package com.roastmc.launcher.fabric;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import net.fabricmc.loader.impl.game.minecraft.MinecraftGameProvider;
import net.fabricmc.loader.impl.launch.FabricLauncher;
import net.fabricmc.loader.impl.util.Arguments;

public class RoastMCGameProvider extends MinecraftGameProvider {
    private Path extractedModFilePath;

    @Override
    public void initialize(FabricLauncher launcher) {
        System.setProperty("log4j2.configurationFile", "log4j2_roastmc.xml");
        try {
            this.extractedModFilePath = extractModFile();
            launcher.addToClassPath(extractedModFilePath);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        for (var libraryPath : System.getProperty("roastmc.classpath").split(File.pathSeparator)) {
            launcher.addToClassPath(Paths.get(libraryPath));
        }
        super.initialize(launcher);
    }

    @Override
    public Arguments getArguments() {
        Arguments arguments = super.getArguments();
        String existingModsArgument = arguments.get(Arguments.ADD_MODS);
        var builtinMods = System.getProperty("roastmc.builtinMods");
        var modsPath = this.extractedModFilePath.toString() + File.pathSeparator + builtinMods;
        if (existingModsArgument != null) {
            modsPath = existingModsArgument + File.pathSeparator + modsPath;
        }
        arguments.put(Arguments.ADD_MODS, modsPath);
        arguments.addExtraArg("nogui");
        return arguments;
    }

    @Override
    public void unlockClassPath(FabricLauncher launcher) {
        super.unlockClassPath(launcher);
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Path extractModFile() throws Exception {
        var version = RoastMCLauncher.getVersion();

        var resourcePath = "META-INF/libraries/com/roastmc/fabric/%s/roastmc-fabric-%s-server.jar".formatted(version, version);
        var inputStream = getClass().getModule().getResourceAsStream(resourcePath);

        if (inputStream == null) {
            inputStream = getClass().getClassLoader().getResourceAsStream(resourcePath);
        }

        if (inputStream == null) {
            throw new RuntimeException("Could not find RoastMC server jar file: " + resourcePath +
                    ". Please ensure the resource file is properly packaged in the JAR.");
        }

        var targetDirectory = Paths.get("libraries", "com", "mohistmc", "roastmc", version);
        if (!Files.exists(targetDirectory)) {
            Files.createDirectories(targetDirectory);
        }

        var extractedModFile = targetDirectory.resolve(version + ".jar");
        try (var files = Files.list(targetDirectory)) {
            for (Path oldFile : files.toList()) {
                Files.delete(oldFile);
            }
            Files.copy(inputStream, extractedModFile);
        }
        return extractedModFile;
    }

}
