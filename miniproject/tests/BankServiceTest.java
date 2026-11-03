import static org.junit.jupiter.api.Assertions.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BankServiceTest {
    @TempDir Path tempDir;

    private BankService service() {
        return new BankService(new Repository<>(), new AccountStore(), new Validator(),
            new TransactionLog(tempDir.resolve("transactions.log")),
            new StatePersister(tempDir.resolve("accounts.dat")));
    }

    @Test
    void depositIncreasesBalance() {
        BankService bank = service();
        Account account = bank.openAccount("Test User", 1000, "SAVINGS");
        bank.deposit(account.getAccountNumber(), 250);
        assertEquals(1250, account.getBalance());
    }

    @Test
    void withdrawalOverLimitThrows() {
        BankService bank = service();
        Account account = bank.openAccount("Test User", 1000, "SAVINGS");
        assertThrows(InsufficientFundsException.class,
            () -> bank.withdraw(account.getAccountNumber(), 2000));
    }

    @Test
    void concurrentDepositsKeepCorrectBalance() throws Exception {
        BankService bank = service();
        Account account = bank.openAccount("Test User", 0, "SAVINGS");
        int workers = 8, depositsEach = 500;
        ExecutorService executor = Executors.newFixedThreadPool(workers);
        try {
            List<Future<?>> futures = new ArrayList<>();
            for (int i = 0; i < workers; i++) {
                futures.add(executor.submit(() -> {
                    for (int j = 0; j < depositsEach; j++) {
                        bank.deposit(account.getAccountNumber(), 1);
                    }
                }));
            }
            for (Future<?> future : futures) future.get();
        } finally {
            executor.shutdownNow();
        }
        assertEquals((long) workers * depositsEach, account.getBalance());
    }
}
