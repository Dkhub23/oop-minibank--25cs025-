
package service;

import model.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    @TempDir
    Path tempDir;

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

        assertThrows(
            IllegalArgumentException.class,
            () -> service.deleteStudent(100)
        );
    }

    @Test
    void testSaveAndReload() throws Exception {

        StudentService service1 = new StudentService();

        service1.addStudent(new Student(1, "Dhruvi"));
        service1.addStudent(new Student(2, "Amit"));

        String file = tempDir.resolve("students.dat").toString();

        service1.saveData(file);

        StudentService service2 = new StudentService();

        service2.loadData(file);

        assertEquals(service1.getCount(), service2.getCount());

        for (int i = 0; i < service1.getCount(); i++) {

            Student original = service1.getStudents().get(i);
            Student loaded = service2.getStudents().get(i);

            assertEquals(original.getId(), loaded.getId());
            assertEquals(original.getName(), loaded.getName());
        }
    }
}
