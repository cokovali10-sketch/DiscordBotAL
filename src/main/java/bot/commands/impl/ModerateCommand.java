package bot.commands.impl;

import bot.commands.SlashCommand;
import bot.moderation.ModerationService;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public class ModerateCommand implements SlashCommand {
    private final ModerationService moderationService;

    public ModerateCommand(ModerationService moderationService) {
        this.moderationService = moderationService;
    }

    @Override
    public String getName() {
        return "ban";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("ban", "Ban a member")
                .addOptions(
                        new OptionData(OptionType.USER, "user", "Target user", true),
                        new OptionData(OptionType.STRING, "reason", "Reason", false)
                );
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        Member target = event.getOption("user") == null ? null : event.getOption("user").getAsMember();
        if (target == null) {
            event.reply("User not found.").setEphemeral(true).queue();
            return;
        }

        Member actor = event.getMember();
        if (actor == null || !moderationService.canModerate(actor, target)) {
            event.reply("You cannot ban that member.").setEphemeral(true).queue();
            return;
        }

        String reason = moderationService.buildReason(event.getOption("reason") == null ? null : event.getOption("reason").getAsString());
        event.reply("Ban requested for " + target.getEffectiveName() + " with reason: " + reason)
                .setEphemeral(true)
                .queue();
    }
}
