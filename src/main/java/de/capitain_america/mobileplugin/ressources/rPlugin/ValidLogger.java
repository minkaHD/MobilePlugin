package de.capitain_america.mobileplugin.ressources.rPlugin;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ValidLogger {
    public void log(Player player, String message) {
        player.sendMessage("§8[§a§lStarboy.de§8] §7" + message);
    }
    public void logSender(CommandSender sender, String message) {
        sender.sendMessage("§8[§a§lStarboy.de§8] §7" + message);
    }
    public void error(CommandSender sender, String message) {
        sender.sendMessage("§8[§a§lStarboy.de§8] §7" + message);
    }
}
