package az.task.demo.Service;

import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.Task;

import java.util.List;

public interface TaskService {
    List<NoneExpiredTaskMapper> allNoneExpiredTaskList();
    void addTask(String header,String description,String assignDateStr,String deadlineStr);
    void updateTaskStatus(int taskId,int taskStatus);
    List<Task> getAllTasks();
    Task getTask(int taskId);
    void assignTaskToUser(int taskId, int userId);
    void updateDeadline(int taskId,String newDeadline);
    void updateTaskState(int taskId,int taskState);
    void setTaskNotified(int taskId);
    void changeExpiredTaskStatus();
}
