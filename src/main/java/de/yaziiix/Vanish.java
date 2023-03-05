package de.yaziiix;

import de.yaziiix.commands.CMD_Vanish;
import de.yaziiix.listener.JoinListener;
import de.yaziiix.utils.Config;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vanish extends JavaPlugin {

    public static Config config;
    private static Vanish plugin;

    public void onLoad() {
        plugin = this;
        saveDefaultConfig();
        this.config = new Config();
    }

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new JoinListener(), this);
        getCommand("vanish").setExecutor(new CMD_Vanish());
        System.out.println("------------------");
        System.out.println("Vanish from Yaziiix");
        System.out.println("Activated");
        System.out.println("------------------");

    }

    @Override
    public void onDisable() {
        System.out.println("------------------");
        System.out.println("Vanish from Yaziiix");
        System.out.println("Disabled");
        System.out.println("------------------");
    }

    public static Vanish getPlugin() {
        return plugin;
    }

    public static Config getConfiguration() {
        return config;
    }
}
