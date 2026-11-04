
class Box<T> {
    T value;

    void set(T value) {
        this.value = value;
    }

    T get() {
        return value;
    }
}

class Pair<A, B> {
    A first;
    B second;

    Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    void display() {
        System.out.println(first + " : " + second);
    }
}

public class GenericDemo {

    // Generic method
    static <T> T getFirst(T[] arr) {
        return arr[0];
    }

    public static void main(String[] args) {

        Box<Integer> b1 = new Box<>();
        b1.set(100);
        System.out.println("Box Integer: " + b1.get());

        Box<String> b2 = new Box<>();
        b2.set("Hello Java");
        System.out.println("Box String: " + b2.get());

        Pair<String, Integer> p =
                new Pair<>("Dhruvi", 90);

        System.out.print("Pair: ");
        p.display();

        Integer[] numbers = {10, 20, 30};
        String[] names = {"Amit", "Rahul", "Priya"};

        System.out.println("First Number: " +
                getFirst(numbers));

        System.out.println("First Name: " +
                getFirst(names));
    }
}
