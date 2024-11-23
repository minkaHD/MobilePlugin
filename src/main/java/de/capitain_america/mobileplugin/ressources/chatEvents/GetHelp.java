package de.capitain_america.mobileplugin.ressources.chatEvents;

import org.bukkit.entity.Player;

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
