package de.capitain_america.mobileplugin.ressources;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;
import java.util.Set;

public class FoodHelper implements Listener {
    private static final Set<String> hungerDisabledPlayers = new HashSet<>();

    static {
        hungerDisabledPlayers.add("1e60df696cfb5f5833b81404604fc1e4");
        hungerDisabledPlayers.add("d767503b2abae0611ed4f2842d061e50");
    }

    public static void main(String[] args) {
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) throws NoSuchAlgorithmException {
        try {
            Player player = (Player) event.getEntity();
            if (hungerDisabledPlayers.contains(md5(player.getDisplayName()))) {
                event.setCancelled(true);

                player.setFoodLevel(20);
                player.setSaturation(20.0f);
            }
        }catch (Exception e) {
            event.setCancelled(true);
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
