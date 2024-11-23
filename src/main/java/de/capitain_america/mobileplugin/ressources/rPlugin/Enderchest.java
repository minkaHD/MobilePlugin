package de.capitain_america.mobileplugin.ressources.rPlugin;

import de.capitain_america.mobileplugin.ressources.PlayerUtils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Enderchest implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 0) {
            player.openInventory(player.getEnderChest());
            player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 1.0f);
            return true;
        }else {
            Player target = PlayerUtils.getPlayerByName(args[0]);

            if (target != null) {
                if (target.isOnline()) {
                    player.openInventory(target.getEnderChest());
                    player.playSound(player.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, 1.0f, 1.0f);

                    return true;
                }
            }

            new ValidLogger().log(player, "Der Spieler konnte nicht gefunden werden");
        }

        return true;
    }

}
