package bot.commands;

import java.util.ArrayList;
import java.util.List;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommandRegistry extends ListenerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandRegistry.class);

    private final List<SlashCommand> commands = new ArrayList<>();

    public CommandRegistry register(SlashCommand command) {
        commands.add(command);
        return this;
    }

    public void registerAll(JDA jda) {
        List<CommandData> data = commands.stream().map(SlashCommand::getCommandData).toList();
        jda.updateCommands().addCommands(data).queue(success -> LOGGER.info("Registered {} slash commands", data.size()));
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        commands.stream()
                .filter(command -> command.getName().equalsIgnoreCase(event.getName()))
                .findFirst()
                .ifPresent(command -> command.handle(event));
    }
}
