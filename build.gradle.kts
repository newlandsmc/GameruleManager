plugins {
    id("java")
    id("net.minecrell.plugin-yml.bukkit") version "0.5.1"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "com.semivanilla.disableadvanceannounce"
version = "1.0.0-SNAPSHOT"
description = "A plugin that disables advancement messages in chat."

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.1-R0.1-SNAPSHOT") // Paper
}

tasks {

    runServer {
        minecraftVersion("1.18")
    }

}

bukkit {
    name = rootProject.name
    main = "$group.DisableAdvanceAnnounce"
    apiVersion = "1.18"
    website = "https://github.com/SemiVanilla-MC/DisableAdvanceAnnouncements"
    authors = listOf("destro174")
}