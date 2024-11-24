package de.capitain_america.mobileplugin.ressources.chatEvents;
import de.capitain_america.mobileplugin.ressources.Logger;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.generator.structure.StructureType;
import org.bukkit.structure.Structure;
import org.bukkit.util.StructureSearchResult;
import org.checkerframework.checker.units.qual.N;

import java.text.MessageFormat;
import java.util.Objects;

public class Locate {

    public Locate(Player player, String locationName) {
        Location location = player.getLocation();
        Location biomeLocation = GetBiomeLocation(player, locationName);
        Location structureLocation = GerStructureLocation(player, locationName);

        if (biomeLocation != null) {
            new Logger().log(player, MessageFormat.format("{0}: X: {1} Z: {2}",
                    locationName, biomeLocation.getBlockX(), biomeLocation.getBlockZ()));
            return;
        } else if (structureLocation != null) {
            new Logger().log(player, MessageFormat.format("{0}: X: {1} Z: {2}",
                    locationName, structureLocation.getBlockX(), structureLocation.getBlockZ()));
            return;
        } else
            new Logger().log(player, MessageFormat.format("Location: {0} could not be found", locationName));
    }

    private Location GetBiomeLocation(Player player, String locationName)
    {
        try {
            Biome biome = Biome.valueOf(locationName);
            return Objects.requireNonNull(player.getWorld().locateNearestBiome(player.getLocation(), 100000, biome)).getLocation();
        }
        catch (Exception ex)
        {
            return  null;
        }
    }

    private Location GerStructureLocation(Player player, String locationName)
    {
        try
        {
            NamespacedKey key = NamespacedKey.minecraft(locationName);
            StructureType structure = Registry.STRUCTURE_TYPE.get(key);
            return  Objects.requireNonNull(player.getWorld().locateNearestStructure(player.getLocation(), structure, 100000, true)).getLocation();
        }
        catch (Exception ex)
        {
            return  null;
        }
    }
}
