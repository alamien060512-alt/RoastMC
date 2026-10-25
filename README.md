<div align="center">
  <h1 align="center">RoastMC 26.3</h1>
  <h3 align="center">Multiloader Minecraft server software: NeoForge + Paper/Purpur API, and Fabric + Spigot API</h3>

  <p align="center">
    <a href="https://github.com/alamien060512-alt/RoastMC/stargazers">
      <img alt="GitHub Stars" src="https://img.shields.io/github/stars/alamien060512-alt/RoastMC?logo=github">
    </a>
    <a href="https://neoforged.net/">
      <img alt="NeoForge" src="https://img.shields.io/badge/NeoForge-26.3.0.99-FF8B00">
    </a>
    <a href="https://www.azul.com/downloads/?version=java-25-lts#zulu">
      <img alt="JDK" src="https://img.shields.io/badge/JDK-25.0.4.1+1-007396?logo=java&logoColor=white">
    </a>
    <a href="https://docs.gradle.org/9.8.1/release-notes.html">
      <img alt="Gradle" src="https://img.shields.io/badge/Gradle-9.8.1-02303A?logo=gradle&logoColor=white">
    </a>
    <a href="https://discord.gg/mohistmc">
      <img alt="Discord" src="https://img.shields.io/discord/311256119005937665?color=5865F2&logo=discord&logoColor=white">
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

| Edition  | Command                | Output                               |
|----------|------------------------|--------------------------------------|
| NeoForge | `./gradlew roastmcJar` | `projects/roastmc/build/libs/*-server.jar` |
| Fabric   | `./gradlew fabricJar`  | `fabric/build/libs/*-server.jar`     |

The Fabric edition is a standalone Gradle build (Fabric Loom cannot share a build with the NeoForge toolchain) that is wired into the root build via `includeBuild`. It can also be built on its own with `cd fabric && ./gradlew jar`.

## 📊 Progress Status

✅ **Core Integration**
- [x] NeoForge ([3bb720446](https://github.com/neoforged/NeoForge/commit/3bb720446))
- [x] Paper ([a90108f43c](https://github.com/PaperMC/Paper/commit/a90108f43c))
- [x] Purpur ([5cd359666](https://github.com/PurpurMC/Purpur/commit/5cd359666))
- [x] Fabric edition ([`fabric/`](fabric)): Fabric Loader, Fabric API, Bukkit/CraftBukkit/Spigot

## 📚 Documentation

- [English Documentation](https://github.com/alamien060512-alt/RoastMC)
- [中文文档](https://github.com/alamien060512-alt/RoastMC)

## ⚙️ Technical Stack

| Component | Source                                              | Purpose        | Status |
|-----------|-----------------------------------------------------|----------------|--------|
| NeoForge  | [GitHub](https://github.com/neoforged/NeoForge.git) | Mod support    | ✅     |
| Paper     | [GitHub](https://github.com/PaperMC/Paper.git)      | Plugin support | ✅     |
| PurPur    | [GitHub](https://github.com/PurpurMC/Purpur.git)    | Plugin support | ✅     |
| Fabric    | [GitHub](https://github.com/FabricMC/fabric-api.git) | Mod support (Fabric edition) | ✅ |
| Spigot    | [Spigot](https://hub.spigotmc.org/stash/scm/spigot/spigot.git) | Plugin support (Fabric edition) | ✅ |

## 🙏 Credits

RoastMC builds on [Youer](https://github.com/MohistMC/Youer) and [Silkard](https://github.com/MohistMC/Silkard) by MohistMC, as well as NeoForge, Fabric, Paper, Purpur, Spigot and Arclight. See `LICENSE.md` and the `LICENCE/` directory for licensing.