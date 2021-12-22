package utils;

public class VerifDouble {

    public static boolean isDouble(String str){
        if (str == null) {
            return false;
        }

        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
