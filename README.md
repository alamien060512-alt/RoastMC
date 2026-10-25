<div align="center">
  <h1 align="center">RoastMC 26.2</h1>
  <h3 align="center">Multiloader Minecraft server software: NeoForge + Paper/Purpur API, and Fabric + Spigot API</h3>

  <p style="text-align: center;">
    <a href="https://github.com/alamien060512-alt/RoastMC/stargazers">
      <img alt="GitHub Stars" src="https://img.shields.io/github/stars/alamien060512-alt/RoastMC?logo=github&color=181717&style=flat-square">
    </a>
    <a href="https://neoforged.net/">
      <img alt="NeoForge" src="https://img.shields.io/badge/NeoForge-26.2.0.93-FF8B00?style=flat-square">
    </a>
    <a href="https://www.azul.com/downloads/?version=java-25-lts#zulu">
      <img alt="JDK" src="https://img.shields.io/badge/JDK-25.0.4-007396?logo=java&logoColor=white&style=flat-square">
    </a>
    <a href="https://docs.gradle.org/9.7.1/release-notes.html">
      <img alt="Gradle" src="https://img.shields.io/badge/Gradle-9.7.1-02303A?logo=gradle&logoColor=white&style=flat-square">
    </a>
    <a href="https://discord.gg/mohistmc">
      <img alt="Discord" src="https://img.shields.io/discord/311256119005937665?color=5865F2&logo=discord&logoColor=white&style=flat-square">
    </a>
  </p>
</div>

## 🚀 Features

- Multiloader: one project, one brand, a server jar per mod loader
- **NeoForge edition** (repository root): NeoForge mod support with Paper/Purpur API compatibility
- **Fabric edition** ([`fabric/`](fabric)): Fabric mod support with Spigot API compatibility
- Seamless plugin support from the Paper/Purpur (NeoForge) and Bukkit/Spigot (Fabric) ecosystems
- Optimized performance and stability for modded environments

## 🔨 Building

Requires JDK 25.

| Edition  | Command                | Output                                     |
|----------|------------------------|--------------------------------------------|
| NeoForge | `./gradlew roastmcJar` | `projects/roastmc/build/libs/*-server.jar` |
| Fabric   | `./gradlew fabricJar`  | `fabric/build/libs/*-server.jar`           |

The Fabric edition is a standalone Gradle build (Fabric Loom cannot share a build with the NeoForge toolchain) wired into the root build via `includeBuild`. It can also be built on its own with `cd fabric && ./gradlew jar`.

## 📊 Progress Status

✅ **Core Integration**
- [x] NeoForge ([4e2117162](https://github.com/neoforged/NeoForge/commit/4e2117162))
- [x] Paper ([91693c3ec1](https://github.com/PaperMC/Paper/commit/91693c3ec1))
- [x] PurPur ([3f5d9c0e8](https://github.com/PurpurMC/Purpur/commit/3f5d9c0e8))

🔄 **Compatible Progress**

## 📚 Documentation

- [English Documentation](https://github.com/alamien060512-alt/RoastMC)
- [中文文档](https://github.com/alamien060512-alt/RoastMC)

## ⚙️ Technical Stack

| Component   | Source                                                              | Purpose                      | Status |
|-------------|---------------------------------------------------------------------|------------------------------|--------|
| NeoForge    | [GitHub](https://github.com/neoforged/NeoForge.git)                 | Mod support                  | ✅      |
| Paper       | [GitHub](https://github.com/PaperMC/Paper.git)                      | Plugin support               | ✅      |
| PurPur      | [GitHub](https://github.com/PurpurMC/Purpur.git)                    | Plugin support               | ✅      |
| Fabric      | [GitHub](https://github.com/FabricMC/fabric-api.git)                | Mod support (Fabric edition) | ✅      |
| Spigot      | [Spigot](https://hub.spigotmc.org/stash/scm/spigot/spigot.git)      | Plugin support (Fabric edition) | ✅   |

## 🙏 Credits

RoastMC builds on [Youer](https://github.com/MohistMC/Youer) and [Silkard](https://github.com/MohistMC/Silkard) by MohistMC, as well as NeoForge, Fabric, Paper, Purpur, Spigot and Arclight. See `LICENSE.md` and the `LICENCE/` directory for licensing.
