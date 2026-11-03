import java.io.*;
import java.nio.file.*;

public class StatePersister {
    private final Path file;
    public StatePersister() { this(Path.of("data", "accounts.dat")); }
    public StatePersister(Path file) { this.file = file; }

    public synchronized void save(Account[] accounts) {
        try {
            Path parent = file.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file))) {
                out.writeObject(accounts);
            }
        } catch (IOException e) { throw new BankException("Could not save accounts.", e); }
    }

    public synchronized Account[] load() {
        if (!Files.exists(file)) return new Account[0];
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(file))) {
            Object value = in.readObject();
            if (!(value instanceof Account[] accounts)) throw new BankException("Saved data has an unexpected format.");
            return accounts;
        } catch (IOException | ClassNotFoundException e) { throw new BankException("Could not load accounts.", e); }
    }
}
