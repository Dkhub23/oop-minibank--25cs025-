
import java.util.ArrayList;



public class StudentService {
    private ArrayList <Student> students=new ArrayList<>();

    public static class Student {
        private final String name;
        private final int marks;

        public Student(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    public void add(String name,int marks){
        if(name==null||name.isBlank())
            throw new IllegalArgumentException("name cannot be empty\n");
        if(marks<0 || marks>100)
            throw new IllegalArgumentException("marks is invalid");
        students.add(new Student(name, marks));
    }

    public int count(){
        return students.size();
    }

    public ArrayList<Student> getall(){
        return  students;
    }
}
