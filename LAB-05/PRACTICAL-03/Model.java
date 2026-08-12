
abstract class Media {
    private double latefee;
    Media(double latefee){
        this.latefee=latefee;
    }

   abstract double latefee();
    
}
class Book extends Media{
    private double latefee;
    Book(double latefee){
        super(latefee);
        this.latefee=latefee;
    }
    double latefee(){
        return latefee*20;
    }
}
class DVD extends Media{
    private double latefee;
    DVD(double latefee){
        super(latefee);
        this.latefee=latefee;
    }
    double latefee(){
        return latefee*20;
    }
}
class Magazine extends Media{
    private double latefee;
    Magazine(double latefee){
        super(latefee);
        this.latefee=latefee;
    }
    double latefee(){
        return latefee*20;
    }
}
public class Model{
    public static void main(String[] args) {
        Media[] m={
            new Book(3),
            new DVD(1),
            new Magazine(5)
        };

        for(Media i:m){
            System.out.println("late fee:" +i.latefee()+" for the media:" +i.getClass());

        }
    }
}