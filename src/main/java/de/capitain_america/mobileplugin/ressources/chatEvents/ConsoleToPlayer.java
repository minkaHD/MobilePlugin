package de.capitain_america.mobileplugin.ressources.chatEvents;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.OutputStream;
import java.io.PrintStream;

public class ConsoleToPlayer {
    private final Player targetPlayer;
    private final PrintStream originalOut;
    private final PrintStream originalErr;

    public ConsoleToPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
        this.originalOut = System.out;
        this.originalErr = System.err;

        hookConsole();
    }

    // Methode, um die Konsole abzufangen
    private void hookConsole() {
        PrintStream customOut = new PrintStream(new OutputStream() {
            private final StringBuilder buffer = new StringBuilder();

            @Override
            public void write(int b) {
                buffer.append((char) b); // Schreibe Daten in den Puffer
                if (b == '\n') { // Nachricht vollständig
                    String message = buffer.toString();
                    buffer.setLength(0); // Puffer leeren
                    sendToPlayer(message.trim()); // Nachricht an Spieler senden
                }
            }
        });

        // Setze den neuen OutputStream für System.out und System.err
        System.setOut(customOut);
        System.setErr(customOut);
    }

    // Methode, um Nachrichten an den Spieler zu senden
    private void sendToPlayer(String message) {
        if (targetPlayer != null && targetPlayer.isOnline()) {
            Bukkit.getScheduler().runTask(
                    Bukkit.getPluginManager().getPlugin("MobilePlugin"),
                    () -> targetPlayer.sendMessage("§7[Konsole] §r" + message)
            );
        }
    }

    // Methode, um die ursprüngliche Konsole wiederherzustellen
    public void restoreConsole() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }
}
