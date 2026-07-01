package bot.services;

import bot.models.Ticket;
import bot.repositories.TicketRepository;
import java.time.Instant;
import java.util.List;

public class TicketWorkflowService {
    private final TicketRepository ticketRepository;

    public TicketWorkflowService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public void create(long guildId, long creatorId, long channelId) {
        ticketRepository.save(new Ticket(0L, guildId, creatorId, channelId, "OPEN", Instant.now(), null));
    }

    public List<Ticket> list(long guildId) {
        return ticketRepository.findByGuild(guildId);
    }

    public void close(long ticketId) {
        ticketRepository.updateStatus(ticketId, "CLOSED");
    }
}
