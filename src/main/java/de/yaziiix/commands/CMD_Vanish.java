package de.yaziiix.commands;

import de.yaziiix.Config;
import de.yaziiix.Vanish;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import java.util.Objects;

public class CMD_Vanish implements CommandExecutor {

    Config config = Vanish.getPlugin().getConfiguration();

    private ArrayList<Player> vanish = new ArrayList<>();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission(Objects.requireNonNull(config.getConfig().getString("config.permission")))) {
                if (args.length == 0) {
                    if (vanish.contains(player)) {
                        vanish.remove(player);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(player);
                            player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.vanish_off").replaceAll("&", "§"));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    } else {
                        vanish.add(player);
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            all.hidePlayer(player);
                            player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.vanish_on").replaceAll("&", "§"));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (vanish.contains(target)) {
                            vanish.remove(target);
                            player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.vanish_other_off").replace("%player%", target.getName()).replaceAll("&", "§"));
                            target.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.vanish_off").replaceAll("&", "§"));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        } else {
                            vanish.add(target);
                            player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.vanish_other_on").replace("%player%", target.getName()).replaceAll("&", "§"));
                            target.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.vanish_on").replaceAll("&", "§"));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    } else {
                        player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.not_found").replace("%not_player%", args[0]).replaceAll("&", "§"));
                    }
                } else {
                    player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.use").replaceAll("&", "§"));
                }
            } else {
                player.sendMessage(config.getConfig().getString("config.prefix").replaceAll("&", "§") + config.getConfig().getString("config.nopermissions").replaceAll("&", "§"));
            }
            return true;
        } else {
            sender.sendMessage("§cDu musst ein Spieler sein.");
        }
        return false;
    }
}
