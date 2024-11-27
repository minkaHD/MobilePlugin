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
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Settings implements CommandExecutor, Listener {
    private final int clockEventSlot = 12;
    private final int backpackEventSlot = 14;
    private final int vanishEventSlot = 18;
    private final int toogleAdminEventSlot = 17;
    private final int chatEventSlot = 26;
    private final int pvpEventSlot = 0;
    private final int chatClearEventSlot = 8;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Inventory inventory = Bukkit.createInventory(null, 27, "Settings");

            HashMap<Integer, ItemStack> intigerItemStackHashMap = new HashMap<>();

            ItemStack clockItem = new ItemStack(Material.CLOCK);
            ItemMeta clockMeta = clockItem.getItemMeta();
            assert clockMeta != null;
            clockMeta.setDisplayName("§6Timer vortsetzen");
            clockMeta.setLore(Collections.singletonList("§7Aktiviere den Timer, um mit dem Spiel zu beginnen."));
            clockItem.setItemMeta(clockMeta);
            intigerItemStackHashMap.put(clockEventSlot, clockItem);

            ItemStack backpackItem = new ItemStack(Material.ENDER_CHEST);
            ItemMeta backpackMeta = backpackItem.getItemMeta();
            assert backpackMeta != null;
            backpackMeta.setDisplayName("§6Backpack");
            backpackMeta.setLore(Collections.singletonList("§7Öffne das Globale Backpack"));
            backpackItem.setItemMeta(backpackMeta);
            intigerItemStackHashMap.put(backpackEventSlot, backpackItem);


            if (player.isOp() ) {
                ItemStack vanishItem = new ItemStack(Material.WIND_CHARGE);
                ItemMeta vanishMeta = vanishItem.getItemMeta();
                assert vanishMeta != null;
                vanishMeta.setDisplayName("§6Vanish");
                vanishMeta.setLore(Collections.singletonList("§7Wechsle in den Vanish-Modus, damit dich niemand sehen kann."));
                vanishItem.setItemMeta(vanishMeta);
                intigerItemStackHashMap.put(vanishEventSlot, vanishItem);

                ItemStack toogleChatItem = new ItemStack(Material.TRIAL_KEY);
                ItemMeta toogleChatMeta = toogleChatItem.getItemMeta();
                assert toogleChatMeta != null;
                toogleChatMeta.setDisplayName("§6Wechsel Admin Chat");
                toogleChatMeta.setLore(Collections.singletonList("§7Entscheide ob Admins schreiben können wenn der Chat aus ist"));
                toogleChatItem.setItemMeta(toogleChatMeta);
                intigerItemStackHashMap.put(toogleAdminEventSlot, toogleChatItem);

                ItemStack chatItem = new ItemStack(Material.WRITABLE_BOOK);
                ItemMeta chatMeta = chatItem.getItemMeta();
                assert chatMeta != null;
                chatMeta.setDisplayName("§6Chat");
                chatMeta.setLore(Collections.singletonList("§7Entscheide ob im Chat geschrieben werden soll oder nicht"));
                chatItem.setItemMeta(chatMeta);
                intigerItemStackHashMap.put(chatEventSlot, chatItem);

                ItemStack chatClearItem = new ItemStack(Material.BARRIER);
                ItemMeta chatClearMeta = chatClearItem.getItemMeta();
                assert chatClearMeta != null;
                chatClearMeta.setDisplayName("§6Chat leeren");
                chatClearMeta.setLore(Collections.singletonList("§7Leer den Chat um die Übersicht zu behalten"));
                chatClearItem.setItemMeta(chatClearMeta);
                intigerItemStackHashMap.put(chatClearEventSlot, chatClearItem);

                ItemStack pvpItem = new ItemStack(Material.DIAMOND_SWORD);
                ItemMeta pvpMeta = pvpItem.getItemMeta();
                assert pvpMeta != null;
                pvpMeta.setDisplayName("§6PvP");
                pvpMeta.setLore(Collections.singletonList("§7Entscheide ob aktiviert oder deaktiviert ist."));
                pvpItem.setItemMeta(pvpMeta);
                intigerItemStackHashMap.put(pvpEventSlot, pvpItem);
            }

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
                    player.closeInventory();
                    break;
                case toogleAdminEventSlot:
                    player.performCommand("chat toggleadmin");
                    player.closeInventory();
                    break;
                case pvpEventSlot:
                    if (player.isOp()) {
                        player.performCommand("togglepvp");
                        player.closeInventory();
                    }
                    break;
                case chatClearEventSlot:
                    if (player.isOp()) {
                        player.performCommand("chat clear");
                        player.closeInventory();
                    }
                    break;
                case chatEventSlot:
                    player.performCommand("chat toggle");
                    player.closeInventory();
                    break;
                case backpackEventSlot:
                    player.performCommand("backpack");
                    break;
                case vanishEventSlot:
                    if (player.isOp()) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "vanish " + player.getName());
                        player.closeInventory();
                    }
                    break;
            }
        };
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {}
}
