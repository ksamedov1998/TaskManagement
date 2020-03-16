package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.LogBuilder;
import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.TaskNotFound;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.TaskState;
import az.task.demo.Domains.Enums.TaskStatus;
import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.Task;
import az.task.demo.Repository.HibernateRepository;
import az.task.demo.Repository.TaskRepository;
import az.task.demo.Repository.UserRepository;
import az.task.demo.Service.TaskService;
import az.task.demo.Util.DynamicQueryUtil;
import az.task.demo.Util.LogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private HibernateRepository hibernateRepository;

    @Autowired
    private LogHandler logHandler;

    @Override
    public List<NoneExpiredTaskMapper> allNoneExpiredTaskList() {
        return taskRepository.getNoneExpiredTask(TaskState.ASSIGNED.getValue());
    }



    @Override
    public void addTask(String header, String description, String assignDateStr, String deadlineStr) {
        LocalDateTime assignDate = StringToLocalDateConverter(assignDateStr); //throws Runtime exception
        LocalDateTime deadlineDate = StringToLocalDateConverter(deadlineStr); // throws Runtime exception
        if (isAssignDateAfterDeadline(assignDate, deadlineDate)) {
            taskRepository.save(header, description, assignDate, deadlineDate, TaskState.getState(TaskState.NOT_ASSIGNED), TaskStatus.getStatus(TaskStatus.ACTIVE));
        } else {
            throw new DateTimeException("Inconsistency between " + deadlineStr + " and  " + assignDateStr + "[ Deadline should be after Assignment date ]");
        }
    }

    @Override
    public void updateTaskStatus(int taskId, int taskStatus) {
        if (!TaskStatus.checkState(taskStatus)) {
            logHandler.publish(new LogBuilder()
                    .setPoint("TaskServiceImp.updateTaskStatus")
                    .setException("StatusNotFoundException")
                    .setDescription("TaskStatus is not correct")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new StatusNotFoundException(taskStatus, "TASKSTATUS");
        }
        if (taskRepository.updateTaskStatus(taskStatus, taskId) == 0) {
            logHandler.publish(new LogBuilder()
                    .setPoint("TaskServiceImp.updateTaskStatus")
                    .setException("TaskNotFoundException")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new TaskNotFound(taskId);
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(int taskId) {
        Optional<Task> task = taskRepository.getTaskById(taskId);
        if (!task.isPresent()) {
            logHandler.publish(new LogBuilder()
                    .setPoint("TaskServiceImp.updateTaskStatus")
                    .setException("TaskNotFoundException")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new TaskNotFound(taskId);
        }
        return task.get();
    }

    @Override
    public void assignTaskToUser(int taskId, int userId) {
        //  Boilerplate code |
        if (!taskRepository.getTaskById(taskId).isPresent()) {
            throw new TaskNotFound(taskId);
        }
        if (!userRepository.getUserById(userId).isPresent()) {
            throw new UserNotFound(userId);
        }
        //  | needed to fix

        taskRepository.assignTaskToUser(taskId, userId);
        taskRepository.updateTaskState(taskId,TaskState.ASSIGNED.getValue());
    }

    @Override
    public void updateDeadline(int taskId, String newDeadline) {
        LocalDateTime assignDate = StringToLocalDateConverter(newDeadline); //can throw Runtime exception
        if(taskRepository.updateTaskDeadline(taskId, assignDate)==0){
            logHandler.publish(new LogBuilder()
                    .setPoint("TaskServiceImp.updateDeadline")
                    .setException("TaskNotFoundException")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new TaskNotFound(taskId);
        }
        }

    @Override
    public void updateTaskState(int taskId, int taskState) {
        if (TaskState.checkState(taskState)) {
            if(updateTask(taskId,taskState)){
                logHandler.publish(new LogBuilder()
                        .setPoint("TaskServiceImp.updateTask")
                        .setException("TaskNotFoundException")
                        .setLevel(Level.INFO.getName())
                        .setState("FAIL")
                        .build()
                );
                throw new TaskNotFound(taskId);
            }
        } else {
            logHandler.publish(new LogBuilder()
                    .setPoint("TaskServiceImp.updateDeadline")
                    .setException("StatusNotFoundException")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new StatusNotFoundException(taskState,"TASKSTATE");
        }

    }

    @Override
    public void updateTask(int taskId, Task task) {
        String query= DynamicQueryUtil.createQuery(taskId,task);
        if(!hibernateRepository.update(query)){
            logHandler.publish(new LogBuilder()
                    .setPoint("TaskServiceImp.updateTask")
                    .setException("TaskNotFoundException")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new TaskNotFound(taskId);
        }
    }

    private boolean isAssignDateAfterDeadline(LocalDate assignDate, LocalDate deadlineDate) {
    @Override
    public void setTaskNotified(int taskId) {
            taskRepository.setTaskNotified(taskId);
    }

    @Override
    public void changeExpiredTaskStatus() {
            taskRepository.changeExpiredTasksStatus(TaskState.EXPIRED.getValue());
    }

    private boolean isAssignDateAfterDeadline(LocalDateTime assignDate, LocalDateTime deadlineDate) {
        boolean isAfter = false;
        if (deadlineDate.isAfter(assignDate)) {
            isAfter = true;
        }
        return isAfter;
    }

    private LocalDateTime StringToLocalDateConverter(String date) throws DateTimeParseException {
        return  LocalDateTime.parse(date,DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
    }
    private boolean updateTask(int taskId, int taskState) {
        return taskRepository.updateTaskState(taskId, taskState)==0;
    }
}
