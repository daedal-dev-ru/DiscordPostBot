package ru.daedal.utils.init;

import ru.daedal.utils.BotLogger;

import java.io.File;

public class AttachmentsInitializer {
    public void createAttachmentsDirectoryIfNotExists() {
        File attachmentsDirectory = new File("attachments");
        if (!attachmentsDirectory.exists()) {
            if (attachmentsDirectory.mkdir()) {
                BotLogger.logInfo("Directory created: " + attachmentsDirectory.getName());
            }
        }
    }
}
