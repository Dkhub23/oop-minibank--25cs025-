import java.util.regex.Pattern;

public class PasswordChecker{

    private String passWord;

    public PasswordChecker(String passWord) {
        this.passWord=passWord;
    }

    

   
    public static boolean hashUpperCase(String passWord){
        if (passWord == null) return false;
        return Pattern.matches(".*[A-Z].*", passWord);
    }
    public static boolean hashDigit(String passWord){
        if (passWord == null) return false;
        return Pattern.matches(".*[0-9].*", passWord);
    }
    public static boolean minLength(String passWord)
    {
        if(passWord==null) return false;
        return passWord.length()>=8;
    }
    public static boolean hashSpecial(String passWord){
        if(passWord==null) return false;
        return Pattern.matches(".^[a-zA-Z0-9]", passWord);
    }
    public static boolean strength(String passWord){
        int passes = 0;
        if(minLength(passWord)) passes++;
        if(hashDigit(passWord)) passes++;
        if(hashUpperCase(passWord)) passes++;
        if(hashSpecial(passWord)) passes++;

        if(passes==0)
            System.out.println("Weak");
        else if(passes>1&&passes<4)
            System.out.println("Medium");
        else
            System.out.println("Strong");

        return passes == 4;
    }
    
    
}