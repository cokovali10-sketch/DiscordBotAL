package bot.commands.impl;

import bot.commands.SlashCommand;
import bot.tickets.TicketService;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public class TicketCommand implements SlashCommand {
    private final TicketService ticketService;

    public TicketCommand(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Override
    public String getName() {
        return "ticket";
    }

    @Override
    public CommandData getCommandData() {
        return Commands.slash("ticket", "Create a support ticket");
    }

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        ticketService.createTicket(event.getMember(), null);
        event.reply("Your support ticket request has been queued.").setEphemeral(true).queue();
    }
}
