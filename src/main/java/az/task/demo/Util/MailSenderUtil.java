package az.task.demo.Util;

import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.HOURS;

@Component
public class MailSenderUtil {

    @Autowired
    private TaskService taskService;


    private static final long  HALF_A_DAY=12;

    public  void validateTasksAndSend(List<NoneExpiredTaskMapper> taskList) {
        NoneExpiredTaskMapper task;

        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
                // if deadline and assign date in row
            if (getDaysBetween(task.getAssignDate(),task.getDeadline()) <= 1) {
                if(compareWithCurrentDate(task.getDeadline(),null,true)){
                    sendEmails(task);
                }
            } else {
                if(compareWithCurrentDate(task.getAssignDate(),task.getDeadline(),false)){
                    sendEmails(task);
                }
            }
        }

    }

    private  boolean compareWithCurrentDate(LocalDateTime deadline, LocalDateTime assignDate, boolean isLessThanDay) {
            // if there is only one day for task isLessThanDay is true otherwise false
        boolean isReadyToNotify=false;
        if(isLessThanDay){
            if(deadline.until(LocalDateTime.now(),HOURS)==HALF_A_DAY){
                isReadyToNotify=true;
            }
        }else{
            // if this time is the mean of deadline and assign date
            if(deadline.until(LocalDateTime.now(),HOURS) == deadline.until(assignDate,HOURS)/2){
                isReadyToNotify=true;
            }
        }
        return isReadyToNotify;
    }

        // get days between two date
    private  long getDaysBetween(LocalDateTime d1, LocalDateTime d2) {
        return d1.until(d2,DAYS);
    }


    private  void sendEmails(NoneExpiredTaskMapper task){
        System.out.println("User email : " + task.getEmail());
        System.out.println("Task header : " + task.getHeader());
        System.out.println("Task assign date : " + task.getAssignDate());
        System.out.println("Task deadline : " + task.getDeadline());
        taskService.setTaskNotified(task.getId());
    }
}
