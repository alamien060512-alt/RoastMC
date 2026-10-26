package com.roastmc.multiloader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.time.Duration;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HexFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Downloads Sinytra Connector and its required dependencies (Forgified Fabric API, Launchpad, ...)
 * from Modrinth into the mods folder, so Fabric mods can run on the NeoForge edition.
 *
 * Nothing is installed unless Modrinth has a NeoForge build of every required project for the
 * running Minecraft version; in that case the server just starts without Fabric support.
 */
public final class ConnectorInstaller {

    public static final String DEFAULT_API = "https://api.modrinth.com/v2";
    public static final String CONNECTOR_PROJECT = "u58R1TMW";
    static final String MANIFEST = ".roastmc-connector";
    private static final Pattern USER_MANAGED = Pattern.compile("(?i)^(sinytra-)?connector.*\\.jar$");

    public record Result(boolean installed, String message) {
    }

    record Artifact(String projectId, String versionNumber, String filename, String url, String sha512) {
    }

    private final String api;
    private final HttpClient http;
    private final Consumer<String> log;

    public ConnectorInstaller(String api, Consumer<String> log) {
        this.api = api.endsWith("/") ? api.substring(0, api.length() - 1) : api;
        this.log = log;
        this.http = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    public Result install(String minecraftVersion, Path modsDir) {
        try {
            Files.createDirectories(modsDir);
            Path manifest = modsDir.resolve(MANIFEST);
            List<String> previous = Files.exists(manifest) ? Files.readAllLines(manifest) : List.of();

            if (previous.isEmpty() && hasUserManagedConnector(modsDir)) {
                return new Result(false, "A Connector jar is already in mods/, leaving it as it is.");
            }

            List<Artifact> plan;
            try {
                plan = resolve(minecraftVersion);
            } catch (UnsupportedVersionException e) {
                return fallback(previous, modsDir, e.getMessage());
            } catch (IOException | InterruptedException e) {
                if (e instanceof InterruptedException) {
                    Thread.currentThread().interrupt();
                }
                return fallback(previous, modsDir, "Could not reach Modrinth (" + e.getMessage() + ").");
            }

            List<String> files = new ArrayList<>();
            for (Artifact artifact : plan) {
                Path target = modsDir.resolve(artifact.filename());
                if (!Files.exists(target)) {
                    log.accept("Downloading " + artifact.filename() + " ...");
                    download(artifact, target);
                }
                files.add(artifact.filename());
            }
            for (String old : previous) {
                if (!files.contains(old) && isPlainFilename(old)) {
                    Files.deleteIfExists(modsDir.resolve(old));
                }
            }
            Files.write(manifest, files);
            return new Result(true, "Sinytra Connector ready for Minecraft " + minecraftVersion + ": " + String.join(", ", files));
        } catch (IOException | InterruptedException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            return new Result(false, "Connector install failed (" + e.getMessage() + "); starting without Fabric mod support.");
        }
    }

    /**
     * Removes the Connector files this installer put in the mods folder (listed in the manifest), so a server
     * that switched back to plain NeoForge does not keep loading Connector. Files you added yourself are untouched.
     *
     * @return the removed file names
     */
    public static List<String> uninstall(Path modsDir) throws IOException {
        Path manifest = modsDir.resolve(MANIFEST);
        if (!Files.exists(manifest)) {
            return List.of();
        }
        List<String> removed = new ArrayList<>();
        for (String name : Files.readAllLines(manifest)) {
            if (isPlainFilename(name) && Files.deleteIfExists(modsDir.resolve(name))) {
                removed.add(name);
            }
        }
        Files.delete(manifest);
        return removed;
    }

    private Result fallback(List<String> previous, Path modsDir, String reason) {
        boolean present = !previous.isEmpty() && previous.stream().allMatch(f -> isPlainFilename(f) && Files.exists(modsDir.resolve(f)));
        if (present) {
            return new Result(true, reason + " Using the Connector files already in mods/.");
        }
        return new Result(false, reason + " Starting without Fabric mod support.");
    }

    /** Collects Connector plus everything it requires, or throws if any piece has no build for this version. */
    List<Artifact> resolve(String mc) throws IOException, InterruptedException {
        record Req(String projectId, String versionId) {
        }
        Map<String, Artifact> out = new LinkedHashMap<>();
        Deque<Req> queue = new ArrayDeque<>();
        queue.add(new Req(CONNECTOR_PROJECT, null));
        Set<String> seen = new java.util.HashSet<>();
        while (!queue.isEmpty()) {
            Req req = queue.poll();
            if (!seen.add(req.projectId())) {
                continue;
            }
            JsonObject version = req.versionId() != null
                    ? getJson("/version/" + req.versionId()).getAsJsonObject()
                    : pickVersion(req.projectId(), mc);
            if (version == null) {
                throw new UnsupportedVersionException("Sinytra Connector (or a required dependency, Modrinth project "
                        + req.projectId() + ") has no NeoForge build for Minecraft " + mc + " yet.");
            }
            out.put(req.projectId(), toArtifact(req.projectId(), version));
            for (JsonElement dep : version.getAsJsonArray("dependencies")) {
                JsonObject d = dep.getAsJsonObject();
                if ("required".equals(string(d, "dependency_type")) && d.has("project_id") && !d.get("project_id").isJsonNull()) {
                    String versionId = d.has("version_id") && !d.get("version_id").isJsonNull() ? d.get("version_id").getAsString() : null;
                    queue.add(new Req(d.get("project_id").getAsString(), versionId));
                }
            }
        }
        return new ArrayList<>(out.values());
    }

    /** Newest release, or the newest build of any type when there is no release. */
    private JsonObject pickVersion(String projectId, String mc) throws IOException, InterruptedException {
        String query = "?loaders=" + enc("[\"neoforge\"]") + "&game_versions=" + enc("[\"" + mc + "\"]");
        JsonArray versions = getJson("/project/" + projectId + "/version" + query).getAsJsonArray();
        JsonObject first = null;
        for (JsonElement element : versions) {
            JsonObject version = element.getAsJsonObject();
            if (first == null) {
                first = version;
            }
            if ("release".equals(string(version, "version_type"))) {
                return version;
            }
        }
        return first;
    }

    private static Artifact toArtifact(String projectId, JsonObject version) throws IOException {
        JsonArray files = version.getAsJsonArray("files");
        JsonObject chosen = null;
        for (JsonElement element : files) {
            JsonObject file = element.getAsJsonObject();
            if (chosen == null || (file.has("primary") && file.get("primary").getAsBoolean())) {
                chosen = file;
            }
        }
        if (chosen == null) {
            throw new IOException("Modrinth version " + string(version, "version_number") + " has no files");
        }
        String filename = string(chosen, "filename");
        if (!isPlainFilename(filename)) {
            throw new IOException("Refusing suspicious file name from Modrinth: " + filename);
        }
        return new Artifact(projectId, string(version, "version_number"), filename, string(chosen, "url"),
                string(chosen.getAsJsonObject("hashes"), "sha512"));
    }

    private void download(Artifact artifact, Path target) throws IOException, InterruptedException {
        Path part = target.resolveSibling(target.getFileName() + ".part");
        HttpRequest request = request(URI.create(artifact.url()));
        HttpResponse<InputStream> response = http.send(request, HttpResponse.BodyHandlers.ofInputStream());
        if (response.statusCode() != 200) {
            throw new IOException("HTTP " + response.statusCode() + " downloading " + artifact.url());
        }
        try (InputStream in = response.body()) {
            Files.copy(in, part, StandardCopyOption.REPLACE_EXISTING);
        }
        if (artifact.sha512() != null && !artifact.sha512().equalsIgnoreCase(sha512(part))) {
            Files.deleteIfExists(part);
            throw new IOException("Checksum mismatch for " + artifact.filename());
        }
        Files.move(part, target, StandardCopyOption.REPLACE_EXISTING);
    }

    private JsonElement getJson(String path) throws IOException, InterruptedException {
        HttpResponse<String> response = http.send(request(URI.create(api + path)), HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        if (response.statusCode() != 200) {
            throw new IOException("Modrinth returned HTTP " + response.statusCode() + " for " + path);
        }
        return JsonParser.parseString(response.body());
    }

    private static HttpRequest request(URI uri) {
        return HttpRequest.newBuilder(uri)
                .timeout(Duration.ofSeconds(60))
                .header("User-Agent", "alamien060512-alt/RoastMC (multiloader)")
                .build();
    }

    private static boolean hasUserManagedConnector(Path modsDir) throws IOException {
        try (Stream<Path> list = Files.list(modsDir)) {
            return list.anyMatch(p -> USER_MANAGED.matcher(p.getFileName().toString()).matches());
        }
    }

    private static boolean isPlainFilename(String name) {
        return name != null && !name.isBlank() && name.endsWith(".jar") && !name.contains("/") && !name.contains("\\") && !name.contains("..");
    }

    private static String sha512(Path file) throws IOException {
        try (InputStream in = Files.newInputStream(file)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            byte[] buffer = new byte[8192];
            for (int n; (n = in.read(buffer)) > 0; ) {
                digest.update(buffer, 0, n);
            }
            return HexFormat.of().formatHex(digest.digest());
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new IOException(e);
        }
    }

    private static String string(JsonObject object, String key) {
        return object != null && object.has(key) && !object.get(key).isJsonNull() ? object.get(key).getAsString() : null;
    }

    private static String enc(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }

    static final class UnsupportedVersionException extends IOException {
        UnsupportedVersionException(String message) {
            super(message);
        }
    }
}
