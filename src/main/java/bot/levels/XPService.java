package bot.levels;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class XPService {
    private final Map<Long, Integer> xpByUser = new ConcurrentHashMap<>();

    public int addXp(long userId, int amount) {
        int updated = xpByUser.getOrDefault(userId, 0) + amount;
        xpByUser.put(userId, updated);
        return updated;
    }

    public int getXp(long userId) {
        return xpByUser.getOrDefault(userId, 0);
    }

    public int getLevel(long userId) {
        return getXp(userId) / 100 + 1;
    }
}
