package az.task.demo.Service;

import az.task.demo.Domains.Task;

import java.util.List;

public interface TaskService {
    void addTask(String header,String description,int userId);
    void deleteTask(int taskId);
    List<Task> getAllTasks();

}
