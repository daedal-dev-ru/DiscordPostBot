package ru.daedal.util.init.config;

import ru.daedal.config.BotConfig;
import ru.daedal.util.BotLogger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Properties;

public class ConfigInitializer {
    public BotConfig createConfigIfNotExists() {
        BotLogger.logInfo("Initializing configuration...");
        File configFile = new File("application.properties");
        Properties properties = new Properties();

        if (!configFile.exists()) {
            try {
                InputStream link = (getClass().getClassLoader().getResourceAsStream("application.properties"));
                Files.copy(Objects.requireNonNull(link), configFile.getAbsoluteFile().toPath());
                BotLogger.logInfo("File created: " + configFile.getName());
            } catch (IOException e) {
                BotLogger.logError("An error occurred during configuration file creating.");
                BotLogger.logError(e.toString());
            }
        }
        else {
            BotLogger.logInfo("Configuration file already exists. Skip configuration file creating...");
        }

        try {
            properties.load(new FileReader("application.properties"));
        } catch (IOException e) {
            BotLogger.logError("An error occurred during properties loading.");
        }

        BotConfig botConfig = new BotConfig(properties);
        botConfig.init();
        return botConfig;
    }
}
