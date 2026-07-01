package bot.commands.impl;

import bot.commands.SlashCommand;
import bot.levels.XPService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class LevelCommand implements SlashCommand {
    private final XPService xpService;

    public LevelCommand(XPService xpService) {
        this.xpService = xpService;
    }

    @Override
    public String getName() {
        return "level";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("level", "Show your current level and XP");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        long userId = event.getUser().getIdLong();
        event.reply("Level: " + xpService.getLevel(userId) + " | XP: " + xpService.getXp(userId)).setEphemeral(true).queue();
    }
}
