package de.capitain_america.mobileplugin.ressources.rPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TogglePvp extends JavaPlugin implements Listener {
    private boolean isPvPEnabled = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp()) {
                // PvP-Status umschalten
                isPvPEnabled = !isPvPEnabled;

                // Nachricht an den Sender
                player.sendMessage("§aPvP ist jetzt " + (isPvPEnabled ? "§aaktiviert" : "§cdeaktiviert") + "§a!");

                // Nachricht an alle Spieler (optional)
                getServer().broadcastMessage("§7[§6Server§7] §aPvP wurde " + (isPvPEnabled ? "§aaktiviert" : "§cdeaktiviert") + "§7!");

                return true;
            } else {
                player.sendMessage("§cDu hast keine Berechtigung, diesen Befehl zu verwenden!");
                return true;
            }
        } else {
            sender.sendMessage("§cDieser Befehl kann nur von einem Spieler ausgeführt werden!");
            return true;
        }
    }

    // PvP-Status global speichern


    // PvP-Listener
    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        // Überprüfen, ob PvP deaktiviert ist
        if (!isPvPEnabled) {
            // Überprüfen, ob das Ziel ein Spieler ist
            if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
                event.setCancelled(true);

                // Optional: Nachricht an den Angreifer
                Player damager = (Player) event.getDamager();
                damager.sendMessage("§cPvP ist derzeit deaktiviert!");
            }
        }
    }

    // Befehl zur Steuerung von PvP

}
