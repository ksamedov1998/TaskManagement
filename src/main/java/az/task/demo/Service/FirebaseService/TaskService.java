package az.task.demo.Service.FirebaseService;

import az.task.demo.Domains.Firebase.FirebaseTask;
import az.task.demo.Repository.Firebase.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("firebaseTaskService")
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public void createTask(FirebaseTask task){
        System.out.println(task.getAssignDate());
        taskRepository.createTask(task);
    }

    public void getAll() {
        taskRepository.getAllTasks();
    }
}
