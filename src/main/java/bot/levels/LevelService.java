package bot.levels;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LevelService {
    private final Map<Long, UserProgress> progressMap = new ConcurrentHashMap<>();

    public UserProgress getOrCreate(long userId) {
        return progressMap.computeIfAbsent(userId, ignored -> new UserProgress(userId, 0, 1));
    }

    public void awardXp(long userId, int xp) {
        UserProgress current = getOrCreate(userId);
        int newXp = current.xp() + xp;
        int newLevel = 1 + (newXp / 100);
        progressMap.put(userId, new UserProgress(userId, newXp, newLevel));
    }

    public record UserProgress(long userId, int xp, int level) {
    }
}
