package az.task.demo.Repository;

import az.task.demo.Domains.NoneExpiredTaskMapper;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Query(value = "insert into Task(header,description,assign_date,deadline,task_state,task_status)" +
            " values(:header,:description,:assignDate,:deadline,:taskState,:taskStatus)",
            nativeQuery = true)
    void save(String header, String description, LocalDateTime assignDate,
              LocalDateTime deadline, int taskState,
               int taskStatus);

    @Modifying
    @Query(value = "update Task set task_status=:task_status where id=:id", nativeQuery = true)
    int updateTaskStatus(@Param(value = "task_status") int taskStatus,
                         @Param(value = "id") int taskId);

    List<Task> findAll();

    Optional<Task> getTaskById(int taskId);

    @Modifying
    @Query(value = "insert into User_Task(user_task,task_user) values(:task_id,:user_id)", nativeQuery = true)
    void assignTaskToUser(@Param(value = "task_id") int taskId,
                          @Param(value = "user_id") int userId);


    @Modifying
    @Query(value = "update Task set deadline=:newDeadline where id=:taskId", nativeQuery = true)
    int updateTaskDeadline(int taskId, LocalDateTime newDeadline);


    @Modifying
    @Query(value = "update Task set task_state=:taskState where id=:taskId", nativeQuery = true)
    int updateTaskState(int taskId, int taskState);


    @Query(value = "select u.email email,t.header header,t.id,t.assign_date assignDate,t.deadline deadline from Task t " +
            "            join User_Task ut on ut.user_task=t.id " +
            "             join User u  on ut.task_user=u.id " +
            " where t.task_state = :assigned ", nativeQuery = true)
    List<NoneExpiredTaskMapper> getNoneExpiredTask(@Param("assigned") int state);


    @Modifying
    @Query(value = "update Task set notified=true where id=:taskId", nativeQuery = true)
    void setTaskNotified(int taskId);


    @Modifying
    @Query(value = "update Task set task_state=:state  where (TIMESTAMPDIFF(SECOND ,CURRENT_TIMESTAMP(),deadline) <= 0 ) ; ", nativeQuery = true)
    void changeExpiredTasksStatus(int state);
}
