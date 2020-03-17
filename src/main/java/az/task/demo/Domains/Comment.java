package az.task.demo.Domains;

import az.task.demo.Domains.Enums.Status;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "text")
    private String commentText;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreationTimestamp
    @Column("insert_date")
    private LocalDateTime insertDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @UpdateTimestamp
    @Column("delete_date")
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
