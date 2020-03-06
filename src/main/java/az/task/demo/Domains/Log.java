package az.task.demo.Domains;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.logging.Level;

@Entity
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String level;

    private String state;

    private String exception;

    private String description;

    private String point;

    private LocalDateTime timestamp;

    public Log() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Log(String level, String state, String exception, String description, String point,LocalDateTime localDateTime) {
        this.level=level;
        this.description=description;
        this.exception=exception;
        this.state=state;
        this.point=point;
        this.timestamp=localDateTime;
    }


    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", level=" + level +
                ", exception='" + exception + '\'' +
                ", description='" + description + '\'' +
                ", point='" + point + '\'' +
                '}';
    }
}
