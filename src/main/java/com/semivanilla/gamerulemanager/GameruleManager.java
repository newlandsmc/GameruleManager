package com.semivanilla.gamerulemanager;

import com.semivanilla.gamerulemanager.config.Config;
import com.semivanilla.gamerulemanager.config.WorldConfig;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class GameruleManager extends JavaPlugin implements Listener {

    private static JavaPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;
        reloadConfig();
//        Bukkit.getScheduler().runTask(this, () -> {
//            List<String> worlds = disableAdvanceAnnounce();
//            if (!worlds.isEmpty()) getLogger().info(getInfo(worlds));
//        });

//        getServer().getPluginManager().registerEvents(this, this);
        getCommand("gamerulemanagerreload").setExecutor(this::onCommand);
    }

    public void reloadConfig() {
        Config.init();
        setGameRules();
    }

    private String getInfo(List<String> worlds) {
        return "Disabled advancement announcing in " + String.join(", ", worlds) + " worlds";
    }

//    @EventHandler
//    public void onWorldLoad(WorldLoadEvent event) {
//        disableAdvanceAnnounce();
//    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("gamerulemanagerreload")) {
            reloadConfig();
            sender.sendMessage("Configuration reloaded.");
        }
//        List<String> worlds = disableAdvanceAnnounce();
//        sender.sendMessage(getInfo(worlds));
        return true;
    }

    private void setGameRules() {
        for (World world : Bukkit.getWorlds()) {
            if (world == null) continue;
            String worldName = world.getName();
            WorldConfig worldConfig = new WorldConfig(worldName);
            for (Map.Entry<String, Object> entry : worldConfig.customRules.entrySet()) {
                String rule = entry.getKey();
                Object value = entry.getValue();
                if (!world.isGameRule(rule)) continue;
                GameRule<?> gameRule = GameRule.getByName(rule);
                if (gameRule == null) continue;
                Class<?> type = gameRule.getType();
                if (type == Boolean.class) {
                    GameRule<Boolean> booleanGameRule = (GameRule<Boolean>) GameRule.getByName(rule);
                    try {
                        if (world.getGameRuleValue(booleanGameRule) != (boolean) value) {
                            world.setGameRule(booleanGameRule, (boolean) value);
                            getLogger().info("Gamerule " + booleanGameRule.getName() + " has been set to " + value);
                        }
                    } catch (ClassCastException ex) {
                        getLogger().severe("Gamerule " + rule + " requires a boolean value and not " + value);
                    }
                } else if (type == Integer.class) {
                    GameRule<Integer> integerGameRule = (GameRule<Integer>) GameRule.getByName(rule);
                    try {
                        if (!Objects.equals(world.getGameRuleValue(integerGameRule), (Integer) value)) {
                            world.setGameRule(integerGameRule, (Integer) value);
                            getLogger().info("Gamerule " + integerGameRule.getName() + " has been set to " + value);
                        }
                    } catch (ClassCastException ex) {
                        getLogger().severe("Gamerule " + rule + " requires a numberic value and not " + value);
                    }
                }
//                world.setGameRuleValue(rule, value.toString());
            }
        }
    }

    private List<String> disableAdvanceAnnounce() {
        return Bukkit.getWorlds().stream()
                .filter(w -> w.getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS))
                .peek(w -> w.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false))
                .map(World::getName)
                .collect(Collectors.toList());
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
