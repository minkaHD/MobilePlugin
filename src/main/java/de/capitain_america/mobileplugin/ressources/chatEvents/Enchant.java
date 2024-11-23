package de.capitain_america.mobileplugin.ressources.chatEvents;

import de.capitain_america.mobileplugin.ressources.CommandParser;
import de.capitain_america.mobileplugin.ressources.Logger;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.inventory.MainHand;
import org.bukkit.plugin.Plugin;

import java.text.MessageFormat;
import java.util.List;

public class Enchant {

    private final Logger _logger = new Logger();
    private final Player _player;

    public Enchant(Player player)
    {
        _player = player;
    }

    public void EnchantItem(String input)
    {
        try {
            List<String> enchantParams = CommandParser.parseCommand(input);
            if (enchantParams.get(1).equals("info")) {
                EnchantmentInfo(); return;
            }

            NamespacedKey key = NamespacedKey.minecraft(enchantParams.get(1));
            int level = Integer.parseInt(enchantParams.get(2));

            Enchantment enchantment = Registry.ENCHANTMENT.get(key);
            assert enchantment != null;

            ItemStack enchantItem = _player.getInventory().getItemInMainHand();
            enchantItem.addUnsafeEnchantment(enchantment, level);

            if (level >= 255) level = 255;
            _logger.log(_player, MessageFormat.format("Enchanted: {0} level: {1}",
                    enchantParams.get(1), level));
        }
        catch (Exception ex)
        {
            _logger.log(_player, "Enchantment could not be found! Use: @en info");
        }
    }

    private void EnchantmentInfo()
    {
        StringBuilder enchantments = new StringBuilder();
        enchantments.append("Enchantments: Normal max level:\n");
        for (Enchantment en : Registry.ENCHANTMENT)
        {
            enchantments.append(en.getKey()).append(": ").append(en.getMaxLevel()).append("\n");
        }
        _logger.log(_player, enchantments.toString());
    }
}
