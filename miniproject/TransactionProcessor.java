import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.ArrayList;

public class TransactionProcessor {
    private final ConcurrentHashMap<String, Account> accounts;

    public TransactionProcessor(ConcurrentHashMap<String, Account> accounts) {
        this.accounts = accounts;
    }

    public void processBatch(List<String> commands) {
        ExecutorService pool = Executors.newFixedThreadPool(Math.max(2, Math.min(commands.size(), 4)));
        ArrayList<Future<?>> futures = new ArrayList<>();
        try {
            for (String line : commands) {
                futures.add(pool.submit(() -> processOne(line)));
            }
            for (Future<?> future : futures) {
                try {
                    future.get();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new BankException("Batch processing interrupted.", e);
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof RuntimeException runtimeException) throw runtimeException;
                    throw new BankException("Batch transaction failed.", cause);
                }
            }
        } finally {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    throw new BankException("Batch processing timed out.");
                }
            } catch (InterruptedException e) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
                throw new BankException("Batch processing interrupted.", e);
            }
        }
    }

    private void processOne(String line) {
        Command command = CommandParser.parse(line);
        Account account = accounts.get(command.accountNumber());
        if (account == null) throw new AccountNotFoundException("Account not found: " + command.accountNumber());
        switch (command.type()) {
            case DEPOSIT -> account.deposit(command.amount());
            case WITHDRAW -> {
                if (!account.withdraw(command.amount()))
                    throw new InsufficientFundsException("Withdrawal not allowed for " + account.getAccountNumber());
            }
            case TRANSFER -> throw new IllegalArgumentException("Batch TRANSFER requires two account numbers; use the service transfer method.");
        }
    }
}
