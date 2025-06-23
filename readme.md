# Botany Pots Fix 植物盆栽修复

Reduce lag caused by massive [Botany Pots](https://www.curseforge.com/minecraft/mc-mods/botany-pots) on both server & client.

修复/减轻了大量的[植物盆栽](https://www.curseforge.com/minecraft/mc-mods/botany-pots)对服务端与客户端的卡顿。

If installed on server, it can reduce server tick time.
If installed on client, it can reduce frame time by reducing client tick time.

如果服务端安装，则能减缓服务端 tick time；
如果客户端安装，则能通过减缓客户端 tick time 来减轻客户端画面卡顿（前提是电脑的其他硬件没有拖累）。

## How it works 工作机理

### What causes lag? 卡顿元凶

The class that manages the storage & recipe in use of a botany pot has an [updating method](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/inv/BotanyPotContainer.java#L67) that **scans all soil & seeds recipes** under specific conditions. That costs a lot.

储存植物盆栽中的物品及其正在使用的配方的类中，有一个[更新方法](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/inv/BotanyPotContainer.java#L67)会在一定条件下**遍历扫描所有的土壤配方与种子生长配方**，这对运算资源消耗极大。

Unluckily, this method is invoked [every tick](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/BlockEntityBotanyPot.java#L214) by every botany pot and triggers [the updating method of the block entity](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/BlockEntityBotanyPot.java#L312), making Bookshelf syncing the BE data. When client receives the data, it executes [a local update](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/BlockEntityBotanyPot.java#L319), and [similarly scans the recipes](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/inv/BotanyPotContainer.java#L84) again.

不幸的是，这个方法对于**每个盆栽**的[每个游戏刻](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/BlockEntityBotanyPot.java#L214)都会被调用，并且会触发[方块实体的更新方法](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/BlockEntityBotanyPot.java#L312)，让 Bookshelf 同步方块实体数据到客户端。客户端在接收到同步过来的数据后，会执行[本地的更新方法](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/BlockEntityBotanyPot.java#L319)并作出相似的[配方遍历逻辑](https://github.com/Darkhax-Minecraft/BotanyPots/blob/1.20.1/common/src/main/java/net/darkhax/botanypots/block/inv/BotanyPotContainer.java#L84)。

### How this fixes? 修复方案

This mod injects [a further detection](https://github.com/MUYUTwilighter/BotanyPotsFix/blob/master/common/src/main/java/cool/muyucloud/botanypotsfix/mixin/BotanyPotContainerMixin.javaL50) into the update of that handles storage: Only execute a complete update when the soil or seed slot has changed, or the recipe system is updated by a datapack reload.

本模组在存储类的更新方法中，引入了[更精确的检测](https://github.com/MUYUTwilighter/BotanyPotsFix/blob/master/common/src/main/java/cool/muyucloud/botanypotsfix/mixin/BotanyPotContainerMixin.javaL50)：仅当土壤或种子栏位发生了变化，或是数据包更新引发了配方更改，才执行完全的更新逻辑。

In this way, the unnecessary computing in a large scope is avoided with efforts even though the update is invoked every tick.

这样一来，哪怕每个游戏刻都会执行更新，也尽量不会引发更大范围的无意义运算。

# License 许可

Following MIT license, you can use this project for any purpose,
including distributing the file packed into your own modpack.

在遵循 MIT License 的情况下，本项目可用于任何用途，包括直接将文件打包到你的整合包中。