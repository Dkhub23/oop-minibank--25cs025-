
class MyStack<T> {

    Object[] arr;
    int top;
    int size;

    MyStack(int size) {
        this.size = size;
        arr = new Object[size];
        top = -1;
    }

    void push(T value) {
        if (top == size - 1) {
            System.out.println("Stack is Full");
            return;
        }

        arr[++top] = value;
    }

    T pop() {
        if (top == -1) {
            System.out.println("Stack is Empty");
            return null;
        }

        return (T) arr[top--];
    }

    void display() {
        if (top == -1) {
            System.out.println("Stack is Empty");
            return;
        }

        for (int i = top; i >= 0; i--) {
            System.out.println(arr[i]);
        }
    }
}

public class GenericStack {

    public static void main(String[] args) {

        MyStack<Integer> s1 = new MyStack<>(5);

        s1.push(10);
        s1.push(20);
        s1.push(30);

        System.out.println("Integer Stack:");
        s1.display();

        System.out.println("Popped: " + s1.pop());

        MyStack<String> s2 = new MyStack<>(5);

        s2.push("Java");
        s2.push("Python");
        s2.push("C++");

        System.out.println("\nString Stack:");
        s2.display();

        System.out.println("Popped: " + s2.pop());
    }
}
