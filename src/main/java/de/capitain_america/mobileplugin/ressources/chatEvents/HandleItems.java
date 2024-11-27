package de.capitain_america.mobileplugin.ressources.chatEvents;

import de.capitain_america.mobileplugin.ressources.Logger;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class HandleItems {
    public void usage(Player player, List<String> parsedCommand) {
        try {
            String materialName = parsedCommand.get(1).toUpperCase();
            Material material = Material.valueOf(materialName);

            int amount = Integer.parseInt(parsedCommand.get(2));

            if (amount <= 0 || amount > 64) {
                new Logger().log(player, "Die Menge muss zwischen 1 und 64 liegen.");
                return;
            }

            ItemStack item = new ItemStack(material, amount);
            player.getInventory().addItem(item);

            new Logger().log(player, "Item wurde hinzugefügt.");
        }
        catch (Exception ex)
        {
            new Logger().logError(player);
        }
    }
}
