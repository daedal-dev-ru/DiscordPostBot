package ru.daedal;

import ru.daedal.bot.MessageSender;
import ru.daedal.config.BotConfig;
import ru.daedal.task.MessageSendTask;
import ru.daedal.util.BotLogger;
import ru.daedal.util.ConsoleColor;
import ru.daedal.util.init.attachments.AttachmentsInitializer;
import ru.daedal.util.init.config.ConfigInitializer;
import ru.daedal.util.init.config.ConfigValidator;

import java.util.Timer;
import java.util.TimerTask;

public class BotApplication {
    private static BotConfig config;

    public static void main(String[] args) {
        // Start profiling
        long start = System.currentTimeMillis();

        // Creating "application.properties" in bot working directory.
        config = new ConfigInitializer().createConfigIfNotExists();

        // Creating "attachments" directory in bot working directory.
        new AttachmentsInitializer().createAttachmentsDirectoryIfNotExists();

        printWelcomeMessage();

        // Config validation
        ConfigValidator configValidator = new ConfigValidator(config);
        configValidator.validate();

        // Run repeating message sending task
        BotLogger.logInfo("Executing message sending repeating task...");
        TimerTask timerTask = new MessageSendTask(new MessageSender(config), config);
        Timer timer = new Timer("MessageSendTask");

        if (config.isMessageOnStartEnabled()) {
            timer.scheduleAtFixedRate(timerTask, 0L, config.getPeriod() * 1000L);
        }
        else {
            timer.scheduleAtFixedRate(timerTask, config.getPeriod() * 1000L, config.getPeriod() * 1000L);
        }
        BotLogger.logInfo(ConsoleColor.ANSI_GREEN + "OK!");

        long end = System.currentTimeMillis();
        BotLogger.logInfo("Bot started for " + (end-start) + " ms!");
    }

    private static void printWelcomeMessage() {
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   Welcome to DiscordPostBot version 1.0");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡟⠁⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⢹⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   Developer: Daedal (TG: @mister_harvey)");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢸⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   GitHub: https://github.com/daedal-dev-ru/DiscordPostBot");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡇⠄⠄⠄⢠⣴⣾⣵⣶⣶⣾⣿⣦⡄⠄⠄⠄⢸⣿⣿⣿");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡇⠄⠄⢀⣾⣿⣿⢿⣿⣿⣿⣿⣿⣿⡄⠄⠄⢸⣿⣿⣿" + ConsoleColor.ANSI_YELLOW + "   Settings:");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡇⠄⠄⢸⣿⣿⣧⣀⣼⣿⣄⣠⣿⣿⣿⠄⠄⢸⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   Discord Token: " + ConsoleColor.ANSI_YELLOW + config.getDiscordToken().substring(0, 4) + "***************");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡇⠄⠄⠘⠻⢷⡯⠛⠛⠛⠛⢫⣿⠟⠛⠄⠄⢸⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   Message sending period: " + ConsoleColor.ANSI_YELLOW + config.getPeriod() + " sec.");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⡇⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢸⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   Message random delay: " + ConsoleColor.ANSI_YELLOW + config.getPeriodRandomDelay() + " sec.");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⣧⡀⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢡⣀⠄⠄⢸⣿⣿⣿" + ConsoleColor.ANSI_WHITE + "   Attachments: " + ConsoleColor.ANSI_YELLOW + config.getMessageAttachments().size() + " files");
        BotLogger.logInfo(ConsoleColor.ANSI_PURPLE + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿");
        for (int i = 0; i < 3; i++) {
            BotLogger.logInfo("");
        }
    }
}