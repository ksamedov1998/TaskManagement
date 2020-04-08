package az.task.demo.Domains.RequestBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatingRequestBody {
    private String username;
    private String email;
    private String password;
    private int userType;
    private int userStatus;
}
