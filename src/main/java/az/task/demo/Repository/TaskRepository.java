package az.task.demo.Repository;

import az.task.demo.Domains.Task;
import org.hsqldb.HsqlException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Modifying
    @Query(value = "insert into Task(header,description,assign_date,deadline,task_state,task_status) values(:header,:description,:assignDate,:deadline,:task_state,:task_status)",nativeQuery = true)
    @Transactional
    void save(String header, String description, LocalDate assignDate, LocalDate deadline,@Param(value = "task_state") int taskState,@Param(value = "task_status") int taskStatus);

    @Modifying
    @Query(value = "update Task set task_status:task_status where id=:id",nativeQuery = true)
    @Transactional
    int updateTaskStatus(@Param(value = "task_status") int taskStatus,
                    @Param(value = "id") int taskId);

    List<Task> findAll();

    Optional<Task> getTaskById(int taskId);

    @Modifying
    @Query(value = "insert into User_Task(user_task,task_user) values(:user_id,:task_id)",nativeQuery = true)
    @Transactional
    void assignTaskToUser(@Param(value ="task_id") int taskId,
                          @Param(value ="user_id") int userId) ;


    @Modifying
    @Query(value = "update Task set deadline=:newDeadline where id=:taskId",nativeQuery = true)
    @Transactional
    int updateTaskDeadline(int taskId,LocalDate newDeadline);


    @Modifying
    @Query(value = "update Task set task_state=:taskState where id=:taskId",nativeQuery = true)
    @Transactional
    int updateTaskState(int taskId, int taskState);
}
