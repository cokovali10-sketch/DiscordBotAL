package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import bot.config.BotConfig;
import bot.moderation.ModerationService;
import net.dv8tion.jda.api.entities.User;
import org.junit.jupiter.api.Test;

class ModerationServiceTest {
    @Test
    void buildReasonUsesFallbackWhenBlank() {
        ModerationService service = new ModerationService(new BotConfig("", 0L, 0L, null, null, null));

        assertEquals("No reason provided", service.buildReason("   "));
    }

    @Test
    void createConfirmationMessageIncludesButtons() {
        ModerationService service = new ModerationService(new BotConfig("", 0L, 0L, null, null, null));
        User targetUser = mock(User.class);
        when(targetUser.getAsMention()).thenReturn("<@1>");

        var message = service.createConfirmationMessage("Ban", targetUser);
        String content = message.build().getContent();

        assertTrue(content.contains("Confirm Ban"));
    }
}
