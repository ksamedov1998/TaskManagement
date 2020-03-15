package az.task.demo.Schedule;

import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.Task;
import az.task.demo.Service.TaskService;
import az.task.demo.Util.MailSenderUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.threeten.bp.temporal.TemporalUnit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

@Component
public class MailSender {
    private TaskService service;
    private final int MIN_MAIL_SIZE = 10;

    @Autowired
    private MailSenderUtil mailSenderUtil;

    @Autowired
    public MailSender(TaskService service) {
        this.service = service;
    }

    @Scheduled(cron = "*/10 * * * * *")
    @Async
    public void getTasks() {
        List<NoneExpiredTaskMapper> taskList = service.allNoneExpiredTaskList();
        if (taskList.size() > MIN_MAIL_SIZE) {
            mailSenderUtil. validateTasksAndSend(taskList);
        }
    }



}