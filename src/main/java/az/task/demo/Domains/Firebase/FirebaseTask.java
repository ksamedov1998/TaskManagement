package az.task.demo.Domains.Firebase;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class FirebaseTask {

    private String header;

    private String description;

    List<FirebaseUser> userList;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private Date assignDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd/MM/yyyy")
    private Date deadline;

    private int taskStatus;

    private int taskState;

    public FirebaseTask() {
    }

    public List<FirebaseUser> getUserList() {
        return userList;
    }

    public void setUserList(List<FirebaseUser> userList) {
        this.userList = userList;
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


    public Date getAssignDate() {
        return assignDate;
    }
    public void setAssignDate(Date assignDate) {
        this.assignDate = assignDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
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



    @Override
    public String toString() {
        return "FirebaseTask{" +
                "header='" + header + '\'' +
                ", description='" + description + '\'' +
                ", userList=" + userList +
                ", assignDate=" + assignDate +
                ", deadline=" + deadline +
                ", taskStatus=" + taskStatus +
                ", taskState=" + taskState +
                '}';
    }
}
