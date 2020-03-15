package az.task.demo.Domains;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface NoneExpiredTaskMapper {

    int getId();

    String getEmail();

    String getHeader();

    LocalDateTime getAssignDate();

    LocalDateTime getDeadline();

}
