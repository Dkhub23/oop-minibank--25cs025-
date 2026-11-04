import java.util.ArrayList;

public class StudentService {

    private ArrayList<Student> students = new ArrayList<>();

    public void addStudent(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Invalid student");
        }
        students.add(s);
    }

    public int getCount() {
        return students.size();
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void deleteStudent(int id) {
        boolean removed = students.removeIf(s -> s.getId() == id);

        if (!removed) {
            throw new IllegalArgumentException("Student not found");
        }
    }
}