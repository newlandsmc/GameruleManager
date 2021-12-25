import com.github.jengelman.gradle.plugins.shadow.tasks.ConfigureShadowRelocation

plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "7.1.0"
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "com.semivanilla.gamerulemanager"
version = "1.0.0-SNAPSHOT"
description = "Manage gamerules on server or world level."

dependencies {

    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT") // Paper
    shadow("net.kyori:adventure-text-minimessage:4.2.0-SNAPSHOT") { // Minimessage
        exclude("net.kyori", "adventure-api")
    }

}

tasks {

    runServer {
        minecraftVersion("1.18.1")
    }

    shadowJar {
        dependsOn(getByName("relocateJars") as ConfigureShadowRelocation)
        archiveFileName.set("${project.name}-${project.version}.jar")
        minimize()
        configurations = listOf(project.configurations.shadow.get())
    }

    build {
        dependsOn(shadowJar)
    }

    create<ConfigureShadowRelocation>("relocateJars") {
        target = shadowJar.get()
        prefix = "${project.name}.lib"
    }

}

bukkit {
    name = rootProject.name
    main = "$group.GameruleManager"
    apiVersion = "1.18"
    website = "https://github.com/SemiVanilla-MC/GameruleManager"
    authors = listOf("destro174")
    load = net.minecrell.pluginyml.bukkit.BukkitPluginDescription.PluginLoadOrder.POSTWORLD
    commands {
        create("gamerulemanagerreload") {
            description = "Reload gamerule manager configuration"
            usage = "/gamerulemanagerreload"
            permission = "gamerulemanager.reload"
        }
    }
}