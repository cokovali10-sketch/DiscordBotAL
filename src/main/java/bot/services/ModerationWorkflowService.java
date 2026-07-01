package bot.services;

import bot.config.BotConfig;
import bot.models.ModerationCase;
import bot.repositories.ModerationRepository;
import java.time.Instant;
import java.util.List;

public class ModerationWorkflowService {
    private final BotConfig config;
    private final ModerationRepository moderationRepository;

    public ModerationWorkflowService(BotConfig config, ModerationRepository moderationRepository) {
        this.config = config;
        this.moderationRepository = moderationRepository;
    }

    public void logCase(long userId, long moderatorId, String action, String reason) {
        moderationRepository.addCase(new ModerationCase(
                0L,
                config.guildId(),
                userId,
                moderatorId,
                action,
                reason,
                Instant.now()
        ));
    }

    public List<ModerationCase> history() {
        return moderationRepository.findByGuild(config.guildId());
    }
}
