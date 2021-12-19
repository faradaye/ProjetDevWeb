package utils;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class VerifDate {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("uuuu-MM-dd", Locale.FRANCE);

    public static boolean estValide(String date) {
        try {
            VerifDate.dateFormatter.parse(date);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }
}
