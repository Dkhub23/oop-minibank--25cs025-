abstract class Shape{
    public abstract double area();
}
 class Circle extends Shape{
    private double r;
    Circle (double r){
        this.r=r;
    }
    @Override
    public double area(){
        return 3.14*r*r;
    }
}
class Rectangle extends Shape{
     private double l;
     private double w;
    Rectangle (double l,double w){
        this.l=l;
        this.w=w;
    }
    @Override
    public double area(){
        return l*w;
    }
}
class Triangle extends Shape{
    private double b;
    private double h;
    Triangle(double b,double h){
        this.b=b;
        this.h=h;
    }
    @Override
    public double area(){
        return 0.5*b*h;
    }
}
public class ShapeArea{
    public static void main(String arg[]){
        Shape[] shapes={
            new Circle(2.5),
            new Rectangle(2.5,2.5),
            new Triangle(2.0,4.0)
        };
        double total=0;
        double largest=0;
        Shape largestShape=null;
        for(Shape s:shapes){
            double area=s.area();
            total+=area;

            if(area>largest)
            {
                largest=area;
                largestShape=s;
            }
            System.out.println("shape"+largest +"area:"+area);
            

        }
    }
}