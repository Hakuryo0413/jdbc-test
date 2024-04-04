package org.example;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    Dotenv dotenv;

    public Config() {
        dotenv = Dotenv.configure().directory("src/main/asset/").load();
    }

    public String get(String key) {
        return dotenv.get(key);
    }
}
