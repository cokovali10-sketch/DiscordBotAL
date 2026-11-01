<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0:0f172a,50:38bdf8,100:a78bfa&height=160&section=header&text=DiscordBotAL&fontSize=48&fontColor=ffffff&fontAlignY=38&animation=fadeIn" alt="header" />

</div>

<div align="center">

# 🤖 Discord Bot AL

**A full-featured Java 21 Discord bot with economy, moderation, and ticket systems**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?style=flat&logo=gradle&logoColor=white)](https://gradle.org/)
[![JDA](https://img.shields.io/badge/JDA-5-blue.svg)](https://github.com/DV8FromTheWorld/JDA)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](#license)

</div>

---

## Overview

Discord Bot AL is a production-ready Discord bot built in Java 21 using the Discord Java API (JDA 5). It features a complete slash-command ecosystem with economy, leveling, moderation, ticket management, and logging — all backed by SQLite for zero-config persistence.

## Features

### Economy System
- Daily rewards with cooldown tracking
- Balance management and transfers
- Item shop integration

### XP & Leveling
- Automatic XP gain per message
- Level-up notifications
- Configurable XP curves

### Ticket Workflow
- Create, assign, and close support tickets
- Staff routing and priority flags
- Full ticket history

### Moderation
- Ban, kick, mute, and warn commands
- Audit logging with configurable channels
- Auto-mod foundations

### Welcome & Logging
- Join/leave event handlers
- Channel activity logging
- Server statistics tracking

## Commands

| Command | Description |
|---------|-------------|
| `/daily` | Claim your daily reward |
| `/balance` | Check your coin balance |
| `/transfer` | Send coins to another user |
| `/level` | View your XP and level |
| `/ticket create` | Open a new support ticket |
| `/ticket close` | Close the current ticket |
| `/ban` | Ban a member |
| `/kick` | Kick a member |
| `/mute` | Mute a member |
| `/warn` | Issue a warning |

## Project Structure

```
DiscordBotAL/
├── src/main/java/bot/
│   ├── commands/       Slash command handlers
│   ├── listeners/      Event listeners
│   ├── economy/        Economy and daily rewards
│   ├── levels/         XP and leveling system
│   ├── tickets/        Ticket workflow
│   ├── moderation/     Moderation helpers
│   ├── logging/        Action logging
│   └── database/       SQLite bootstrap
├── src/main/resources/
│   └── config.json     Bot token and guild settings
├── build.gradle.kts
└── settings.gradle.kts
```

## Getting Started

### Prerequisites

- **Java 21** or later
- A [Discord Bot Token](https://discord.com/developers/applications)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/cokovali10-sketch/DiscordBotAL.git
   cd DiscordBotAL
   ```

2. Configure your bot token in `src/main/resources/config.json`:
   ```json
   {
     "token": "YOUR_BOT_TOKEN",
     "guildId": "YOUR_GUILD_ID"
   }
   ```

3. Build and run:
   ```bash
   # Run tests
   ./gradlew test

   # Start the bot
   ./gradlew run
   ```

4. Invite the bot to your server with the required permissions (Send Messages, Use Slash Commands, Manage Messages, Kick Members, Ban Members).

## Tech Stack

| Component       | Technology             |
| --------------- | ---------------------- |
| Language        | Java 21                |
| Build           | Gradle Kotlin DSL      |
| Discord API     | JDA 5                  |
| Database        | SQLite                 |
| Serialization   | Jackson                |
| Logging         | SLF4J + Logback        |
| Testing         | JUnit 5 + Mockito      |

## Contributing

Contributions are welcome! Please open an issue or pull request with a clear summary of changes.

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes
4. Push to the branch and open a Pull Request

## License

MIT
