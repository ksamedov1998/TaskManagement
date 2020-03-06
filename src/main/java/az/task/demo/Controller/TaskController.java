package az.task.demo.Controller;

import az.task.demo.Domains.Task;
import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping(value = "/{taskId}/state/{state}")
    public void updateTaskState(@PathVariable(value = "taskId") int taskId,
                                @PathVariable(value = "state") int state){
        taskService.updateTaskState(taskId,state);
    }

    @GetMapping(value = "/{taskId}")
    public Task getTask(@PathVariable(value = "taskId") int taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping("/add")
    public void createTask(@RequestParam(value = "header") String header,
                           @RequestParam(value = "desc") String description,
                           @RequestParam(value = "assignDate") String assignDate,
                           @RequestParam(value = "deadline") String deadline){
        taskService.addTask(header,description,assignDate,deadline);
    }

    @GetMapping("/update/{taskId}/status/{newStatus}")
    public void updateTaskStatus(@PathVariable(value = "taskId") int taskId,
                           @PathVariable(value = "newStatus") int status){
        taskService.updateTaskStatus(taskId,status);
    }

    @GetMapping("/assign/task/{taskId}/user/{userId}")
    public void assignTaskToUser(@PathVariable(value = "taskId") int taskId,
                                 @PathVariable(value = "userId") int userId){
        taskService.assignTaskToUser(taskId,userId);
    }

    @PostMapping("/postpone/{taskId}")
    public void updateDeadline(@PathVariable(value = "taskId") int taskId,@RequestParam(value = "newDeadline") String newDeadline){
        taskService.updateDeadline(taskId,newDeadline);
    }

}
