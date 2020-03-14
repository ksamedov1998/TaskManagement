package az.task.demo.Schedule;

import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.Task;
import az.task.demo.Service.TaskService;
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
    private final long  HALF_A_DAY=12;


    @Autowired
    public MailSender(TaskService service) {
        this.service = service;
    }

    @Scheduled(cron = "*/10 * * * * *")
    @Async
    public void getTasks() {
        List<NoneExpiredTaskMapper> taskList = service.allNoneExpiredTaskList();
        if (taskList.size() > 0/*MIN_MAIL_SIZE*/) {
            validateTasks(taskList);
        }
    }


    private void validateTasks(List<NoneExpiredTaskMapper> taskList) {
        NoneExpiredTaskMapper taskMapper;
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i);
            taskMapper = taskList.get(i);
            if (getDaysBetween(taskMapper.getAssignDate(),taskMapper.getDeadline()) <= 1) {
                System.out.println("<=1");
                    if(compareWithCurrentDate(taskMapper.getDeadline(),null,true)){
                        sendEmails(taskMapper);
                    }
            } else {
                if(compareWithCurrentDate(taskMapper.getAssignDate(),taskMapper.getDeadline(),false)){
                    sendEmails(taskMapper);
                }
                System.out.println(">1");
            }
        }

    }

    private boolean compareWithCurrentDate(LocalDateTime deadline, LocalDateTime assignDate, boolean isLessThanDay) {
        boolean isReadyToNotify=false;
        if(isLessThanDay){
            if(LocalDateTime.now().until(deadline,HOURS)==HALF_A_DAY){
                isReadyToNotify=true;
                System.out.println("HALF");
            }
        }else{
            System.out.println("FULL");
            if(LocalDateTime.now().until(deadline,DAYS) == deadline.until(assignDate,DAYS)/2){
                isReadyToNotify=true;
            }
            System.out.println(LocalDateTime.now().until(deadline,DAYS));
        }
         return isReadyToNotify;
    }


    public void sendEmails(NoneExpiredTaskMapper taskMapper){
        System.out.println("User email : " + taskMapper.getEmail());
        System.out.println("Task header : " + taskMapper.getHeader());
        System.out.println("Task assign date : " + taskMapper.getAssignDate());
        System.out.println("Task deadline : " + taskMapper.getDeadline());
    }
    public long getDaysBetween(LocalDateTime d1, LocalDateTime d2) {
        return d1.until(d2,DAYS);
    }

}