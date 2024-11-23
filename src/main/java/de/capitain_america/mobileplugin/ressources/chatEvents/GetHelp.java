package de.capitain_america.mobileplugin.ressources.chatEvents;

import de.capitain_america.mobileplugin.ressources.Logger;
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
                "@help > hopefully helps you!",
                "@crash <player name>",
                "@give <item name> <count>",
                "@track <player name>",
                "@en <enchantment> <level 0 - 255>",
                "@ipinfo <player name>",
        };

        StringBuilder command = new StringBuilder();
        command.append("Commands:\n");
        for (String co : commands) {
            command.append(co).append("\n");
        }
        new Logger().log(player, command.toString());
    }
}
