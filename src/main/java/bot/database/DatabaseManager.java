package bot.database;

import bot.config.BotConfig;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseManager.class);

    private final BotConfig config;
    private DataSource dataSource;

    public DatabaseManager(BotConfig config) {
        this.config = config;
    }

    public void initialize() throws SQLException {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(config.database().url());
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(2);
        hikariConfig.setConnectionTestQuery("SELECT 1");
        hikariConfig.setPoolName("discord-bot-pool");
        dataSource = new HikariDataSource(hikariConfig);

        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            statement.executeUpdate("""
                    CREATE TABLE IF NOT EXISTS guild_settings (
                        guild_id TEXT PRIMARY KEY,
                        prefix TEXT DEFAULT "!"
                    )
                    """);
            LOGGER.info("Database initialized successfully.");
        }
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
