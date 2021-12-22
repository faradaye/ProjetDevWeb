package utils;

public class VerifInt {

    public static boolean isNumber(String str){
        if (str == null) {
            return false;
        }

        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
