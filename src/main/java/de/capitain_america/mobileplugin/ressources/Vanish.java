package de.capitain_america.mobileplugin.ressources;

import de.capitain_america.mobileplugin.MobilePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;

public class Vanish implements CommandExecutor, Listener {

    private final MobilePlugin plugin;
    private final Set<Player> vanishedPlayers = new HashSet<>();

    public Vanish(MobilePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Dieser Befehl kann nur von Spielern ausgeführt werden.");
            return true;
        }

        Player player = (Player) sender;

        if (player.isOp()) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                if (target != null) {
                    if (vanishedPlayers.contains(target)) {
                        showPlayer(target);
                        target.sendMessage("§aDu bist nun sichtbar!");
                    } else {
                        hidePlayer(player);
                        target.sendMessage("§cDu bist nun unsichtbar!");
                    }
                }else {
                    player.sendMessage("§cSpieler wurde nicht gefunden");
                }
            }
            else {
                if (vanishedPlayers.contains(player)) {
                    showPlayer(player);
                    player.sendMessage("§aDu bist nun sichtbar!");
                } else {
                    hidePlayer(player);
                    player.sendMessage("§cDu bist nun unsichtbar!");
                }
            }
        }else
            sender.sendMessage("§cDu hast keine Rechte dafür");
        return true;
    }

    public void hidePlayer(Player player) {
        vanishedPlayers.add(player);
        for (Player other : Bukkit.getOnlinePlayers()) {
            other.hidePlayer((Plugin) plugin, player);
        }
    }

    public void showPlayer(Player player) {
        vanishedPlayers.remove(player);
        for (Player other : Bukkit.getOnlinePlayers()) {
            other.showPlayer((Plugin) plugin, player);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player joinedPlayer = event.getPlayer();
        for (Player vanished : vanishedPlayers) {
            joinedPlayer.hidePlayer(plugin, vanished);
        }
    }
}
