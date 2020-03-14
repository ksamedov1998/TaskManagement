package az.task.demo.Domains;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface NoneExpiredTaskMapper {

    String getEmail();

    String getHeader();

    LocalDateTime getAssignDate();

    LocalDateTime getDeadline();

}
