import java.io.Serializable;
import java.util.Objects;

public abstract class Account implements Serializable, Comparable<Account>, Transactable {
    private static final long serialVersionUID = 1L;
    private static int accountCounter = 0;

    @Id
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active = true;

    private static synchronized String generateAccountNumber() {
        accountCounter++;
        return String.format("AC%04d", accountCounter);
    }

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = Objects.requireNonNull(ownerName, "Owner name cannot be null");
        this.balance = openingBalance;
    }

    public Account(String ownerName) { this(ownerName, 0); }

    @Override
    public synchronized void deposit(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive.");
        balance = Math.addExact(balance, amount);
    }

    @Override
    public synchronized boolean withdraw(long amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive.");
        if (!active || !canWithdraw(amount)) return false;
        balance = Math.subtractExact(balance, amount);
        return true;
    }

    public abstract double interestRate();
    public abstract boolean canWithdraw(long amount);
    public static synchronized void updateCounterFromAccountNumber(String number) {
        if (number != null && number.matches("AC\\d{4,}")) {
            try {
                accountCounter = Math.max(accountCounter, Integer.parseInt(number.substring(2)));
            } catch (NumberFormatException ignored) { }
        }
    }

    public String getAccountNumber() { return accountNumber; }
    public String getOwnerName() { return ownerName; }
    public synchronized long getBalance() { return balance; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
    protected synchronized void setBalance(long balance) { this.balance = balance; }

    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }

    @Override
    public String toString() {
        return accountNumber + " | " + ownerName + " | Balance: " + getBalance();
    }
}
