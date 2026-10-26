package com.roastmc.launcher.fabric;

import com.roastmc.launcher.fabric.install.FabricInstaller;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class RoastMCLauncher {

    static void main(String[] args) {
        System.setProperty("fabric.skipMcProvider", "true");
        System.setProperty("org.jline.terminal.disableDeprecatedProviderWarning", String.valueOf(true));
        var logo = """
                  ____                 _   __  __  ____
                 |  _ \\ ___   __ _ ___| |_|  \\/  |/ ___|
                 | |_) / _ \\ / _` / __| __| |\\/| | |
                 |  _ < (_) | (_| \\__ \\ |_| |  | | |___
                 |_| \\_\\___/ \\__,_|___/\\__|_|  |_|\\____|
                """;
        System.out.printf("%n%s%n%s - %s, Java(%s) %s PID: %s%n",
                logo,
                "欢迎使用 RoastMC (Fabric)",
                getVersion(),
                System.getProperty("java.class.version"),
                System.getProperty("java.version"),
                ManagementFactory.getRuntimeMXBean().getName().split("@")[0]
        );
        System.out.print("");
        try {
            var install = fabricInstall();
            var ours = RoastMCLauncher.class.getProtectionDomain().getCodeSource().getLocation();
            var classloader = new URLClassLoader(Stream.concat(Stream.of(ours), install.getValue().stream().map(it -> {
                try {
                    return it.toUri().toURL();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
            })).toArray(URL[]::new), ClassLoader.getPlatformClassLoader());
            Thread.currentThread().setContextClassLoader(classloader);
            var cl = Class.forName(install.getKey(), false, classloader);
            var handle = MethodHandles.lookup().findStatic(cl, "main", MethodType.methodType(void.class, String[].class));
            handle.invoke((Object) args);
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println("Fail to launch RoastMC.");
        }
    }

    public static String getVersion() {
        return (RoastMCLauncher.class.getPackage().getImplementationVersion() != null) ? RoastMCLauncher.class.getPackage().getImplementationVersion() : "DEV";
    }

    private static Map.Entry<String, List<Path>> fabricInstall() throws Throwable {
        return FabricInstaller.applicationInstall();
    }
}
