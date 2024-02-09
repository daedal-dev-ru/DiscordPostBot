package ru.daedal.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Getter
@RequiredArgsConstructor
public class BotConfig {
    private String discordToken;
    private int period;
    private boolean messageOnStartEnabled;
    private int periodRandomDelay;
    private final List<String> channelIds = new ArrayList<>();
    private String message;
    private final List<File> messageAttachments = new ArrayList<>();
    private final Properties properties;

    public void init() {
        discordToken = properties.getProperty("discord.token");
        period = Integer.parseInt(properties.getProperty("discord.message.sending.period"));
        messageOnStartEnabled = Boolean.getBoolean(properties.getProperty("discord.message.send.on.start"));
        periodRandomDelay = Integer.parseInt(properties.getProperty("discord.message.sending.period.random.delay"));
        channelIds.addAll(Arrays.asList(properties.getProperty("discord.channels.id").split(",")));
        message = properties.getProperty("discord.message.content");
        for (String path : properties.getProperty("discord.message.attachments").split(",")) {
            messageAttachments.add(new File(path));
        }
    }
}
