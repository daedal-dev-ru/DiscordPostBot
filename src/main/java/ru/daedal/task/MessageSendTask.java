package ru.daedal.task;

import lombok.RequiredArgsConstructor;
import ru.daedal.bot.MessageSender;
import ru.daedal.config.BotConfig;
import ru.daedal.util.BotLogger;

import java.io.File;
import java.util.List;
import java.util.TimerTask;

@RequiredArgsConstructor
public class MessageSendTask extends TimerTask {
    private final MessageSender messageSender;
    private final BotConfig config;

    @Override
    public void run() {
        List<File> attachments = config.getMessageAttachments();
        List<String> channelIds = config.getChannelIds();
        int randomDelay = (int) (Math.random() * config.getPeriodRandomDelay()); // seconds
        try {
            Thread.sleep(randomDelay * 1000L);
        } catch (InterruptedException e) {
            BotLogger.logError("MessageSendTask interrupted. Reason: " + e);
        }
        for (String channelId : channelIds) {
            messageSender.sendMessage(channelId, attachments);
        }
    }
}
