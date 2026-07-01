package bot.repositories;

import bot.database.DatabaseManager;
import bot.models.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SQLiteTicketRepository implements TicketRepository {
    private final DatabaseManager databaseManager;

    public SQLiteTicketRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        initialize();
    }

    private void initialize() {
        try (Connection connection = databaseManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS tickets (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        guild_id INTEGER NOT NULL,
                        creator_id INTEGER NOT NULL,
                        channel_id INTEGER NOT NULL,
                        status TEXT NOT NULL,
                        created_at TEXT NOT NULL,
                        closed_at TEXT
                    )
                    """);
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to initialize ticket repository", e);
        }
    }

    @Override
    public void save(Ticket ticket) {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                     INSERT INTO tickets (guild_id, creator_id, channel_id, status, created_at, closed_at)
                     VALUES (?, ?, ?, ?, ?, ?)
                     """)) {
            statement.setLong(1, ticket.guildId());
            statement.setLong(2, ticket.creatorId());
            statement.setLong(3, ticket.channelId());
            statement.setString(4, ticket.status());
            statement.setString(5, ticket.createdAt().toString());
            statement.setString(6, ticket.closedAt() == null ? null : ticket.closedAt().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to save ticket", e);
        }
    }

    @Override
    public List<Ticket> findByGuild(long guildId) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM tickets WHERE guild_id = ? ORDER BY id DESC")) {
            statement.setLong(1, guildId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tickets.add(new Ticket(
                            resultSet.getLong("id"),
                            resultSet.getLong("guild_id"),
                            resultSet.getLong("creator_id"),
                            resultSet.getLong("channel_id"),
                            resultSet.getString("status"),
                            Instant.parse(resultSet.getString("created_at")),
                            resultSet.getString("closed_at") == null ? null : Instant.parse(resultSet.getString("closed_at"))
                    ));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to read tickets", e);
        }
        return tickets;
    }

    @Override
    public void updateStatus(long ticketId, String status) {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("UPDATE tickets SET status = ? WHERE id = ?")) {
            statement.setString(1, status);
            statement.setLong(2, ticketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to update ticket status", e);
        }
    }
}
