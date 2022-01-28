# GameruleManager

___
Plugin designed for [SemiVanilla-MC](https://github.com/SemiVanilla-MC/SemiVanilla-MC).
Manage and change gamerules by simply adding them to a config file.

## **Downloads**
Downloads can be obtained on the [github actions page.](https://github.com/SemiVanilla-MC/GameruleManager/actions)

## **Building**

#### Initial setup
Clone the repo using `git clone https://github.com/SemiVanilla-MC/GameruleManager.git`.

#### Compiling
Use the command `./gradlew build --stacktrace` in the project root directory.
The compiled jar will be placed in directory `/build/libs/`.

## **Commands**

| Command             | Description                 | Permission                  |
|---------------------|-----------------------------|-----------------------------|

## **Permissions**

| Permission                  | Description                                    |
|-----------------------------|------------------------------------------------|

## **Configuration**

```yaml
# Magic value used to determine auto configuration updates, do not change this value
config-version: 1
# Gamerules can be managed per world by adding a new node with that world's name to this node.
world-settings:
  default:
    gamerules:
      # Possible gamerule values (last updated for minecraft 1.18.1)
      # doFireTick(class java.lang.Boolean), maxCommandChainLength(class java.lang.Integer), fireDamage(class java.lang.Boolean), reducedDebugInfo(class java.lang.Boolean), disableElytraMovementCheck(class java.lang.Boolean), announceAdvancements(class java.lang.Boolean), drowningDamage(class java.lang.Boolean), commandBlockOutput(class java.lang.Boolean), forgiveDeadPlayers(class java.lang.Boolean), doMobSpawning(class java.lang.Boolean), maxEntityCramming(class java.lang.Integer), disableRaids(class java.lang.Boolean), doWeatherCycle(class java.lang.Boolean), doDaylightCycle(class java.lang.Boolean), showDeathMessages(class java.lang.Boolean), doTileDrops(class java.lang.Boolean), universalAnger(class java.lang.Boolean), playersSleepingPercentage(class java.lang.Integer), doInsomnia(class java.lang.Boolean), doImmediateRespawn(class java.lang.Boolean), naturalRegeneration(class java.lang.Boolean), doMobLoot(class java.lang.Boolean), fallDamage(class java.lang.Boolean), keepInventory(class java.lang.Boolean), doEntityDrops(class java.lang.Boolean), doLimitedCrafting(class java.lang.Boolean), mobGriefing(class java.lang.Boolean), randomTickSpeed(class java.lang.Integer), spawnRadius(class java.lang.Integer), freezeDamage(class java.lang.Boolean), doTraderSpawning(class java.lang.Boolean), logAdminCommands(class java.lang.Boolean), spectatorsGenerateChunks(class java.lang.Boolean), sendCommandFeedback(class java.lang.Boolean), doPatrolSpawning(class java.lang.Boolean)
      maxCommandChainLength: 100
      announceAdvancements: false
```

## **Support**

## **License**
[LICENSE](LICENSE)