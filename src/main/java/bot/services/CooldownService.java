package bot.services;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownService {
    private final Clock clock;
    private final Map<String, Instant> lastUsed = new ConcurrentHashMap<>();

    public CooldownService() {
        this(Clock.systemUTC());
    }

    public CooldownService(Clock clock) {
        this.clock = clock;
    }

    public boolean tryConsume(String key, Duration duration) {
        Instant now = clock.instant();
        Instant previous = lastUsed.get(key);
        if (previous != null && !previous.plus(duration).isBefore(now)) {
            return false;
        }
        lastUsed.put(key, now);
        return true;
    }
}
