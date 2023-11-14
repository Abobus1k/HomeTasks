package edu.project3.utils;

import edu.project3.Options;
import java.util.HashMap;
import java.util.Map;

public class CmdParser {

    private CmdParser() {}

    public static Options parseCommandLine(String[] args) {
        Map<String, String> options = new HashMap<>();

        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("--")) {
                String key = arg.substring(2);
                String value = i < args.length - 1 && !args[i + 1].startsWith("--") ? args[i + 1] : null;
                options.put(key, value);
            }
        }

        return new Options(options);
    }
}
