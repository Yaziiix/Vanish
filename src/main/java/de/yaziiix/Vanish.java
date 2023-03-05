package de.yaziiix;

import de.yaziiix.commands.CMD_Vanish;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vanish extends JavaPlugin {

    private Config config;
    private static Vanish plugin;

    public void onLoad() {
        plugin = this;
        saveDefaultConfig();
        this.config = new Config();
    }

    @Override
    public void onEnable() {
        getCommand("vanish").setExecutor(new CMD_Vanish());
        System.out.println("------------------");
        System.out.println("Vanish von Yaziiix");
        System.out.println("Aktiviert");
        System.out.println("------------------");

    }

    @Override
    public void onDisable() {
        System.out.println("------------------");
        System.out.println("Vanish von Yaziiix");
        System.out.println("Deaktiviert");
        System.out.println("------------------");
    }

    public static Vanish getPlugin() {
        return plugin;
    }

    public Config getConfiguration() {
        return config;
    }
}
