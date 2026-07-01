package bot.commands.impl;

import bot.commands.SlashCommand;
import bot.economy.EconomyService;
import bot.services.CooldownService;
import java.time.Duration;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class DailyCommand implements SlashCommand {
    private final EconomyService economyService;
    private final CooldownService cooldownService;

    public DailyCommand(EconomyService economyService, CooldownService cooldownService) {
        this.economyService = economyService;
        this.cooldownService = cooldownService;
    }

    @Override
    public String getName() {
        return "daily";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("daily", "Claim your daily reward");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        String key = "daily:" + event.getUser().getId();
        if (!cooldownService.tryConsume(key, Duration.ofHours(24))) {
            event.reply("You already claimed your daily reward. Try again later.").setEphemeral(true).queue();
            return;
        }

        economyService.deposit(event.getUser().getIdLong(), 100L);
        event.reply("You received 100 coins for your daily reward!").setEphemeral(true).queue();
    }
}
