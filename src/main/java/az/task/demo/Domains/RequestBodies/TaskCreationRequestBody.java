package az.task.demo.Domains.RequestBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskCreationRequestBody {
    private String header;
    private String desc;
    private String assignDate;
    private String deadline;
}
