interface Switchable {

    void on();
    void off();

    default void toggle(boolean isOn) {
        if (isOn)
            off();
        else
            on();
    }
}

class Fan implements Switchable {

    public void on() {
        System.out.println("Fan turned on");
    }

    public void off() {
        System.out.println("Fan turned off");
    }
}

class Light implements Switchable {

    public void on() {
        System.out.println("Light turned on");
    }

    public void off() {
        System.out.println("Light turned off");
    }
}


interface SwitchPolicy {
    boolean maySwitchOn(Switchable device, int hour);
}

public class SwitchControl {

    public static void main(String[] args) {

        Switchable[] devices = {
            new Fan(),
            new Light()
        };

        boolean[] isOn = {
            false,
            true
        };

        for (int i = 0; i < devices.length; i++) {
            devices[i].toggle(isOn[i]);
        }

        SwitchPolicy policy1 = new SwitchPolicy() {
            public boolean maySwitchOn(Switchable device, int hour) {
                return hour >= 6 && hour <= 22;
            }
        };

        SwitchPolicy policy2 =
            (device, hour) -> hour >= 6 && hour <= 22;

       
        System.out.println("Anonymous class: " + policy1.maySwitchOn(new Fan(), 10));
         System.out.println("lamda: " + policy2.maySwitchOn(new Fan(), 10));
        System.out.println("Anonymous class: " + policy1.maySwitchOn(new Light(), 23));
        System.out.println("Lambda: " + policy2.maySwitchOn(new Light(), 23));
    }
}