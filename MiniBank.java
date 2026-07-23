
import java.util.Scanner;

public class MiniBank{
    enum MenuOption {OPEN_ACCOUNT, DEPOSIT, WITHDRAW, TRANSFER ,EXIT}
    public static void main(String[] args) {
    record BankInfo (String name,String branch){}
    System.out.println("1.OPEN_ACCOUNT\n");
    System.out.println("2.DEPOSIT\n");
    System.out.println("3.WITHDRAW\n");
    System.out.println("4.TRANSFER\n");
    System.out.println("5.EXIT\n");

    Scanner sc=new Scanner(System.in);
    int num=sc.nextInt();
    // use the input to avoid "variable not read" warning
    switch (num) {
        }
    


        
    }
}