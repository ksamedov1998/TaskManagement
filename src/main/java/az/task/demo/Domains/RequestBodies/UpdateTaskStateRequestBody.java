package az.task.demo.Domains.RequestBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStateRequestBody {
    private int taskId;
    private int state;
}
