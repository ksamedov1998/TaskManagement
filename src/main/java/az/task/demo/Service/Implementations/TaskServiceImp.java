package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.LogBuilder;
import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.TaskNotFound;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.Status;
import az.task.demo.Domains.Enums.TaskState;
import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.Task;
import az.task.demo.Repository.HibernateRepository;
import az.task.demo.Repository.TaskRepository;
import az.task.demo.Repository.UserRepository;
import az.task.demo.Service.TaskService;
import az.task.demo.Util.DateTimeFormattingUtil;
import az.task.demo.Util.DynamicQueryUtil;
import az.task.demo.Util.LogHandler;
import org.springframework.stereotype.Service;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
public class TaskServiceImp implements TaskService {

    private final TaskRepository taskRepository;

    private final UserRepository userRepository;

    private final HibernateRepository hibernateRepository;

    private final LogHandler logHandler;

    private final DateTimeFormattingUtil dateTimeFormattingUtil;

    public TaskServiceImp(TaskRepository taskRepository, UserRepository userRepository, HibernateRepository hibernateRepository, LogHandler logHandler, DateTimeFormattingUtil dateTimeFormattingUtil) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.hibernateRepository = hibernateRepository;
        this.logHandler = logHandler;
        this.dateTimeFormattingUtil = dateTimeFormattingUtil;
    }

    @Override
    public List<NoneExpiredTaskMapper> allNoneExpiredTaskList() {
        return taskRepository.getNoneExpiredTask(TaskState.ASSIGNED.getValue());
    }


    @Override
    public void addTask(String header, String description, String assignDateStr, String deadlineStr) {
        LocalDateTime assignDate = dateTimeFormattingUtil.convertStringToLocalDateTime(assignDateStr); //throws Runtime exception
        LocalDateTime deadlineDate = dateTimeFormattingUtil.convertStringToLocalDateTime(deadlineStr); // throws Runtime exception
        System.out.println(deadlineDate);
        if (isAssignDateAfterDeadline(assignDate, deadlineDate)) {
            taskRepository.save(header, description, assignDate, deadlineDate, TaskState.getState(TaskState.NOT_ASSIGNED), Status.getStatus(Status.ACTIVE));
        } else {
            throw new DateTimeException("Inconsistency between " + deadlineStr + " and  " + assignDateStr + "[ Deadline should be after Assignment date ]");
        }
    }

    @Override
    public void updateTaskStatus(int taskId, int taskStatus) {
        if (!Status.checkState(taskStatus)) {
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
        taskRepository.updateTaskState(taskId, TaskState.ASSIGNED.getValue());
    }

    @Override
    public void updateDeadline(int taskId, String newDeadline) {
        LocalDateTime assignDate = dateTimeFormattingUtil.convertStringToLocalDateTime(newDeadline); //can throw Runtime exception
        if (taskRepository.updateTaskDeadline(taskId, assignDate) == 0) {
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
            if (updateTask(taskId, taskState)) {
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
            throw new StatusNotFoundException(taskState, "TASKSTATE");
        }

    }

    @Override
    public void updateTask(int taskId, Task task) {
        String query = DynamicQueryUtil.createQuery(taskId, task);
        if (!hibernateRepository.update(query)) {
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


    private boolean updateTask(int taskId, int taskState) {
        return taskRepository.updateTaskState(taskId, taskState) == 0;
    }


}


