package bot.commands.impl;

import bot.commands.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class InfoCommand implements SlashCommand {
    @Override
    public String getName() {
        return "info";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("info", "Show bot information");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        JDA jda = event.getJDA();
        String response = String.format(
                "Bot: %s\nGuilds: %d\nUsers: %d",
                jda.getSelfUser().getName(),
                jda.getGuildCache().size(),
                jda.getUserCache().size()
        );
        event.reply(response).setEphemeral(true).queue();
    }
}
