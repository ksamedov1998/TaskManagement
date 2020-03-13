package az.task.demo.Controller.Firebase;

import az.task.demo.Domains.Firebase.FirebaseTask;
import az.task.demo.Service.FirebaseService.TaskService;
import com.google.firebase.FirebaseApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

@RestController("firebaseTaskController")
@RequestMapping("/firebase/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @PostMapping("/create")
    public void createTask(@RequestBody FirebaseTask firebaseTask) {
        System.out.println(firebaseTask);
        taskService.createTask(firebaseTask);
    }

    @GetMapping("/list")
    public void getAllTasks() {
        taskService.getAll();
    }

}
