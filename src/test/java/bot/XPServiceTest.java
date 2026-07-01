package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bot.levels.XPService;
import org.junit.jupiter.api.Test;

class XPServiceTest {
    @Test
    void xpAndLevelAreCalculatedCorrectly() {
        XPService service = new XPService();

        assertEquals(150, service.addXp(10L, 150));
        assertEquals(2, service.getLevel(10L));
    }
}
