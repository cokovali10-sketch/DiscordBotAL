package bot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import bot.services.CooldownService;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;

class CooldownServiceTest {
    @Test
    void repeatedCommandWithinCooldownIsBlocked() {
        Clock clock = new FixedClock(Instant.parse("2026-01-01T00:00:00Z"));
        CooldownService service = new CooldownService(clock);

        assertTrue(service.tryConsume("ping", Duration.ofSeconds(10)));
        assertFalse(service.tryConsume("ping", Duration.ofSeconds(10)));
    }

    @Test
    void commandCanBeUsedAgainAfterCooldownExpires() {
        FixedClock clock = new FixedClock(Instant.parse("2026-01-01T00:00:00Z"));
        CooldownService service = new CooldownService(clock);

        assertTrue(service.tryConsume("ping", Duration.ofSeconds(10)));
        clock.setInstant(Instant.parse("2026-01-01T00:00:11Z"));
        assertTrue(service.tryConsume("ping", Duration.ofSeconds(10)));
    }

    private static final class FixedClock extends Clock {
        private Instant instant;

        private FixedClock(Instant instant) {
            this.instant = instant;
        }

        @Override
        public ZoneOffset getZone() {
            return ZoneOffset.UTC;
        }

        @Override
        public Clock withZone(java.time.ZoneId zone) {
            return this;
        }

        @Override
        public Instant instant() {
            return instant;
        }

        public void setInstant(Instant instant) {
            this.instant = instant;
        }
    }
}
