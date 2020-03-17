package az.task.demo.Repository;

import az.task.demo.Domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Modifying
    @Query(value = "insert into Comment values(#{comment.commentText}," +
                                "#{comment.insertDate}," +
                                "#{comment.deleteDate}," +
                                "#{comment.status}," +
                                "task_id)",
                                nativeQuery = true)
    Comment saveAndSort(Comment comment);


}
