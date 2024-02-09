package ru.daedal.bot;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import ru.daedal.exception.FileSizeException;
import ru.daedal.util.BotLogger;
import ru.daedal.config.BotConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@RequiredArgsConstructor
public class MessageSender {
    private final BotConfig config;

    public void sendMessage(String channelId, List<File> attachments) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost("https://discord.com/api/channels/{channel.id}/messages"
                    .replace("{channel.id}", channelId));

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(StandardCharsets.UTF_8);
            post.addHeader("Authorization", config.getDiscordToken());
            builder.addTextBody("content", config.getMessage(), ContentType.TEXT_PLAIN);

            int attachmentsCount = 0;
            if (!attachments.isEmpty()) {
                for (File file : attachments) {
                    if (!file.exists()) {
                        BotLogger.logError("File " + file.getName() + " not found!");
                        continue;
                    }
                    if (!(getFileSizeMegaBytes(file) < 25.0)) {
                        throw new FileSizeException();
                    }
                    builder.addBinaryBody("file",
                            Files.newInputStream(file.toPath()),
                            ContentType.APPLICATION_FORM_URLENCODED,
                            file.getName());
                    attachmentsCount += 1;
                }
            }

            HttpEntity multipart = builder.build();
            post.setEntity(multipart);
            HttpResponse response = httpClient.execute(post);
            httpClient.close();

            BotLogger.logInfo("Message successfully sent to channel {channel.id}. Content:"
                    .replace("{channel.id}", channelId));
            BotLogger.logInfo("\n" + config.getMessage());

            if (attachmentsCount != 0) {
                BotLogger.logInfo(
                        "[+ {attachments.count} attachments]"
                                .replace("{attachments.count}", String.valueOf(attachmentsCount)));
            }

            BotLogger.logInfo("Response: " + response.toString());
        } catch (IOException e) {
            BotLogger.logError("An error occurred when sending message.");
            e.printStackTrace();
        }
    }

    private static double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }
}
