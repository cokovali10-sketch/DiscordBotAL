package bot.repositories;

import bot.database.DatabaseManager;
import bot.models.ModerationCase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SQLiteModerationRepository implements ModerationRepository {
    private final DatabaseManager databaseManager;

    public SQLiteModerationRepository(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        initialize();
    }

    private void initialize() {
        try (Connection connection = databaseManager.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS moderation_cases (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        guild_id INTEGER NOT NULL,
                        user_id INTEGER NOT NULL,
                        moderator_id INTEGER NOT NULL,
                        action TEXT NOT NULL,
                        reason TEXT NOT NULL,
                        created_at TEXT NOT NULL
                    )
                    """);
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to initialize moderation repository", e);
        }
    }

    @Override
    public void addCase(ModerationCase moderationCase) {
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("""
                     INSERT INTO moderation_cases (guild_id, user_id, moderator_id, action, reason, created_at)
                     VALUES (?, ?, ?, ?, ?, ?)
                     """)) {
            statement.setLong(1, moderationCase.guildId());
            statement.setLong(2, moderationCase.userId());
            statement.setLong(3, moderationCase.moderatorId());
            statement.setString(4, moderationCase.action());
            statement.setString(5, moderationCase.reason());
            statement.setString(6, moderationCase.createdAt().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to save moderation case", e);
        }
    }

    @Override
    public List<ModerationCase> findByGuild(long guildId) {
        List<ModerationCase> cases = new ArrayList<>();
        try (Connection connection = databaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM moderation_cases WHERE guild_id = ? ORDER BY id DESC")) {
            statement.setLong(1, guildId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cases.add(new ModerationCase(
                            resultSet.getLong("id"),
                            resultSet.getLong("guild_id"),
                            resultSet.getLong("user_id"),
                            resultSet.getLong("moderator_id"),
                            resultSet.getString("action"),
                            resultSet.getString("reason"),
                            Instant.parse(resultSet.getString("created_at"))
                    ));
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Unable to read moderation cases", e);
        }
        return cases;
    }
}
