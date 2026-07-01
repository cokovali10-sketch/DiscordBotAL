package bot.models;

import java.time.Instant;

public record ModerationCase(
        long id,
        long guildId,
        long userId,
        long moderatorId,
        String action,
        String reason,
        Instant createdAt
) {
}
