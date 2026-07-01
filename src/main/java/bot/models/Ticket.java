package bot.models;

import java.time.Instant;

public record Ticket(
        long id,
        long guildId,
        long creatorId,
        long channelId,
        String status,
        Instant createdAt,
        Instant closedAt
) {
}
