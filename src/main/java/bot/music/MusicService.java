package bot.music;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class MusicService {
    private final Deque<String> queue = new ArrayDeque<>();

    public void enqueue(String track) {
        queue.addLast(track);
    }

    public String dequeue() {
        return queue.pollFirst();
    }

    public List<String> getQueueSnapshot() {
        return new ArrayList<>(queue);
    }

    public void clear() {
        queue.clear();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
