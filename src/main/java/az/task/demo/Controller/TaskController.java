package az.task.demo.Controller;

import az.task.demo.Domains.Task;
import az.task.demo.Repository.TaskRepository;
import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(value = "/")
    public List<Task> getTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{taskId}")
    public Task getTask(@PathVariable(value = "taskId") int taskId){
        return taskService.getTask(taskId);
    }


    @PostMapping("/add")
    public void createTask(@RequestParam(value = "header") String header,
                           @RequestParam(value = "desc") String description,
                           @RequestParam(value = "userId") int userId){
        taskService.addTask(header,description,userId);
    }

    @GetMapping("/delete/{taskId}")
    public void deleteTask(@PathVariable(value = "taskId") int taskId){
        taskService.deleteTask(taskId);
    }
}
