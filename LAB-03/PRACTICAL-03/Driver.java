public class Driver {
    public static void main(String[] args) {
        Fraction f1 = new Fraction(1, 2);
        Fraction f2 = new Fraction(2, 4);
        Fraction f3 = new Fraction(3, 7);

        System.out.println("new Fraction(2,4) prints " + f2 + " and equals new Fraction(1,2): " + f2.equals(f1));
        System.out.println("new Fraction(3,7) prints " + f3 + " and equals new Fraction(1,2): " + f3.equals(f1));
        System.out.println("All fractions are equal: " + (f1.equals(f2) && f2.equals(f3)));
    }
}