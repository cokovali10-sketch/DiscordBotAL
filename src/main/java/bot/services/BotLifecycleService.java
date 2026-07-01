package bot.services;

import bot.config.BotConfig;
import bot.database.DatabaseManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotLifecycleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BotLifecycleService.class);

    private final BotConfig config;
    private final DatabaseManager databaseManager;
    private JDA jda;

    public BotLifecycleService(BotConfig config, DatabaseManager databaseManager) {
        this.config = config;
        this.databaseManager = databaseManager;
    }

    public void start() throws Exception {
        if (config.token().isBlank()) {
            throw new IllegalStateException("Bot token is not configured. Update src/main/resources/config.json.");
        }

        jda = JDABuilder.createDefault(config.token())
                .enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES)
                .setMemberCachePolicy(MemberCachePolicy.ALL)
                .build()
                .awaitReady();

        LOGGER.info("JDA session established for guild {}", config.guildId());
    }

    public JDA getJda() {
        return jda;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
}
