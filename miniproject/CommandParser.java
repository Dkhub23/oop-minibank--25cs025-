public class CommandParser {
    public static Command parse(String line) {
        if (line == null || line.trim().isEmpty()) throw new IllegalArgumentException("Transaction line cannot be empty.");
        String[] parts = line.trim().split("\\s+");
        if (parts.length != 3) throw new IllegalArgumentException("Expected: DEPOSIT|WITHDRAW accountNumber amount");
        TransactionType type;
        try { type = TransactionType.valueOf(parts[0].toUpperCase()); }
        catch (IllegalArgumentException e) { throw new IllegalArgumentException("Unknown transaction type: " + parts[0]); }
        long amount;
        try { amount = Long.parseLong(parts[2]); }
        catch (NumberFormatException e) { throw new InvalidAmountException("Amount must be a whole number."); }
        Validator.requirePositiveAmount(amount);
        if (type == TransactionType.TRANSFER) throw new IllegalArgumentException("Use TRANSFER from the menu with source and destination account numbers.");
        return new Command(type, parts[1], amount);
    }
}
