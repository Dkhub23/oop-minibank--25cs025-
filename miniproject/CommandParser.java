

public class CommandParser{
    public static Command parse(String line)
    {
        String[] parts=line.split(" ");
        String type = parts[0];
        String account = parts[1];
        int amount = Integer.parseInt(parts[2]);

        return new Command(type, account, amount);
    }

    public static class Command {
        public final String type;
        public final String account;
        public final int amount;

        public Command(String type, String account, int amount) {
            this.type = type;
            this.account = account;
            this.amount = amount;
        }
    }
}