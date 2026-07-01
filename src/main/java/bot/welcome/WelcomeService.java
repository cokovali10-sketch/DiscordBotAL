package bot.welcome;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.middleman.GuildMessageChannel;

public class WelcomeService {
    public void sendWelcome(Member member, GuildMessageChannel channel) {
        if (member == null || channel == null) {
            return;
        }
        channel.sendMessage("Welcome to the server, " + member.getAsMention() + "!").queue();
    }
}
