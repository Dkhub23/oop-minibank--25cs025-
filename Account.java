import java.lang.*;
import java.util.*;

public class Account {
    private static int counter = 1;
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    public Account(String ownerName, long balance) {
        this.accountNumber = String.format("AC%04d", counter++);
        this.ownerName = ownerName;
        this.balance = balance;
        this.active = true;
    }

    public void deposit(long amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(long amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

   @Override 
   public String toString(){
    return "Account Number:"+accountNumber+"\n"+"Owner Name:"+ownerName+"\n"+"Balance:"+balance;

   }

   @Override
   public boolean equals(Object obj){
       if (this == obj) return true;
       if (!(obj instanceof Account)) return false;
       Account other = (Account) obj;
       return Objects.equals(accountNumber, other.accountNumber)
               && Objects.equals(ownerName, other.ownerName)
               && balance == other.balance
               && active == other.active;
   }

   @Override
   public int hashCode(){
       return Objects.hash(accountNumber, ownerName, balance, active);
   }
}
        
    
