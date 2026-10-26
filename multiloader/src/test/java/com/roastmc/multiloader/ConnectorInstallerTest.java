package com.roastmc.multiloader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HexFormat;
import java.util.Map;
import java.util.TreeMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/** Runs the installer against a fake Modrinth served from localhost. */
class ConnectorInstallerTest {

    private HttpServer server;
    private String base;
    private final Map<String, byte[]> routes = new TreeMap<>();

    @BeforeEach
    void start() throws Exception {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/", exchange -> {
            String key = exchange.getRequestURI().getRawPath()
                    + (exchange.getRequestURI().getRawQuery() != null ? "?" + exchange.getRequestURI().getRawQuery() : "");
            byte[] body = routes.get(key);
            if (body == null) {
                exchange.sendResponseHeaders(404, -1);
            } else {
                exchange.sendResponseHeaders(200, body.length);
                exchange.getResponseBody().write(body);
            }
            exchange.close();
        });
        server.start();
        base = "http://127.0.0.1:" + server.getAddress().getPort();
    }

    @AfterEach
    void stop() {
        server.stop(0);
    }

    private static String sha512(byte[] data) throws Exception {
        return HexFormat.of().formatHex(MessageDigest.getInstance("SHA-512").digest(data));
    }

    private void json(String path, String body) {
        routes.put(path, body.getBytes(StandardCharsets.UTF_8));
    }

    private String versionJson(String file, byte[] jar, String type, String deps) throws Exception {
        routes.put("/files/" + file, jar);
        return "{\"version_number\":\"1\",\"version_type\":\"" + type + "\",\"dependencies\":[" + deps + "],\"files\":[{\"primary\":true,\"filename\":\""
                + file + "\",\"url\":\"" + base + "/files/" + file + "\",\"hashes\":{\"sha512\":\"" + sha512(jar) + "\"}}]}";
    }

    private static final String Q = "?loaders=%5B%22neoforge%22%5D&game_versions=%5B%2226.2%22%5D";

    @Test
    void installsConnectorAndRequiredDependencies(@TempDir Path mods) throws Exception {
        json("/project/" + ConnectorInstaller.CONNECTOR_PROJECT + "/version" + Q, "["
                + versionJson("connector-beta.jar", "B".getBytes(), "beta", "")
                .replace("\"dependencies\":[]", "\"dependencies\":[{\"project_id\":\"FFAPI\",\"dependency_type\":\"required\"},{\"project_id\":\"OPT\",\"dependency_type\":\"optional\"}]")
                + "," + versionJson("connector.jar", "C".getBytes(), "release", "{\"project_id\":\"FFAPI\",\"dependency_type\":\"required\"},{\"project_id\":\"OPT\",\"dependency_type\":\"optional\"}")
                + "]");
        json("/project/FFAPI/version" + Q, "[" + versionJson("ffapi.jar", "F".getBytes(), "release", "{\"project_id\":\"LP\",\"dependency_type\":\"required\"}") + "]");
        json("/project/LP/version" + Q, "[" + versionJson("launchpad.jar", "L".getBytes(), "release", "") + "]");

        var result = new ConnectorInstaller(base, m -> { }).install("26.2", mods);

        assertTrue(result.installed(), result.message());
        assertEquals("C", Files.readString(mods.resolve("connector.jar")));
        assertTrue(Files.exists(mods.resolve("ffapi.jar")));
        assertTrue(Files.exists(mods.resolve("launchpad.jar")));
        assertFalse(Files.exists(mods.resolve("connector-beta.jar")), "prefers the release over a beta");
        assertEquals(3, Files.readAllLines(mods.resolve(ConnectorInstaller.MANIFEST)).size());
    }

    @Test
    void noBuildForThisVersionInstallsNothing(@TempDir Path mods) throws Exception {
        json("/project/" + ConnectorInstaller.CONNECTOR_PROJECT + "/version" + Q.replace("26.2", "26.3"), "[]");
        var result = new ConnectorInstaller(base, m -> { }).install("26.3", mods);
        assertFalse(result.installed());
        assertTrue(result.message().contains("26.3"));
        try (var list = Files.list(mods)) {
            assertEquals(0, list.filter(p -> p.toString().endsWith(".jar")).count());
        }
    }

    @Test
    void missingRequiredDependencyInstallsNothing(@TempDir Path mods) throws Exception {
        json("/project/" + ConnectorInstaller.CONNECTOR_PROJECT + "/version" + Q, "["
                + versionJson("connector.jar", "C".getBytes(), "release", "{\"project_id\":\"FFAPI\",\"dependency_type\":\"required\"}") + "]");
        json("/project/FFAPI/version" + Q, "[]");
        var result = new ConnectorInstaller(base, m -> { }).install("26.2", mods);
        assertFalse(result.installed());
        assertFalse(Files.exists(mods.resolve("connector.jar")), "no partial installs");
    }

    @Test
    void badChecksumIsRejected(@TempDir Path mods) throws Exception {
        String v = versionJson("connector.jar", "C".getBytes(), "release", "");
        routes.put("/files/connector.jar", "tampered".getBytes());
        json("/project/" + ConnectorInstaller.CONNECTOR_PROJECT + "/version" + Q, "[" + v + "]");
        var result = new ConnectorInstaller(base, m -> { }).install("26.2", mods);
        assertFalse(result.installed());
        assertFalse(Files.exists(mods.resolve("connector.jar")));
        assertFalse(Files.exists(mods.resolve("connector.jar.part")));
    }

    @Test
    void leavesUserManagedConnectorAlone(@TempDir Path mods) throws Exception {
        Files.writeString(mods.resolve("connector-3.0.0.jar"), "mine");
        var result = new ConnectorInstaller(base, m -> { }).install("26.2", mods);
        assertFalse(result.installed());
        assertEquals("mine", Files.readString(mods.resolve("connector-3.0.0.jar")));
    }

    @Test
    void updateRemovesOldManagedFiles(@TempDir Path mods) throws Exception {
        Files.writeString(mods.resolve("connector-old.jar"), "old");
        Files.writeString(mods.resolve(ConnectorInstaller.MANIFEST), "connector-old.jar\n");
        json("/project/" + ConnectorInstaller.CONNECTOR_PROJECT + "/version" + Q, "["
                + versionJson("connector-new.jar", "N".getBytes(), "release", "") + "]");
        var result = new ConnectorInstaller(base, m -> { }).install("26.2", mods);
        assertTrue(result.installed(), result.message());
        assertFalse(Files.exists(mods.resolve("connector-old.jar")));
        assertTrue(Files.exists(mods.resolve("connector-new.jar")));
    }

    @Test
    void uninstallRemovesOnlyManagedFiles(@TempDir Path mods) throws Exception {
        Files.writeString(mods.resolve("connector.jar"), "x");
        Files.writeString(mods.resolve("worldedit.jar"), "mine");
        Files.writeString(mods.resolve(ConnectorInstaller.MANIFEST), "connector.jar\n../evil.jar\n");

        var removed = ConnectorInstaller.uninstall(mods);

        assertEquals(java.util.List.of("connector.jar"), removed);
        assertFalse(Files.exists(mods.resolve("connector.jar")));
        assertFalse(Files.exists(mods.resolve(ConnectorInstaller.MANIFEST)));
        assertEquals("mine", Files.readString(mods.resolve("worldedit.jar")));
        assertTrue(ConnectorInstaller.uninstall(mods).isEmpty());
    }

    @Test
    void offlineKeepsPreviouslyInstalledFiles(@TempDir Path mods) throws Exception {
        Files.writeString(mods.resolve("connector.jar"), "x");
        Files.writeString(mods.resolve(ConnectorInstaller.MANIFEST), "connector.jar\n");
        server.stop(0);
        var result = new ConnectorInstaller(base, m -> { }).install("26.2", mods);
        assertTrue(result.installed(), result.message());
        assertTrue(Files.exists(mods.resolve("connector.jar")));
    }
}
