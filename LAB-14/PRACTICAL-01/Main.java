import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        StudentService service = new StudentService();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Count Students");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    service.addStudent(new Student(id, name));
                    System.out.println("Student Added!");
                    break;

                case 2:
                    for (Student s : service.getStudents()) {
                        System.out.println(
                            s.getId() + " " + s.getName()
                        );
                    }
                    break;

                case 3:
                    System.out.println("Total Students: " +
                            service.getCount());
                    break;

                case 4:
                    System.out.print("Enter ID: ");
                    int deleteId = sc.nextInt();

                    try {
                        service.deleteStudent(deleteId);
                        System.out.println("Student Deleted!");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid Choice!");
            }
        }
    }
}