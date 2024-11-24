package de.capitain_america.mobileplugin.ressources.rPlugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvPCommand implements CommandExecutor {
    private final PvPManager pvpManager;

    public PvPCommand(PvPManager pvpManager) {
        this.pvpManager = pvpManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            new ValidLogger().logSender(sender, "Dieser Befehl kann nur von Spielern ausgeführt werden!");
            return true;
        }

        if (!sender.hasPermission("pvp.toggle")) {
            new ValidLogger().logSender(sender, "Du hast keine Berechtigung, diesen Befehl auszuführen!");
            return true;
        }

        pvpManager.togglePvP();
        String status = pvpManager.isPvPEnabled() ? "aktiviert" : "deaktiviert";
        new ValidLogger().logSender(sender, "PvP wurde " + status + "!");
        return true;
    }
}
