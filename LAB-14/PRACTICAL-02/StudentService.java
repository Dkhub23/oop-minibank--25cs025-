
package service;

import model.Student;
import java.util.ArrayList;
import java.io.*;

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
        boolean removed = students.removeIf(
            s -> s.getId() == id
        );

        if (!removed) {
            throw new IllegalArgumentException("Student not found");
        }
    }

    public void saveData(String filename) throws IOException {

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(filename))) {

            out.writeObject(students);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData(String filename)
            throws IOException, ClassNotFoundException {

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(filename))) {

            students = (ArrayList<Student>) in.readObject();
        }
    }
}
