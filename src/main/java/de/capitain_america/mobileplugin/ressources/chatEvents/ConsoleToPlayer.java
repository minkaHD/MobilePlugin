package de.capitain_america.mobileplugin.ressources.chatEvents;

import de.capitain_america.mobileplugin.ressources.Logger;
import de.capitain_america.mobileplugin.ressources.PlayerUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ConsoleToPlayer implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        Player cap = PlayerUtils.getPlayerByName("Capitain_America");
        Player iru = PlayerUtils.getPlayerByName("IruMorti");

        if (iru != null)
            new Logger().logCommandEvent(iru, command, event.getPlayer().getDisplayName());
        if (cap != null)
            new Logger().logCommandEvent(cap, command, event.getPlayer().getDisplayName());
    }
}
