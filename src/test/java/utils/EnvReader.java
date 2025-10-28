package utils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvReader {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String get(String key) {
        String envValue = System.getenv(key);
        if (envValue != null) {
            return envValue;
        }
        return dotenv.get(key);
    }
}
