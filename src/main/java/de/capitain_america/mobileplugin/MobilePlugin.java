package de.capitain_america.mobileplugin;

import de.capitain_america.mobileplugin.ressources.*;
import de.capitain_america.mobileplugin.ressources.rPlugin.Enderchest;
import de.capitain_america.mobileplugin.ressources.rPlugin.Invsee;
import de.capitain_america.mobileplugin.ressources.rPlugin.TogglePvp;
import de.capitain_america.mobileplugin.ressources.rPlugin.Vanish;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MobilePlugin extends JavaPlugin implements Listener {
    private Vanish vanishManager;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);

        vanishManager = new Vanish(this);
        this.getCommand("vanish").setExecutor(vanishManager);
        this.getCommand("ec").setExecutor(new Enderchest());
        this.getCommand("inv").setExecutor(new Invsee());
        this.getCommand("togglepvp").setExecutor(new TogglePvp());

        getServer().getPluginManager().registerEvents(vanishManager, this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
