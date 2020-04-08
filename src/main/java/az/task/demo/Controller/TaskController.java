package az.task.demo.Controller;

import az.task.demo.Domains.RequestBodies.TaskCreationRequestBody;
import az.task.demo.Domains.RequestBodies.UpdateTaskStateRequestBody;
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
    public void updateTaskState(@RequestBody UpdateTaskStateRequestBody updateTaskStateRequestBody){
        taskService.updateTaskState(updateTaskStateRequestBody.getTaskId(),updateTaskStateRequestBody.getState());
    }

    @GetMapping(value = "/{taskId}")
    public Task getTask(@PathVariable(value = "taskId") int taskId){
        return taskService.getTask(taskId);
    }

    @PostMapping("/add")
    public void createTask(@RequestBody TaskCreationRequestBody requestBody){
        taskService.addTask(requestBody.getHeader(),requestBody.getDesc(),requestBody.getAssignDate(),requestBody.getDeadline());
    }

    @PatchMapping("/set/status/{taskId}")
    public void updateTaskStatus(@PathVariable(value = "taskId") int taskId,
                                @RequestParam(value = "newStatus") int status){
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

    @PutMapping("/update/{taskId}")
    public void updateTask(@PathVariable(value = "taskId") int taskId,
                            @RequestBody Task task){
        taskService.updateTask(taskId,task);
    }

}
