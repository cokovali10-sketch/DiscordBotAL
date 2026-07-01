package bot.commands.impl;

import bot.commands.SlashCommand;
import bot.economy.EconomyService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class BalanceCommand implements SlashCommand {
    private final EconomyService economyService;

    public BalanceCommand(EconomyService economyService) {
        this.economyService = economyService;
    }

    @Override
    public String getName() {
        return "balance";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("balance", "Show your balance");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        long userId = event.getUser().getIdLong();
        long balance = economyService.balance(userId);
        event.reply("Your balance is: " + balance).setEphemeral(true).queue();
    }
}
