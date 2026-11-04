import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        BankService bank = new BankService();
        try (Scanner sc = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                System.out.println("===== MiniBank =====");
                System.out.println("1. Open account");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Account statement");
                System.out.println("6. List accounts (balance high to low)");
                System.out.println("7. Process batch file lines");
                System.out.println("8. Transaction report");
                System.out.println("9. Save and exit");
                System.out.print("Choose: ");
                String choice = sc.nextLine().trim();
                try {
                    switch (choice) {
                        case "1" -> {
                            System.out.print("Owner name: "); String name = sc.nextLine();
                            System.out.print("Opening balance: "); long amount = Long.parseLong(sc.nextLine().trim());
                            System.out.print("Type (SAVINGS/CURRENT/FIXED): "); String type = sc.nextLine().trim();
                            Account a = bank.openAccount(name, amount, type);
                            System.out.println("Account opened: " + a);
                        }
                        case "2" -> {
                            System.out.print("Account number: "); String id = sc.nextLine().trim();
                            System.out.print("Amount: "); long amount = Long.parseLong(sc.nextLine().trim());
                            bank.deposit(id, amount); System.out.println("Deposit successful.");
                        }
                        case "3" -> {
                            System.out.print("Account number: "); String id = sc.nextLine().trim();
                            System.out.print("Amount: "); long amount = Long.parseLong(sc.nextLine().trim());
                            bank.withdraw(id, amount); System.out.println("Withdrawal successful.");
                        }
                        case "4" -> {
                            System.out.print("From account: "); String from = sc.nextLine().trim();
                            System.out.print("To account: "); String to = sc.nextLine().trim();
                            System.out.print("Amount: "); long amount = Long.parseLong(sc.nextLine().trim());
                            bank.transfer(from, to, amount); System.out.println("Transfer successful.");
                        }
                        case "5" -> {
                            System.out.print("Account number: "); System.out.println(bank.statement(sc.nextLine().trim()));
                        }
                        case "6" -> bank.listAccounts().forEach(System.out::println);
                        case "7" -> {
                            System.out.println("Enter commands (DEPOSIT AC0001 500 / WITHDRAW AC0001 200). Blank line ends batch:");
                            List<String> lines = new ArrayList<>();
                            while (true) { String line = sc.nextLine(); if (line.isBlank()) break; lines.add(line); }
                            bank.processBatch(lines); System.out.println("Batch completed.");
                        }
                        case "8" -> System.out.println(bank.runReport());
                        case "9" -> { bank.saveOnExit(); System.out.println("Saved. Goodbye."); running = false; }
                        default -> System.out.println("Invalid menu choice.");
                    }
                } catch (RuntimeException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } finally {
            bank.saveOnExit();
        }
    }
}
