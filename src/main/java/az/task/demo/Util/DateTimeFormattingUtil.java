package az.task.demo.Util;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeFormattingUtil {
    @Value("${localdatetime.pattern}")
    private String pattern;

    public  LocalDateTime convertStringToLocalDateTime(String date) throws DateTimeParseException {
        return LocalDateTime.parse(date,  DateTimeFormatter.ofPattern(pattern));
    }
}
