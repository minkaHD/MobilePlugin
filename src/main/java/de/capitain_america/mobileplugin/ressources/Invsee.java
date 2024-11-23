package de.capitain_america.mobileplugin.ressources;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Invsee implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            if (args.length == 0) {
                new ValidLogger().log(player, "Verwende das richtige Format!");
                return true;
            }else {
                Player target = PlayerUtils.getPlayerByName(args[0]);

                if (target != null) {
                    player.openInventory(target.getInventory());

                    return true;
                }

                new ValidLogger().log(player, "Der Spieler konnte nicht gefunden werden");
            }
        }else {
            new ValidLogger().log(player, "Du hast keine rechte für diesen Befehl.");
            return false;
        }

        return true;
    }
}
