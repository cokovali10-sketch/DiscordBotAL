package bot.repositories;

import bot.models.ModerationCase;
import java.util.List;

public interface ModerationRepository {
    void addCase(ModerationCase moderationCase);

    List<ModerationCase> findByGuild(long guildId);
}
