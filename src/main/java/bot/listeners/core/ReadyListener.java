package bot.listeners.core;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadyListener extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadyListener.class);

    @Override
    public void onReady(ReadyEvent event) {
        LOGGER.info("Bot is ready as {}", event.getJDA().getSelfUser().getAsTag());
    }
}
