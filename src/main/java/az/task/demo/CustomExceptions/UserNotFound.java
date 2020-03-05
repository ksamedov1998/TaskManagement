package az.task.demo.CustomExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFound extends RuntimeException {

    private String reason;

    public UserNotFound(String cause) {
        this.reason=cause;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "UserNotFound{" +
                "reason='" + reason + '\'' +
                '}';
    }
}
