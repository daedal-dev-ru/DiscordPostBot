package ru.daedal.utils.init;

import ru.daedal.config.BotConfig;
import ru.daedal.utils.BotLogger;
import ru.daedal.utils.ConsoleColor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigInitializer {
    public BotConfig createConfigIfNotExists() {
        BotLogger.logInfo("Initializing configuration...");
        File configFile = new File("application.properties");
        Properties properties = new Properties();

        /*
        if (!configFile.exists()) {
            try {
                if (configFile.createNewFile()) {
                    properties.load(BotApplication.class.getResource("application.properties"));
                    BotLogger.logInfo("File created: " + configFile.getName());
                } else {
                    BotLogger.logInfo("Configuration file already exists. Skip configuration file creating...");
                }
            } catch (IOException e) {
                BotLogger.logError("An error occurred when creating configuration file");
                BotLogger.logError(e.toString());
            }
        }
        else {
            try {
                properties.load(new FileReader("application.properties"));
            } catch (IOException e) {
                BotLogger.logError("An error occurred when loading properties.");
            }
        } */

        try {
            properties.load(new FileReader("application.properties"));
        } catch (IOException e) {
            BotLogger.logError("ERROR");
        }

        BotConfig botConfig = new BotConfig(properties);
        botConfig.init();
        return botConfig;
    }
}
