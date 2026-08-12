
abstract class Model {
   

   abstract double latefee();
    
}
class Book extends Model{
    private double latefee;
    Book(double latefee){
        this.latefee=latefee;
    }
    double latefee(){
        return latefee*20;
    }
}
class DVD extends Model{
    private double latefee;
    
    double latefee(){
        return latefee*20;
    }
}
class Magazine extends Model{
    private double latefee;
    Book(double latefee){
        this.latefee=latefee;
    }
    double latefee(){
        return latefee*20;
    }
}
