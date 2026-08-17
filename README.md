<div align="center">

# 🎮 Discord Bot AL

**Modern Java Discord bot** — economy, levels, tickets, moderation & music in one extensible framework

![Java](https://img.shields.io/badge/Java-21-E76F00?style=flat-square&logo=java)
![JDA](https://img.shields.io/badge/JDA-5.x-5865F2?style=flat-square&logo=discord)
![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A?style=flat-square&logo=gradle)
![SQLite](https://img.shields.io/badge/SQLite-003B57?style=flat-square&logo=sqlite)
![Tests](https://img.shields.io/badge/tests-%E2%9C%93%20passing-brightgreen?style=flat-square)

</div>

## ✨ Features

| Module | What it does |
|---|---|
| 💰 **Economy** | Balance, daily rewards, configurable currency commands |
| 📈 **Levels & XP** | Per-message XP, role-progression levels, `!level` leaderboard |
| 🎫 **Tickets** | Full ticket workflow (open / claim / close) with repositories |
| 🛡️ **Moderation** | Moderation cases, workflows, action logging |
| 🎵 **Music** | Music service for voice channels |
| 👋 **Welcome** | Custom join/leave greeting service |
| ⚙️ **Config** | Centralized bot configuration with cooldowns |
| 🔌 **Events** | Event publisher + registrar, clean listener architecture |

## 🧱 Architecture

```
bot/
├── commands/       # SlashCommand interface + CommandRegistry + impls
├── config/         # BotConfig (token, settings)
├── database/       # DatabaseManager
├── economy/        # EconomyService
├── events/         # BotEvent, GuildEventPublisher
├── levels/         # LevelService, XPService
├── listeners/      # EventListenerRegistrar + core listeners
├── logging/        # ActionLogger
├── models/         # ModerationCase, Ticket
├── moderation/     # ModerationService
├── music/          # MusicService
├── repositories/   # SQLite repositories (moderation, tickets)
├── services/       # lifecycle, cooldown, workflows
├── tickets/        # TicketService
└── welcome/        # WelcomeService
```

Clean separation of concerns: **commands → services → repositories → SQLite**, easy to extend with new slash commands.

## 🚀 Getting Started

```bash
git clone https://github.com/cokovali10-sketch/DiscordBotAL
cd DiscordBotAL
./gradlew build      # compile + run tests
```

Configure your token in `src/main/java/bot/config/BotConfig.java` (or via env), then:

```bash
./gradlew run
```

## ✅ Tests

Unit tests cover the core services:
- `EconomyServiceTest` — economy logic
- `ModerationServiceTest` — moderation workflow
- `TicketServiceTest` — ticket lifecycle
- `CooldownServiceTest` — cooldown enforcement

---

<div align="center"><i>Beautiful, scalable, and ready for production servers.</i></div>