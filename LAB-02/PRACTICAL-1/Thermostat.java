
public class Thermostat{
    private String location;
    private int temperature;
    private static final int MIN=16;
    private static final int MAX=30;
    private static int activeCount=0;

    public Thermostat(String location, int startTemp)
    {
         
        this.location = location;
        this.temperature = (startTemp >= MIN && startTemp <= MAX) ? startTemp : 22;
        activeCount++;
    }

    public Thermostat(String location)
    {
        this(location, 22);
        
    }
    public void raise(){
        if(temperature<MAX)
        temperature++;
        else
        System.out.println("Already at maximum ("+MAX+")" );
    }
     public void lower(){
        if(temperature>MIN)
        temperature--;
        else
        System.out.println("Already at maximum ("+MIN+")" );
    }

    public String getLocation() {
    return location;
    }

    public int getTemperature()
    {
        return temperature;
    }

    public static int getActiveCount(){
        return activeCount;
    }
    public static void main(String[] args) {
        Thermostat living=new Thermostat("living room",24);
        Thermostat bedroom=new Thermostat("bed room");

        for (int i = 0; i < 10; i++) {
            living.raise();
            System.out.println("temp:"+living.getTemperature());
            
        }
        for(int i=0;i<20;i++)
        {
            bedroom.lower();
            System.out.println("temp:"+bedroom.getTemperature());
        }

        System.out.println("active Count:"+living.getActiveCount());
    }

}