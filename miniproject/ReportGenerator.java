import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.List;

public class ReportGenerator {
    private final TransactionLog log;
    private final Path reportFile;
    public ReportGenerator(TransactionLog log) { this(log, Path.of("reports", "transaction-report.txt")); }
    public ReportGenerator(TransactionLog log, Path reportFile) { this.log = log; this.reportFile = reportFile; }

    public String generate() {
        long deposits = 0, withdrawals = 0;
        for (String line : log.readAll()) {
            String[] parts = line.trim().split("\\s+");
            for (int i = 0; i < parts.length - 2; i++) {
                if (parts[i].equals("DEPOSIT")) {
                    try { deposits = Math.addExact(deposits, Long.parseLong(parts[i + 2])); } catch (NumberFormatException ignored) {}
                } else if (parts[i].equals("WITHDRAW")) {
                    try { withdrawals = Math.addExact(withdrawals, Long.parseLong(parts[i + 2])); } catch (NumberFormatException ignored) {}
                }
            }
        }
        String report = "Total deposits: " + deposits + System.lineSeparator()
            + "Total withdrawals: " + withdrawals + System.lineSeparator()
            + "Net change: " + (deposits - withdrawals) + System.lineSeparator();
        try {
            Path parent = reportFile.toAbsolutePath().getParent();
            if (parent != null) Files.createDirectories(parent);
            Files.writeString(reportFile, report, StandardCharsets.UTF_8);
        } catch (IOException e) { throw new BankException("Could not write report.", e); }
        return report;
    }
}
