package ru.daedal.util.init.config;

import lombok.RequiredArgsConstructor;
import ru.daedal.config.BotConfig;
import ru.daedal.util.BotLogger;

@RequiredArgsConstructor
public class ConfigValidator {

    private final BotConfig config;

    public void validate() {
        if (config.getDiscordToken().isEmpty() ||
                config.getDiscordToken().equalsIgnoreCase("Enter your token here")) {
            BotLogger.logError("You don't setup your discord token. Please, refer to github repo for instruction: https://github.com/daedal-dev-ru/DiscordPostBot");
            System.exit(130);
        }

        if (config.getMessage().isEmpty() && config.getMessageAttachments().isEmpty()) {
            BotLogger.logError("Message can't be empty (Discord API restriction)");
            System.exit(130);
        }

        if (config.getMessage().length() > 2000) {
            BotLogger.logError("Message length can't be greater than 2000 symbols. (Discord API restriction)");
            System.exit(130);
        }

        if (config.getPeriod() <= 0) {
            BotLogger.logError("Message sending period can't be less than or equal to 0");
            System.exit(130);
        }

        if (config.getChannelIds().isEmpty()
                || (config.getChannelIds().get(0).equals("0000000000000000000")
                && config.getChannelIds().get(1).equals("1111111111111111111"))) {
            BotLogger.logError("Channel id can't be empty. Please, refer to github repo for instruction: https://github.com/daedal-dev-ru/DiscordPostBot");
            System.exit(130);
        }
    }
}
