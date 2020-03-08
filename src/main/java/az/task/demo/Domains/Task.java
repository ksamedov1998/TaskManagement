package az.task.demo.Domains;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import net.minidev.json.annotate.JsonIgnore;
import org.aspectj.lang.annotation.After;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Component
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany()
    @JoinTable(name = "User_Task",
                    joinColumns = {@JoinColumn(name = "user_task")},
                    inverseJoinColumns = {@JoinColumn(name = "task_user")})
    @JsonIgnore
    private List<User> listOfUsers;

    private String header;

    private String description;

//    Comment add -> add reply Comment

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "assign_date")
    private LocalDate assignDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Column(name = "deadline")
    private LocalDate deadline;

    @Column(name = "task_status")
    private int taskStatus;

    @Column(name = "task_state")
    private int taskState;

    public Task() {
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public int getTaskState() {
        return taskState;
    }

    public void setTaskState(int taskState) {
        this.taskState = taskState;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public List<User> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(List<User> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
