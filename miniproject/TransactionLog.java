import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;

public class TransactionLog {
    private final Path file;
    public TransactionLog() { this(Path.of("logs", "transactions.log")); }
    public TransactionLog(Path file) { this.file = file; }

    public synchronized void append(String transaction) {
        try {
            Path parent = file.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            Files.writeString(file, LocalDateTime.now() + " " + transaction + System.lineSeparator(),
                StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) { throw new BankException("Could not write transaction log.", e); }
    }

    public List<String> readAll() {
        try {
            if (!Files.exists(file)) return List.of();
            return Files.readAllLines(file, StandardCharsets.UTF_8);
        } catch (IOException e) { throw new BankException("Could not read transaction log.", e); }
    }

    public Path getPath() { return file; }
}
