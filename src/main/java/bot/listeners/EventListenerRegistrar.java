package bot.listeners;

import bot.commands.CommandRegistry;
import bot.commands.impl.BalanceCommand;
import bot.commands.impl.ConfigCommand;
import bot.commands.impl.DailyCommand;
import bot.commands.impl.HelpCommand;
import bot.commands.impl.InfoCommand;
import bot.commands.impl.LevelCommand;
import bot.commands.impl.ModerateCommand;
import bot.commands.impl.PingCommand;
import bot.commands.impl.TicketCommand;
import bot.economy.EconomyService;
import bot.levels.XPService;
import bot.listeners.core.ReadyListener;
import bot.logging.ActionLogger;
import bot.moderation.ModerationService;
import bot.services.CooldownService;
import bot.tickets.TicketService;
import bot.welcome.WelcomeService;
import net.dv8tion.jda.api.JDA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListenerRegistrar {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventListenerRegistrar.class);

    private final JDA jda;

    public EventListenerRegistrar(JDA jda) {
        this.jda = jda;
    }

    public void registerAll() {
        jda.addEventListener(new ReadyListener());

        EconomyService economyService = new EconomyService();
        CooldownService cooldownService = new CooldownService();
        ModerationService moderationService = new ModerationService(null);
        TicketService ticketService = new TicketService();
        XPService xpService = new XPService();
        ActionLogger actionLogger = new ActionLogger();
        WelcomeService welcomeService = new WelcomeService();

        CommandRegistry commandRegistry = new CommandRegistry();
        commandRegistry.register(new PingCommand());
        commandRegistry.register(new HelpCommand());
        commandRegistry.register(new InfoCommand());
        commandRegistry.register(new BalanceCommand(economyService));
        commandRegistry.register(new DailyCommand(economyService, cooldownService));
        commandRegistry.register(new ModerateCommand(moderationService));
        commandRegistry.register(new TicketCommand(ticketService));
        commandRegistry.register(new LevelCommand(xpService));
        commandRegistry.register(new ConfigCommand());
        commandRegistry.registerAll(jda);
        jda.addEventListener(commandRegistry);

        LOGGER.info("Registered event listeners and slash commands.");
    }
}
