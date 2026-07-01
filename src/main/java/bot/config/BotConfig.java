package bot.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public record BotConfig(
        String token,
        long guildId,
        long ownerId,
        DatabaseConfig database,
        LoggingConfig logging,
        ModerationConfig moderation
) {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static BotConfig load() throws IOException {
        try (InputStream inputStream = BotConfig.class.getClassLoader().getResourceAsStream("config.json")) {
            Objects.requireNonNull(inputStream, "config.json not found on classpath");
            JsonNode root = OBJECT_MAPPER.readTree(inputStream);
            return new BotConfig(
                    root.path("token").asText(""),
                    root.path("guildId").asLong(0L),
                    root.path("ownerId").asLong(0L),
                    new DatabaseConfig(root.path("database").path("url").asText("jdbc:sqlite:bot.db")),
                    new LoggingConfig(root.path("logging").path("channelId").asLong(0L)),
                    new ModerationConfig(root.path("moderation").path("logChannelId").asLong(0L))
            );
        }
    }

    public record DatabaseConfig(String url) {
    }

    public record LoggingConfig(long channelId) {
    }

    public record ModerationConfig(long logChannelId) {
    }
}
