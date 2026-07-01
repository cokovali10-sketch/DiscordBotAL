package bot.events;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class GuildEventPublisher {
    private final List<Consumer<BotEvent>> listeners = new ArrayList<>();

    public void subscribe(Consumer<BotEvent> listener) {
        listeners.add(listener);
    }

    public void publish(BotEvent event) {
        listeners.forEach(listener -> listener.accept(event));
    }
}
