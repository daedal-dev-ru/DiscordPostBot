package ru.daedal.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

@Getter
@RequiredArgsConstructor
public class BotConfig {
    private String discordToken;
    private int period;
    private long channelId;
    private String message;
    private List<String> messageAttachmentsPaths;
    private final Properties properties;

    public void init() {
        discordToken = properties.getProperty("discord.token");
        period = Integer.parseInt(properties.getProperty("discord.message.sending.period"));
        channelId = Long.parseLong(properties.getProperty("discord.channel.id"));
        message = properties.getProperty("discord.message.content");
        messageAttachmentsPaths = Arrays.asList(properties.getProperty("discord.message.attachments").split(","));
    }
}
