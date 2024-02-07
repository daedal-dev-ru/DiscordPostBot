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
import ru.daedal.utils.BotLogger;
import ru.daedal.config.BotConfig;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ClientBot {
    private final BotConfig config;
    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void sendMessage(String msg) {
        try {
            List<File> attachments = new ArrayList<>();
            for (String path : config.getMessageAttachmentsPaths()) {
                attachments.add(new File(path));
            }
            HttpPost post = new HttpPost("https://discord.com/api/channels/{channel.id}/messages".replace("{channel.id}", String.valueOf(config.getChannelId())));
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(StandardCharsets.UTF_8);
            post.addHeader("Authorization", config.getDiscordToken());
            builder.addTextBody("content", msg, ContentType.TEXT_PLAIN);

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
            BotLogger.logInfo("Message successfully sent. Content:");
            BotLogger.logInfo("\n" + config.getMessage());
            BotLogger.logInfo("Response: " + response.toString());
            if (attachmentsCount != 0) {
                BotLogger.logInfo(
                        "[+ {attachments.count} attachments]"
                                .replace("{attachments.count}", String.valueOf(attachmentsCount)));
            }
        } catch (IOException e) {
            BotLogger.logError("An error occurred when sending message.");
            e.printStackTrace();
        }
    }

    private static double getFileSizeMegaBytes(File file) {
        return (double) file.length() / (1024 * 1024);
    }
}
