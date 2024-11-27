package de.capitain_america.mobileplugin.ressources.chatEvents;
import de.capitain_america.mobileplugin.ressources.Logger;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

public class GetCoordinates {

    private final Player _player;
    private final Logger _logger = new Logger();

    public GetCoordinates(Player player)
    {
        _player = player;
    }

    public void GetLocationOfPlayer(String playerName)
    {
        try {
            Player trackPlayer = Bukkit.getPlayer(playerName);
            assert trackPlayer != null;

            Location location = trackPlayer.getLocation();
            _logger.log(_player, MessageFormat.format("{0}: X: {1} Y: {2} Z: {3}",
                    trackPlayer.getDisplayName(),
                    location.getBlockX(),
                    location.getBlockY(),
                    location.getBlockZ()));
        }
        catch (Exception ex)
        {
            _logger.log(_player, "The player could not be found!");
        }
    }

    public void GetLocationOfPlayerLife()
    {}
}
