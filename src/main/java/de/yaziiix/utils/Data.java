package de.yaziiix.utils;

import de.yaziiix.Vanish;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Data {

    static Config config = Vanish.getPlugin().getConfiguration();
    public static ArrayList<Player> vanish = new ArrayList<>();

    public static String prefix = config.getConfig().getString("config.prefix").replaceAll("&", "§");
    public static String vanish_on = prefix + config.getConfig().getString("config.vanish_on").replaceAll("&", "§");
    public static String vanish_off = prefix + config.getConfig().getString("config.vanish_off").replaceAll("&", "§");
    public static String vanish_other_on = prefix + config.getConfig().getString("config.vanish_other_on").replaceAll("&", "§");
    public static String vanish_other_off = prefix + config.getConfig().getString("config.vanish_other_off").replaceAll("&", "§");
    public static String not_found = prefix + config.getConfig().getString("config.not_found").replaceAll("&", "§");
    public static String nopermissions = prefix + config.getConfig().getString("config.nopermissions").replaceAll("&", "§");
    public static String permission = prefix + config.getConfig().getString("config.permission").replaceAll("&", "§");
    public static String use = prefix + config.getConfig().getString("config.use").replaceAll("&", "§");
    public static String onjoin = config.getConfig().getString("config.onjoin");

}
