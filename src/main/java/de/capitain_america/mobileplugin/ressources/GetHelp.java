package de.capitain_america.mobileplugin.ressources;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GetHelp {

    public GetHelp(Player player)
    {
        String[] commands = {
                "@h > help you with commands",
                "@crash <player name>",
                "@give <item name> <count>",
                "@track <player name>",
                "@en <enchantment> <level>",
        };

        for (String co : commands) {
            player.sendMessage(co);
        }
    }
}
