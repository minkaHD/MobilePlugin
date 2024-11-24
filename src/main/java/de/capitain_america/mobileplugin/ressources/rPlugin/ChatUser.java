package de.capitain_america.mobileplugin.ressources.rPlugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatUser implements CommandExecutor, Listener {
    private boolean chat = true;
    private boolean adminCanSendMessage = false;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0) {
            new ValidLogger().logSender(sender, "Verwende /chat <clear, toggle, toggleadmin>");
            return false;
        }

        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "clear":
                    if (sender.isOp()) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            for (int i = 0; i < 100; i++) {
                                player.sendMessage("§r");
                            }
                            new ValidLogger().log(player, "Der Chat wurde geleert!");
                        }
                    }else {
                        new ValidLogger().logSender(sender, "Hierzu hast du keine Rechte");
                    }

                    break;
                case "toggle":
                    if (sender.isOp()) {
                        this.chat = !this.chat;
                        String status = this.chat ? "aktiviert" : "deaktiviert";
                        new ValidLogger().logSender(sender, "Der Chat wurde " + status);
                    }else {
                        new ValidLogger().logSender(sender, "Hierzu hast du keine Rechte");
                    }

                    break;

                case "toggleadmin":
                    if (sender.isOp()) {
                        this.adminCanSendMessage = !this.adminCanSendMessage;
                        String adminStatus = this.adminCanSendMessage ? " " : " keine ";

                        new ValidLogger().logSender(sender, "Admins können nun" + adminStatus + "Nachrichten versenden");
                    }else {
                        new ValidLogger().logSender(sender, "Hierzu hast du keine Rechte");
                    }

                    break;

                default:
                    new ValidLogger().logSender(sender, "Verwende /chat <clear, toggle, toggleadmin>");
            }
        }
        return true;
    }


    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        if (!this.chat) {
            Player player = event.getPlayer();

            if (this.adminCanSendMessage && player.isOp()) {
                event.setCancelled(false);
            }else {
                event.setCancelled(true);

                new ValidLogger().log(player, "Der Chat ist deaktiviert");
            }

        }
    }

}
