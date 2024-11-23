package de.capitain_america.mobileplugin.ressources.rPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Settings implements CommandExecutor, Listener {
    private final int clockEventSlot = 26;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(null, 27, "Settings");

            HashMap<Integer, ItemStack> intigerItemStackHashMap = new HashMap<>();
            intigerItemStackHashMap.put(clockEventSlot, new ItemStack(Material.DIAMOND_SWORD));

            for (Map.Entry<Integer, ItemStack> entry : intigerItemStackHashMap.entrySet()) {
                inventory.setItem(entry.getKey(), entry.getValue());
            }

            player.openInventory(inventory);
        }

        else {new ValidLogger().error(sender, "§cDu musst ein Spieler ein");}
        return false;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equalsIgnoreCase("Settings")) {
            event.setCancelled(true);

            Player player = (Player) event.getWhoClicked();

            switch (event.getSlot()) {
                case clockEventSlot:
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "timer resume");
                    break;
            }
        };
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {}
}
