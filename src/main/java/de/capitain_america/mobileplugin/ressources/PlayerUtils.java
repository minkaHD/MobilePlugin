package de.capitain_america.mobileplugin.ressources;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class PlayerUtils {
    public static Player getPlayerByName(String playerName) {
        Player player = Bukkit.getPlayer(playerName);

        if (player != null && player.isOnline()) {
            return player;
        } else {
            return null;
        }
    }
}
