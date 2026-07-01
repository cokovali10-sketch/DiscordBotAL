package bot.economy;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EconomyService {
    private final Map<Long, EconomyAccount> accounts = new ConcurrentHashMap<>();

    public EconomyAccount getOrCreate(long userId) {
        return accounts.computeIfAbsent(userId, ignored -> new EconomyAccount(userId, 100L));
    }

    public long balance(long userId) {
        return getOrCreate(userId).balance();
    }

    public void deposit(long userId, long amount) {
        EconomyAccount account = getOrCreate(userId);
        accounts.put(userId, new EconomyAccount(userId, account.balance() + amount));
    }

    public void withdraw(long userId, long amount) {
        EconomyAccount account = getOrCreate(userId);
        if (account.balance() >= amount) {
            accounts.put(userId, new EconomyAccount(userId, account.balance() - amount));
        }
    }

    public Map<Long, EconomyAccount> snapshot() {
        return new HashMap<>(accounts);
    }

    public record EconomyAccount(long userId, long balance) {
    }
}
