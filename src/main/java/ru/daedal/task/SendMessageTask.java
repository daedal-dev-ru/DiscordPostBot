package ru.daedal.task;

import ru.daedal.exception.MessageTooLongException;
import ru.daedal.utils.BotLogger;
import ru.daedal.bot.ClientBot;
import ru.daedal.config.BotConfig;

public class SendMessageTask extends Thread {
    private final ClientBot clientBot;
    private final BotConfig config;

    public SendMessageTask(ClientBot clientBot, BotConfig config) {
       this.clientBot = clientBot;
       this.config = config;
    }

    @Override
    public void start() {
        while (true) {
            if (config.getMessage().length() > 2000) {
                throw new MessageTooLongException();
            }
            try {
                clientBot.sendMessage(config.getMessage());
                Thread.sleep(config.getPeriod() * 1000L);
            } catch (InterruptedException e) {
                BotLogger.logError("An error occurred in SendMessageTask!");
                BotLogger.logError(e.toString());
            }
        }
    }
}
