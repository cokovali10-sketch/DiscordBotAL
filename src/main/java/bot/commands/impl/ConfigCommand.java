package bot.commands.impl;

import bot.commands.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class ConfigCommand implements SlashCommand {
    @Override
    public String getName() {
        return "config";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("config", "Show configuration info");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.reply("Configuration loaded from config.json and ready for extension.").setEphemeral(true).queue();
    }
}
