
import java.lang.annotation.*;
import java.lang.reflect.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Column {
    String name();
}

class Student {

    @Column(name = "Student_Name")
    String name;

    @Column(name = "Student_Age")
    int age;

    @Column(name = "Student_City")
    String city;

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("City: " + city);
    }
}

public class Main {

    public static void main(String[] args) throws Exception {

        String[] header = {"Student_Name", "Student_Age", "Student_City"};

        String[] data = {"Dhruvi", "19", "Surat"};

        Student s = new Student();

        Field[] fields = Student.class.getDeclaredFields();

        for (Field f : fields) {

            if (f.isAnnotationPresent(Column.class)) {

                Column c = f.getAnnotation(Column.class);

                String columnName = c.name();

                int index = -1;

                for (int i = 0; i < header.length; i++) {
                    if (header[i].equals(columnName)) {
                        index = i;
                        break;
                    }
                }

                if (index == -1) {
                    System.out.println("Column not found: " + columnName);
                    continue;
                }

                f.setAccessible(true);

                if (f.getType() == String.class) {
                    f.set(s, data[index]);
                }
                else if (f.getType() == int.class) {
                    f.setInt(s, Integer.parseInt(data[index]));
                }
            }
        }

        s.display();
    }
}
