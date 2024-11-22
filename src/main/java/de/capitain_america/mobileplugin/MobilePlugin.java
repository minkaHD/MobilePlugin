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
}
