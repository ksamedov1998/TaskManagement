package az.task.demo.Util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormattingUtil {

     private final static DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static LocalDateTime convertStringToLocalDateTime(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date, dateTimeFormatter);
    }
}
