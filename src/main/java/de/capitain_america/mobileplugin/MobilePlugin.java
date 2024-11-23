package de.capitain_america.mobileplugin;

import de.capitain_america.mobileplugin.ressources.*;
import de.capitain_america.mobileplugin.ressources.chatEvents.GetHelp;
import de.capitain_america.mobileplugin.ressources.rPlugin.*;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MobilePlugin extends JavaPlugin implements Listener {
    private Vanish vanishManager;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);
        getServer().getPluginManager().registerEvents(new Settings(), this);

        vanishManager = new Vanish(this);
        Objects.requireNonNull(this.getCommand("vanish")).setExecutor(vanishManager);
        Objects.requireNonNull(this.getCommand("ec")).setExecutor(new Enderchest());
        Objects.requireNonNull(this.getCommand("inv")).setExecutor(new Invsee());
        Objects.requireNonNull(this.getCommand("settings")).setExecutor(new Settings());

        getServer().getPluginManager().registerEvents(vanishManager, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
