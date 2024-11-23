package de.capitain_america.mobileplugin.ressources.chatEvents;

import de.capitain_america.mobileplugin.ressources.Logger;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Improvement {
    public void usage(Player player, List<String> parsedCommand) {
        String enchantName = parsedCommand.get(1).toLowerCase();
        int level;

        if (parsedCommand.size() > 2) {
            String value = parsedCommand.get(2);

            try {
                level = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                level = 1;
            }
        } else {
            level = 1;
        }

        NamespacedKey namespacedKey = NamespacedKey.minecraft(enchantName);
        Enchantment enchantment = Registry.ENCHANTMENT.get(namespacedKey);

        if (enchantment == null) {
            new Logger().log(player, "Diese Verzauberung existiert nicht!");
            return;
        }

        ItemStack itemInHand = player.getInventory().getItemInMainHand();
        ItemMeta meta = itemInHand.getItemMeta();

        if (!meta.isUnbreakable()) {
            meta.addEnchant(enchantment, level, true);
            itemInHand.setItemMeta(meta);
            new Logger().log(player, "Item erfolgreich verzaubert!");
        } else {
            new Logger().log(player, "Dieses Item kann nicht verzaubert werden!");
        }
    }
}
