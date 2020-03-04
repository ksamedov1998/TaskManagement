package az.task.demo.Service.Implementations;

import az.task.demo.Repository.TaskRepository;
import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(String header,String description) {
        taskRepository.save(header,description);
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteTaskById(taskId);
    }
}
