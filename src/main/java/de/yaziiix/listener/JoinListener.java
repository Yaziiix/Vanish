package de.yaziiix.listener;

import de.yaziiix.Vanish;
import de.yaziiix.commands.CMD_Vanish;
import de.yaziiix.utils.Config;
import de.yaziiix.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    Config config = Vanish.getPlugin().getConfiguration();

    @EventHandler
    public void handle(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (Data.onjoin.equalsIgnoreCase("true")) {
            if (!Data.vanish.contains(player)) {
                Data.vanish.add(player);
                Bukkit.getOnlinePlayers().forEach(all -> all.hidePlayer(player));
                player.sendMessage(Data.vanish_on);
            } else {
                Data.vanish.remove(player);
                Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(player));
                player.sendMessage(Data.vanish_off);
            }
        } else if (Data.onjoin.equalsIgnoreCase("false")) {
            return;
        } else {
            if (player.isOp()) {
                player.sendMessage(Data.prefix + "§cIn the config for onjoin, a true or false is missing.");
                player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_BREAK, 1, 1);
            }
        }
    }

}
