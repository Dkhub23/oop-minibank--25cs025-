interface Notifier {
    void send(String mssg);
}

interface Urgent {
}

class UrgentSms implements Notifier, Urgent {

    public void send(String mssg) {
        System.out.println("Urgent SMS: " + mssg);
    }
}

public class NotificationSender {

    public static void main(String[] args) {

        Notifier email = mssg -> System.out.println("Email: " + mssg);

        Notifier sms = mssg -> System.out.println("SMS: " + mssg);

        Notifier urgentsms = new UrgentSms();

        Notifier[] senders = {email, sms, urgentsms};

        String message = "Server is down";

        for (Notifier sender : senders) {
            sender.send(message);
            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}