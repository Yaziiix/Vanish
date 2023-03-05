package de.yaziiix.commands;

import de.yaziiix.utils.Config;
import de.yaziiix.Vanish;
import de.yaziiix.utils.Data;
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
            if (player.hasPermission(Data.permission)) {
                if (args.length == 0) {
                    if (vanish.contains(player)) {
                        vanish.remove(player);
                        Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(player));
                            player.sendMessage(Data.vanish_off);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    } else {
                        vanish.add(player);
                        Bukkit.getOnlinePlayers().forEach(all -> all.hidePlayer(player));
                            player.sendMessage(Data.vanish_on);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    }
                } else if (args.length == 1) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target != null) {
                        if (vanish.contains(target)) {
                            vanish.remove(target);
                            Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(target));
                            player.sendMessage(Data.vanish_other_off.replace("%player%", target.getName()));
                            target.sendMessage(Data.vanish_off);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        } else {
                            vanish.add(target);
                            Bukkit.getOnlinePlayers().forEach(all -> all.hidePlayer(target));
                            player.sendMessage(Data.vanish_other_on.replace("%player%", target.getName()));
                            target.sendMessage(Data.vanish_on);
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                        }
                    } else {
                        player.sendMessage(Data.not_found.replace("%not_player%", args[0]));
                    }
                } else {
                    player.sendMessage(Data.use);
                }
            } else {
                player.sendMessage(Data.nopermissions);
            }
            return true;
        } else {
            sender.sendMessage("§cYou must be a Player.");
        }
        return false;
    }
}
