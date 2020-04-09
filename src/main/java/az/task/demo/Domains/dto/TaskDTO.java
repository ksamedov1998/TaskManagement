package az.task.demo.Domains.dto;

import az.task.demo.Domains.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class TaskDTO {
    private Integer id;
    private String header;
    private String description;
    private LocalDateTime assignDate;
    private LocalDateTime deadline;
    private List<Comment> commentList;
}
