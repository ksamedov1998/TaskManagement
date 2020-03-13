package az.task.demo.Domains;

import lombok.ToString;

import java.time.LocalDate;

public interface NoneExpiredTaskMapper {

    String getEmail();

    String getHeader();


    LocalDate getAssignDate();

    LocalDate getDeadline();

}
