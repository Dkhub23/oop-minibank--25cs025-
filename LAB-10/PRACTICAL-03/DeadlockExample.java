
class BankAccount {

    String name;

    BankAccount(String name) {
        this.name = name;
    }
}

public class DeadlockExample {

    public static void main(String[] args) {

        BankAccount A = new BankAccount("Account A");
        BankAccount B = new BankAccount("Account B");

        Thread t1 = new Thread(() -> {

            synchronized (A) {

                System.out.println("Thread 1 locked Account A");

                try {
                    Thread.sleep(100);
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (B) {
                    System.out.println("Thread 1 locked Account B");
                }
            }
        });

        Thread t2 = new Thread(() -> {

            synchronized (B) {

                System.out.println("Thread 2 locked Account B");

                try {
                    Thread.sleep(100);
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                synchronized (A) {
                    System.out.println("Thread 2 locked Account A");
                }
            }
        });

        t1.start();
        t2.start();
    }
}
