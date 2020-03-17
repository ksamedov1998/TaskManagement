package az.task.demo.Repository;

import az.task.demo.Domains.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Modifying
    @Transactional
    @Query(value = "insert into Comment(text,status,task_id) values(:#{#comment.commentText}," +
                                " :#{#comment.status}," +
                                " :id )", nativeQuery = true)
    int save(@Param("id") int taskId,@Param("comment") Comment comment);





}
