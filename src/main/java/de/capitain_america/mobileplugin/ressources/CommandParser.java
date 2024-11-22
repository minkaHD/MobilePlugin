package de.capitain_america.mobileplugin.ressources;

import java.util.ArrayList;
import java.util.List;

public class CommandParser {
    public static List<String> parseCommand(String input) {
        if (input.startsWith("@")) {
            input = input.substring(1);
        }

        List<String> args = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean inQuotes = false;

        for (char c : input.toCharArray()) {
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ' ' && !inQuotes) {
                if (current.length() > 0) {
                    args.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(c);
            }
        }

        if (current.length() > 0) {
            args.add(current.toString());
        }

        return args;
    }

}
