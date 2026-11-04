import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @Test
    void testAddStudent() {
        StudentService service = new StudentService();

        service.addStudent(new Student(1, "Dhruvi"));

        assertEquals(1, service.getCount());
    }

    @Test
    void testMultipleStudents() {
        StudentService service = new StudentService();

        service.addStudent(new Student(1, "Dhruvi"));
        service.addStudent(new Student(2, "Amit"));

        assertEquals(2, service.getCount());
    }

    @Test
    void testInvalidAction() {
        StudentService service = new StudentService();

        assertThrows(IllegalArgumentException.class,
                () -> service.deleteStudent(100));
    }
}