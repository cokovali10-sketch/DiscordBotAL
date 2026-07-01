package bot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import bot.economy.EconomyService;
import org.junit.jupiter.api.Test;

class EconomyServiceTest {
    @Test
    void depositAndWithdrawUpdateBalance() {
        EconomyService service = new EconomyService();

        service.deposit(42L, 250L);
        assertEquals(350L, service.balance(42L));

        service.withdraw(42L, 100L);
        assertEquals(250L, service.balance(42L));
    }
}
