abstract class Employee{
    protected String name;
    protected int id;

    public Employee(String name,int id) {
        this.name=name;
        this.id=id;
    }
    
    abstract double monthlySalary();
}
class PartTime extends Employee {

    private double hour;
    private double rate;
    

    public PartTime(String name,int id,double hour,double  rate) {
        super(name,id);

        this.hour=hour;
        this.rate=rate;
    }
    @Override
    public double monthlySalary() {
        return hour*rate;
    } 
}
 class Intern  extends Employee{
     private double stipend;
    

    public Intern(String name,int id,double stipend) {
        super(name,id);
        this.stipend=stipend;
    }
    @Override
    public double monthlySalary() {
        return stipend;
    }
}
 class FullTime extends Employee {
    private double fixed;
    

    public FullTime(String name,int id,double fixed) {
        super(name,id);
        this.fixed=fixed;
    }
    @Override
    public double monthlySalary() {
        return fixed;
    }
}

public class Payroll{
    public static void main(String[] args) {
        Employee[] e={
            new FullTime("dhruvi",10 , 10000),
            new PartTime("radadiya", 2, 24, 500),
            new Intern("bam", 3, 5000)

        };
        double total=0;
        double salary=0;
        for(Employee i:e){
            
        salary= i.monthlySalary();

           total+=salary;
            System.out.println("name:"+i.name+" id:"+i.id+" salary:"+i.monthlySalary());
            if (i instanceof Intern){
                System.out.println("this is not salary it is only stipend");
            }
        }
        System.out.println("payroll:"+total);
    }
}