import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AccountStore {
    private final HashMap<String, Account> accounts = new HashMap<>();
    private final ArrayList<String> transactionHistory = new ArrayList<>();

    public synchronized void add(Account account) {
        if (account == null) throw new IllegalArgumentException("Account cannot be null.");
        accounts.put(account.getAccountNumber(), account);
    }
    public synchronized Account get(String accountNumber) { return accounts.get(accountNumber); }
    public synchronized ArrayList<Account> getAll() { return new ArrayList<>(accounts.values()); }
    public synchronized ArrayList<String> getTransactionHistory() { return new ArrayList<>(transactionHistory); }
    public synchronized void addTransaction(String entry) { transactionHistory.add(entry); }

    public synchronized ArrayList<Account> sortedByBalance() {
        ArrayList<Account> sorted = new ArrayList<>(accounts.values());
        sorted.sort(Comparator.comparingLong(Account::getBalance).reversed());
        return sorted;
    }

    public synchronized ConcurrentHashMap<String, Account> toConcurrentMap() {
        return new ConcurrentHashMap<>(accounts);
    }

    public synchronized void replaceAll(List<Account> newAccounts) {
        accounts.clear();
        for (Account account : newAccounts) add(account);
    }
}
