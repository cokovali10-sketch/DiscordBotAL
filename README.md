# Discord Bot AL

A polished Java 21 Discord bot project built with JDA, Gradle Kotlin DSL, SQLite, Jackson, SLF4J, and Logback.

## Features

- Slash commands
- Economy and daily rewards
- XP and level system
- Ticket workflow
- Moderation helpers
- Welcome and logging service foundations
- SQLite-backed persistence

## Tech Stack

- Java 21
- Gradle Kotlin DSL
- JDA 5
- SQLite
- Jackson
- SLF4J + Logback
- JUnit 5 + Mockito

## Quick Start

1. Install Java 21.
2. Clone the repository.
3. Update [src/main/resources/config.json](src/main/resources/config.json) with your bot token and guild settings.
4. Run:
   - `./gradlew test`
   - `./gradlew run`
5. Invite the bot to your server with the required permissions.

## Project Structure

- `bot.commands` — slash commands
- `bot.listeners` — event listeners
- `bot.moderation` — moderation helpers
- `bot.tickets` — ticket workflow
- `bot.economy` — economy system
- `bot.levels` — XP and levels
- `bot.logging` — action logging
- `bot.database` — SQLite bootstrap

## Contributing

Contributions are welcome. Please open an issue or pull request with a clear summary.

## License

MIT
