package bot.logging;

import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ActionLogger {
    private static final Logger LOGGER = LoggerFactory.getLogger(ActionLogger.class);

    public void log(GuildMessageChannel channel, String message) {
        if (channel == null) {
            LOGGER.info(message);
            return;
        }
        channel.sendMessage(message).queue();
    }
}
