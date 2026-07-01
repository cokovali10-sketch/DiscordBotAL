package bot;

import bot.config.BotConfig;
import bot.database.DatabaseManager;
import bot.listeners.EventListenerRegistrar;
import bot.services.BotLifecycleService;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class DiscordBotApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(DiscordBotApplication.class);

    private DiscordBotApplication() {
    }

    public static void main(String[] args) {
        try {
            BotConfig config = BotConfig.load();
            DatabaseManager databaseManager = new DatabaseManager(config);
            databaseManager.initialize();

            BotLifecycleService lifecycleService = new BotLifecycleService(config, databaseManager);
            lifecycleService.start();

            EventListenerRegistrar registrar = new EventListenerRegistrar(lifecycleService.getJda());
            registrar.registerAll();

            LOGGER.info("Discord bot started successfully.");
        } catch (IOException e) {
            LOGGER.error("Failed to load configuration", e);
            System.exit(1);
        } catch (Exception e) {
            LOGGER.error("Bot startup failed", e);
            System.exit(1);
        }
    }
}
