package de.capitain_america.mobileplugin.ressources.rPlugin;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Backpack implements CommandExecutor {

    private Inventory backpack;
    private File saveFile;

    public Backpack(JavaPlugin plugin) {
        saveFile = new File(plugin.getDataFolder(), "global_backpack.yml");
        if (!saveFile.exists()) {
            saveFile.getParentFile().mkdirs();
            try {
                saveFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        backpack = loadBackpack();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            player.openInventory(backpack);
            player.playSound(player.getLocation(), Sound.ITEM_BUNDLE_DROP_CONTENTS, 1.0f, 1.0f);
            return true;
        } else {
            sender.sendMessage("Nur Spieler können diesen Befehl nutzen!");
            return true;
        }
    }

    public void saveBackpack() {
        FileConfiguration config = YamlConfiguration.loadConfiguration(saveFile);

        for (int i = 0; i < backpack.getSize(); i++) {
            ItemStack item = backpack.getItem(i);
            if (item != null) {
                config.set("backpack.slot" + i, item);
            } else {
                config.set("backpack.slot" + i, null);
            }
        }

        try {
            config.save(saveFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Inventory loadBackpack() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Global Backpack");
        FileConfiguration config = YamlConfiguration.loadConfiguration(saveFile);

        for (int i = 0; i < inventory.getSize(); i++) {
            if (config.contains("backpack.slot" + i)) {
                inventory.setItem(i, config.getItemStack("backpack.slot" + i));
            }
        }

        return inventory;
    }
}
