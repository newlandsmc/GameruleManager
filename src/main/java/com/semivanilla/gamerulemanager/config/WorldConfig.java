package com.semivanilla.gamerulemanager.config;

import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldConfig {

    private final String worldName;

    public WorldConfig(String worldName) {
        this.worldName = worldName;
        init();
    }

    public void init() {
        Config.readConfig(WorldConfig.class, this);
    }

    private void set(String path, Object val) {
        Config.config.addDefault("world-settings.default." + path, val);
        Config.config.set("world-settings.default." + path, val);
        if (Config.config.get("world-settings." + worldName + "." + path) != null) {
            Config.config.addDefault("world-settings." + worldName + "." + path, val);
            Config.config.set("world-settings." + worldName + "." + path, val);
        }
    }

    private ConfigurationSection getConfigurationSection(String path) {
        ConfigurationSection section = Config.config.getConfigurationSection("world-settings." + worldName + "." + path);
        return section != null ? section : Config.config.getConfigurationSection("world-settings.default." + path);
    }

    private boolean getBoolean(String path, boolean def) {
        Config.config.addDefault("world-settings.default." + path, def);
        return Config.config.getBoolean("world-settings." + worldName + "." + path, Config.config.getBoolean("world-settings.default." + path));
    }

    private double getDouble(String path, double def) {
        Config.config.addDefault("world-settings.default." + path, def);
        return Config.config.getDouble("world-settings." + worldName + "." + path, Config.config.getDouble("world-settings.default." + path));
    }

    private int getInt(String path, int def) {
        Config.config.addDefault("world-settings.default." + path, def);
        return Config.config.getInt("world-settings." + worldName + "." + path, Config.config.getInt("world-settings.default." + path));
    }

    private <T> List<?> getList(String path, T def) {
        Config.config.addDefault("world-settings.default." + path, def);
        return Config.config.getList("world-settings." + worldName + "." + path, Config.config.getList("world-settings.default." + path));
    }

    private String getString(String path, String def) {
        Config.config.addDefault("world-settings.default." + path, def);
        return Config.config.getString("world-settings." + worldName + "." + path, Config.config.getString("world-settings.default." + path));
    }

    private Map<String, Object> getMap(String path, Map<String, Object> def) {
        final Map<String, Object> fallback = Config.getMap("world-settings.default." + path, def);
        final Map<String, Object> value = Config.getMap("world-settings." + worldName + "." + path, null);
        return value.isEmpty() ? fallback : value;
    }

    public Map<String, Object> customRules = new HashMap<>();
    private void gameruleSettings() {
        customRules = getMap("gamerules", Map.ofEntries(
                Map.entry("announceAdvancements", false)
        ));
    }
}
