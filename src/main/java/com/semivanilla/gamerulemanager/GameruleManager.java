package com.semivanilla.gamerulemanager;

import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.stream.Collectors;

public class GameruleManager extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getScheduler().runTask(this, () -> {
            List<String> worlds = disableAdvanceAnnounce();
            if (!worlds.isEmpty()) getLogger().info(getInfo(worlds));
        });

        getServer().getPluginManager().registerEvents(this, this);
    }

    private String getInfo(List<String> worlds) {
        return "Disabled advancement announcing in " + String.join(", ", worlds) + " worlds";
    }

    @EventHandler
    public void onWorldLoad(WorldLoadEvent event) {
        disableAdvanceAnnounce();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        List<String> worlds = disableAdvanceAnnounce();
        sender.sendMessage(getInfo(worlds));
        return true;
    }

    private List<String> disableAdvanceAnnounce() {
        return Bukkit.getWorlds().stream()
                .filter(w -> w.getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS))
                .peek(w -> w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false))
                .map(World::getName)
                .collect(Collectors.toList());
    }
}
