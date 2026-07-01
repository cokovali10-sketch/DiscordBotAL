package bot.commands.impl;

import bot.commands.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class HelpCommand implements SlashCommand {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("help", "Show available bot commands");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply("Available commands: /ping, /help, /info").setEphemeral(true).queue();
    }
}
