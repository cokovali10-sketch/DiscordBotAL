package bot.moderation;

import bot.config.BotConfig;
import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

public class ModerationService {
    private final BotConfig config;

    public ModerationService(BotConfig config) {
        this.config = config;
    }

    public String buildReason(String reason) {
        return reason == null || reason.isBlank() ? "No reason provided" : reason.trim();
    }

    public boolean canModerate(Member actor, Member target) {
        if (actor == null || target == null) {
            return false;
        }
        return !actor.getId().equals(target.getId()) && actor.canInteract(target);
    }

    public MessageCreateBuilder createConfirmationMessage(String action, User targetUser) {
        return new MessageCreateBuilder()
                .setContent("Confirm " + action + " for " + targetUser.getAsMention() + "?")
                .setActionRow(
                        Button.success("confirm:" + action.toLowerCase(), "Confirm"),
                        Button.danger("cancel:" + action.toLowerCase(), "Cancel")
                );
    }

    public List<String> getRecentModerationActions() {
        return new ArrayList<>();
    }

    public void logAction(GuildMessageChannel channel, String message) {
        if (channel != null) {
            channel.sendMessage(message).queue();
        }
    }
}
