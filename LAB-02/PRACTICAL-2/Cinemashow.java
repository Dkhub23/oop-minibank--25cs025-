

public class Cinemashow{
    private String title;
    private int seatAvailable;
    private final int capacity;
    private static int totalBooked = 0;

    public Cinemashow(String title, int capacity){
        this.title = title;
        this.capacity = capacity;
        this.seatAvailable = capacity;
    }

    public Cinemashow(String title)
    {
        this(title,100);
    }

    public boolean  book(int n){
        if(n<seatAvailable)
        {
            seatAvailable-=n;
            totalBooked+=n;
            return true;
        }
        else
        return false;
    }
    public void cancel(int n){
        seatAvailable+=n;
    }

    public int getCapacity(){
        return capacity;
    }
    public int getSeatAvailable(){
        return seatAvailable;
    }
    public static int getTotalBook()
    {
        return totalBooked;
    }
    public static void main(String[] args) {
        Cinemashow A=new Cinemashow("intersteller",5);
        Cinemashow B=new Cinemashow("dhum");

        System.out.println("Book A"+" |"+A.book(3)+"|total booked seat:"+getTotalBook()+"|  total seat avilable:"+A.getSeatAvailable());
        System.out.println("Book A"+" |"+A.book(3)+"|total seat:"+getTotalBook()+"|  total seat avilable:"+A.getSeatAvailable());
        System.out.println("Book B"+" |"+B.book(5)+"|total booked seat:"+getTotalBook()+"|  total seat avilable:"+B.getSeatAvailable());
    }
}