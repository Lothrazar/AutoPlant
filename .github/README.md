# Auto Plant

A Minecraft mod built on the NeoForge API [https://neoforged.net/](https://neoforged.net/)

[![CurseForge](https://img.shields.io/badge/CurseForge-F16436?style=flat-square&logo=curseforge&logoColor=white)](https://www.curseforge.com/minecraft/mc-mods/auto-plant)
[![Modrinth](https://img.shields.io/badge/Modrinth-1bd96a?style=flat-square&logo=modrinth&logoColor=white)](https://modrinth.com/mod/auto-plant)


[![](http://cf.way2muchnoise.eu/444013.svg)](https://www.curseforge.com/minecraft/mc-mods/auto-plant) 
[![](http://cf.way2muchnoise.eu/versions/444013.svg)](https://www.curseforge.com/minecraft/mc-mods/auto-plant)


[![License: MIT](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)
[![links](https://img.shields.io/badge/more-links-ff69b4.svg)](https://allmylinks.com/lothrazar)
[![Support](https://img.shields.io/badge/Patreon-Support-orange.svg?logo=Patreon)](https://www.patreon.com/Lothrazar)



## About

**AutoPlant** automatically plants sapling items on the ground instead of letting them despawn, creating self-sustaining forests with no extra effort.

**What it does:**

When a sapling item entity is about to expire (using NeoForge's `ItemExpireEvent`), AutoPlant intercepts the despawn and attempts to place the sapling as a living tree sapling block at that exact location — provided the ground beneath it can support the block. If the placement succeeds, the item is consumed and the sapling takes root. If it can't survive there (e.g., no soil beneath it), it despawns normally.

**Why you'd want it:**

Chop a tree, and the leaves will slowly drop saplings as usual. Without this mod, those saplings eventually despawn and the forest shrinks. With AutoPlant, every dropped sapling that lands on valid ground plants itself automatically, letting a single felled tree eventually become many. This creates a genuinely immersive, self-regenerating forest ecosystem with zero player intervention.

**What it works on:**

- All items tagged `minecraft:saplings` (vanilla and modded saplings that use the standard tag)
- Additional custom blocks can be configured via the config file

**Configuration:**

- Toggle sapling auto-planting on or off
- Add custom block/item registry names to extend behavior beyond the `minecraft:saplings` tag

**Requires:** NeoForge (1.20.1+)

![recording of expiring saplings getting self planted](https://i.imgur.com/GVAI5J6.gif)
