package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bot.tickets.TicketService;
import org.junit.jupiter.api.Test;

class TicketServiceTest {
    @Test
    void ticketCreationStoresRecord() {
        TicketService service = new TicketService();
        var ticket = service.createTicket(null, null);

        assertEquals(1, service.getAllTickets().size());
        assertEquals(ticket.id(), service.getAllTickets().getFirst().id());
    }
}
