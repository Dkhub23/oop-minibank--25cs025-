java.util.regex.*;


public class CommandParser{
    public static parse(String line)
    {
        String[] parts=line.split(" ");
        String type = parts[0];
        String account = parts[1];
        int amount = Integer.parseInt(parts[2]);

        return new Command(type, account, amount);
    }
}