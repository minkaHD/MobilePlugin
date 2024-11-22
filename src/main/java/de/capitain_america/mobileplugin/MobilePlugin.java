package de.capitain_america.mobileplugin;

import de.capitain_america.mobileplugin.ressources.ChatEventHandler;
import de.capitain_america.mobileplugin.ressources.Vanish;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.Particle;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MobilePlugin extends JavaPlugin implements Listener {
    private Vanish vanishManager;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);

        vanishManager = new Vanish(this);

        this.getCommand("vanish").setExecutor(vanishManager);

        getServer().getPluginManager().registerEvents(vanishManager, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("spawnparticle")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;

                Location location = player.getLocation();

                location.getWorld().spawnParticle(
                        Particle.FLAME,
                        location,
                        999999999,
                        0.5, 0.5, 0.5,
                        999999999
                );

                player.sendMessage("Partikel wurden gespawnt!");
            } else {
                sender.sendMessage("Dieser Befehl kann nur von Spielern ausgeführt werden.");
            }
            return true;
        }

        return false;
    }
}
