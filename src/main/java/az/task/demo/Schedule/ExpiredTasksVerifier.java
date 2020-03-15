package az.task.demo.Schedule;

import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ExpiredTasksVerifier {

    private TaskService taskService;

    @Autowired
    public ExpiredTasksVerifier(TaskService service) {
        this.taskService = service;
    }

    @Scheduled(cron = "*/1 * * * * *")
    @Async
    public void getTasks() {
        taskService.changeExpiredTaskStatus();

    }
}
