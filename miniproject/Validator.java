import java.util.regex.Pattern;

public class Validator {

    static Pattern mobile = Pattern.compile("^[6-9][0-9]{9}$");
    static Pattern email = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    static Pattern pan = Pattern.compile("^[A-Z]{5}[0-9]{4}[A-Z]$");
    static Pattern ifsc = Pattern.compile("^[A-Z]{4}0[A-Z0-9]{6}$");

    public static boolean isValidMobile(String s) {
        return mobile.matcher(s).matches();
    }

    public static boolean isValidEmail(String s) {
        return email.matcher(s).matches();
    }

    public static boolean isValidPan(String s) {
        return pan.matcher(s).matches();
    }

    public static boolean isValidIfsc(String s) {
        return ifsc.matcher(s).matches();
    }
}