import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BankService {
    private final Repository<Account> repository;
    private final AccountStore store;
    private final Validator validator;
    private final TransactionLog log;
    private final ReportGenerator reportGenerator;
    private final StatePersister persister;

    public BankService() { this(new Repository<>(), new AccountStore(), new Validator(), new TransactionLog(), new StatePersister()); }
    public BankService(Repository<Account> repository, AccountStore store, Validator validator,
                       TransactionLog log, StatePersister persister) {
        this.repository = repository; this.store = store; this.validator = validator;
        this.log = log; this.reportGenerator = new ReportGenerator(log); this.persister = persister;
        loadOnStartup();
    }

    private void loadOnStartup() {
        Account[] saved = persister.load();
        for (Account account : saved) { Account.updateCounterFromAccountNumber(account.getAccountNumber()); repository.save(account); store.add(account); }
    }

    public synchronized Account openAccount(String ownerName, long openingBalance, String type) {
        Validator.requireValidName(ownerName);
        if (openingBalance < 0) throw new InvalidAmountException("Opening balance cannot be negative.");
        Account account;
        switch (type.toUpperCase()) {
            case "SAVINGS" -> account = new SavingsAccount(ownerName, openingBalance, 0);
            case "CURRENT" -> account = new CurrentAccount(ownerName, openingBalance, 0);
            case "FIXED", "FIXED_DEPOSIT" -> account = new FixedDepositAccount(ownerName, openingBalance);
            default -> throw new IllegalArgumentException("Type must be SAVINGS, CURRENT or FIXED.");
        }
        repository.save(account); store.add(account);
        log.append("OPEN " + account.getAccountNumber() + " " + openingBalance);
        return account;
    }

    public synchronized void deposit(String accountNumber, long amount) {
        Validator.requirePositiveAmount(amount);
        Account account = requireAccount(accountNumber);
        account.deposit(amount);
        log.append("DEPOSIT " + accountNumber + " " + amount);
    }

    public synchronized void withdraw(String accountNumber, long amount) {
        Validator.requirePositiveAmount(amount);
        Account account = requireAccount(accountNumber);
        if (!account.withdraw(amount)) throw new InsufficientFundsException("Withdrawal not allowed. Check balance/minimum balance/account type.");
        log.append("WITHDRAW " + accountNumber + " " + amount);
    }

    public synchronized void transfer(String fromNumber, String toNumber, long amount) {
        Validator.requirePositiveAmount(amount);
        if (fromNumber.equals(toNumber)) throw new IllegalArgumentException("Source and destination must differ.");
        Account from = requireAccount(fromNumber), to = requireAccount(toNumber);
        synchronized (from) {
            synchronized (to) {
                if (!from.withdraw(amount)) throw new InsufficientFundsException("Insufficient funds or withdrawal not allowed.");
                try { to.deposit(amount); }
                catch (RuntimeException e) { from.deposit(amount); throw e; }
            }
        }
        log.append("WITHDRAW " + fromNumber + " " + amount);
        log.append("DEPOSIT " + toNumber + " " + amount);
    }

    public String statement(String accountNumber) { return StatementFormatter.buildStatement(requireAccount(accountNumber)); }
    public ArrayList<Account> listAccounts() { return store.sortedByBalance(); }
    public String runReport() { return reportGenerator.generate(); }

    public void processBatch(List<String> commands) {
        ConcurrentHashMap<String, Account> shared = store.toConcurrentMap();
        new TransactionProcessor(shared).processBatch(commands);
        for (String line : commands) {
            try {
                Command c = CommandParser.parse(line);
                if (c.type() == TransactionType.DEPOSIT || c.type() == TransactionType.WITHDRAW) log.append(line.trim().toUpperCase());
            } catch (RuntimeException ignored) { /* processor reports command failures only through worker */ }
        }
    }

    public synchronized void saveOnExit() {
        List<Account> accounts = repository.findAll();
        persister.save(accounts.toArray(new Account[0]));
    }

    public Account findById(String id) { return repository.findById(id); }
    public AccountStore getStore() { return store; }
    private Account requireAccount(String number) {
        Account account = repository.findById(number);
        if (account == null) throw new AccountNotFoundException("Account not found: " + number);
        if (!account.isActive()) throw new BankException("Account is inactive: " + number);
        return account;
    }
}
