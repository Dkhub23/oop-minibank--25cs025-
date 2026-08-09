
import java.util.Scanner;

public class Driver{
    public static void main(String[] args)
    {
        String[] logs={
            "10:05 alice Hello there",
            "10:10 bob Good morning",
            "WrongLine"
        };

        Scanner sc=new Scanner(System.in);

        System.out.println("Enter keyword:");
        String keyword=sc.nextLine();

        ChatFilter.searchLogs(logs, keyword);
        sc.close();

    }
}