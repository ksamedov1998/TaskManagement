package az.task.demo.Domains.dto;

import az.task.demo.Domains.Task;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private Integer userType;
    private List<Task> taskList;
    private String username;
    private String email;
}
