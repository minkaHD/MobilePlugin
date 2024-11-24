package de.capitain_america.mobileplugin.ressources.rPlugin;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class PvPManager implements Listener {
    private boolean pvpEnabled = true;

    public void togglePvP() {
        pvpEnabled = !pvpEnabled;

        if (!pvpEnabled) {
            showPvPDisabledMessage();
        } else {
            clearPvPMessage();
        }
    }

    public boolean isPvPEnabled() {
        return pvpEnabled;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!pvpEnabled && event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            event.setCancelled(true);
        }
    }

    private void showPvPDisabledMessage() {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (pvpEnabled) {
                    cancel();
                    return;
                }

                // Nachricht an alle Spieler senden
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy("§c§lPvP ist aktuell deaktiviert!"));
                }
            }
        }.runTaskTimer(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("MobilePlugin")), 0L, 40L);
    }

    // Leert die ActionBar, wenn PvP aktiviert wird
    private void clearPvPMessage() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacy(""));
        }
    }
}
