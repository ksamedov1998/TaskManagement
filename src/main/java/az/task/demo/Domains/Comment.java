package az.task.demo.Domains;

import az.task.demo.Domains.Enums.Status;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Component
public class Comment implements Serializable {
    private static final long serialVersionUID = 268345117934850758L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String commentText;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreationTimestamp
    @Column(name = "insert_date")
    private LocalDateTime insertDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column(name = "delete_date")
    private LocalDateTime deleteDate;

    private int status;

    @JsonIgnore
    @ManyToOne
    private Task task;

    public Comment() {
    }

    public LocalDateTime getDeleteDate() {
        return deleteDate;
    }



    public void setDeleteDate(LocalDateTime deleteDate) {
        this.deleteDate = deleteDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(LocalDateTime insertDate) {
        this.insertDate = insertDate;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", insertDate=" + insertDate +
                ", deleteDate=" + deleteDate +
                ", status=" + status +
                ", task=" + task +
                '}';
    }
}
