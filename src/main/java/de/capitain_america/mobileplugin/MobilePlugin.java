package de.capitain_america.mobileplugin;

import de.capitain_america.mobileplugin.ressources.*;
import de.capitain_america.mobileplugin.ressources.rPlugin.*;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MobilePlugin extends JavaPlugin implements Listener {
    private Vanish vanishManager;
    private Backpack backpack;
    private ChatUser chatUser;
    private PvPManager pvpManager;



    @Override
    public void onEnable() {
        chatUser = new ChatUser();
        vanishManager = new Vanish(this);
        backpack = new Backpack(this);
        pvpManager = new PvPManager();

        getServer().getPluginManager().registerEvents(new ChatEventHandler(), this);
        getServer().getPluginManager().registerEvents(new Settings(), this);
        getServer().getPluginManager().registerEvents(chatUser, this);

        // Event-Listener registrieren
        getServer().getPluginManager().registerEvents(pvpManager, this);

        Objects.requireNonNull(this.getCommand("vanish")).setExecutor(vanishManager);
        Objects.requireNonNull(this.getCommand("ec")).setExecutor(new Enderchest());
        Objects.requireNonNull(this.getCommand("inv")).setExecutor(new Invsee());
        Objects.requireNonNull(this.getCommand("settings")).setExecutor(new Settings());
        Objects.requireNonNull(this.getCommand("backpack")).setExecutor(backpack);
        Objects.requireNonNull(this.getCommand("chat")).setExecutor(chatUser);
        Objects.requireNonNull(getCommand("togglepvp")).setExecutor(new PvPCommand(pvpManager));

        getServer().getPluginManager().registerEvents(vanishManager, this);
    }

    @Override
    public void onDisable() {
        backpack.saveBackpack();
    }
}
