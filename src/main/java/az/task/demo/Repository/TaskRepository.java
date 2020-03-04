package az.task.demo.Repository;

import az.task.demo.Domains.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Modifying
    @Query(value = "insert into Task(header,description) values(:header,:description)",nativeQuery = true)
    @Transactional
    void save(String header, String description);

//    Task status should be added
    void deleteTaskById(int taskId);

    List<Task> findAll();

    Task getTaskById(int taskId);
}
