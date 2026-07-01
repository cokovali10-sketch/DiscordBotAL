package bot.tickets;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonInteraction;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

public class TicketService {
    private final List<TicketRecord> tickets = new ArrayList<>();

    public TicketRecord createTicket(Member member, Category category) {
        long ownerId = member != null ? member.getIdLong() : 0L;
        String name = "ticket-" + UUID.randomUUID().toString().substring(0, 6);
        TicketRecord ticket = new TicketRecord(UUID.randomUUID(), ownerId, name, Instant.now(), false, category != null ? category.getIdLong() : 0L);
        tickets.add(ticket);
        return ticket;
    }

    public boolean closeTicket(String ticketId) {
        return tickets.removeIf(ticket -> ticket.id().toString().equals(ticketId));
    }

    public void sendTranscript(TextChannel channel, String content) {
        channel.sendMessage(content).queue();
    }

    public MessageCreateBuilder createTicketPanel() {
        return new MessageCreateBuilder()
                .setContent("Create a support ticket")
                .setActionRow(Button.primary("ticket:create", "Open Ticket"));
    }

    public List<TicketRecord> getAllTickets() {
        return new ArrayList<>(tickets);
    }

    public void handleButton(ButtonInteraction interaction) {
        if (interaction.getButton().getId() != null && interaction.getButton().getId().startsWith("ticket:")) {
            interaction.reply("Ticket system is ready.").setEphemeral(true).queue();
        }
    }

    public record TicketRecord(UUID id, long ownerId, String name, Instant createdAt, boolean closed, long categoryId) {
    }
}
