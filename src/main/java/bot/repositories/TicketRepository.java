package bot.repositories;

import bot.models.Ticket;
import java.util.List;

public interface TicketRepository {
    void save(Ticket ticket);

    List<Ticket> findByGuild(long guildId);

    void updateStatus(long ticketId, String status);
}
