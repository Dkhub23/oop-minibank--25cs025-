
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

class MyTest {

    @Run
    public void testAdd() {
        System.out.println("Test Add is running");
    }

    @Run
    public void testSub() {
        System.out.println("Test Sub is running");
    }

    public void testMul() {
        System.out.println("Test Mul is running");
    }

    @Run
    public void testDiv() {
        System.out.println("Test Div is running");
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        MyTest obj = new MyTest();

        Method[] methods = MyTest.class.getDeclaredMethods();

        int count = 0;

        for (Method m : methods) {

            if (m.isAnnotationPresent(Run.class)) {

                m.invoke(obj);
                count++;
            }
        }

        System.out.println("Total tests executed: " + count);
    }
}
