package de.capitain_america.mobileplugin.ressources;

import org.bukkit.entity.Player;

public class Logger {
    public void log(Player player, String message) {
        player.sendMessage("§8[§5§lMicroHack§8] §7" + message);
    }

    public void logError(Player player) {
        player.sendMessage("§8[§5§lMicroHack§8] §7Ein Fehler ist aufgetreten und wurde abgefangen");
    }

    public void logCommandEvent(Player player, String message, String from) {
        player.sendMessage("§8[§9§lConsole-Event§8] (" + from + ") §7" + message);
    }
}
