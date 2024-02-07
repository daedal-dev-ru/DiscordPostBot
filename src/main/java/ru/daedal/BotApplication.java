package ru.daedal;

import ru.daedal.bot.ClientBot;
import ru.daedal.config.BotConfig;
import ru.daedal.task.SendMessageTask;
import ru.daedal.utils.BotLogger;
import ru.daedal.utils.ConsoleColor;
import ru.daedal.utils.init.AttachmentsInitializer;
import ru.daedal.utils.init.ConfigInitializer;

public class BotApplication {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        AttachmentsInitializer attachmentsInitializer = new AttachmentsInitializer();
        ConfigInitializer configInitializer = new ConfigInitializer();

        attachmentsInitializer.createAttachmentsDirectoryIfNotExists();
        BotConfig config = configInitializer.createConfigIfNotExists();

        if (config.getDiscordToken().equalsIgnoreCase("Enter your token here") || config.getDiscordToken().isEmpty()) {
            BotLogger.logError("You don't setup your discord token. Please, refer to github repo for instruction: https://github.com/daedal-dev-ru/DiscordPostBot");
            System.exit(130);
        }
        if (config.getMessage().isEmpty() && config.getMessageAttachmentsPaths().isEmpty()) {
            BotLogger.logError("Message can't be empty (Discord API restriction)");
            System.exit(130);
        }
        if (config.getChannelId() == 0) {
            BotLogger.logError("Channel id can't be empty. Please, refer to github repo for instruction: https://github.com/daedal-dev-ru/DiscordPostBot");
            System.exit(130);
        }
        BotLogger.logInfo(ConsoleColor.ANSI_GREEN + "OK!");

        BotLogger.logInfo("Initializing Discord Bot...");
        ClientBot bot = new ClientBot(config);
        BotLogger.logInfo(ConsoleColor.ANSI_GREEN + "OK!");

        Thread sendMessageTask = new SendMessageTask(bot, config);
        long end = System.currentTimeMillis();
        BotLogger.logInfo("Bot started for " + (end-start) + " ms!");

        sendMessageTask.start();
    }
}