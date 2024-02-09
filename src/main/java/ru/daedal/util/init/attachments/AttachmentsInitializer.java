package ru.daedal.util.init.attachments;

import ru.daedal.util.BotLogger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

public class AttachmentsInitializer {
    public void createAttachmentsDirectoryIfNotExists() {
        File attachmentsDirectory = new File("attachments");
        if (!attachmentsDirectory.exists()) {
            if (attachmentsDirectory.mkdir()) {
                try {
                    File exampleImage = new File("attachments/example.jpg");
                    InputStream link = (getClass().getClassLoader().getResourceAsStream("attachments/example.jpg"));
                    Files.copy(Objects.requireNonNull(link), exampleImage.getAbsoluteFile().toPath());
                } catch (IOException e) {
                    BotLogger.logError("An error occurred during initializing attachments.");
                }
                BotLogger.logInfo("Directory created: " + attachmentsDirectory.getName());
            }
        }
    }
}
