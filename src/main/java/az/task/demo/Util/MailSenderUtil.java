package az.task.demo.Util;

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
        NoneExpiredTaskMapper taskMapper;
        for (int i = 0; i < taskList.size(); i++) {
            taskMapper = taskList.get(i);
            if (getDaysBetween(taskMapper.getAssignDate(),taskMapper.getDeadline()) <= 1) {
                if(compareWithCurrentDate(taskMapper.getDeadline(),null,true)){
                    sendEmails(taskMapper);
                }
            } else {
                if(compareWithCurrentDate(taskMapper.getAssignDate(),taskMapper.getDeadline(),false)){
                    sendEmails(taskMapper);
                }
            }
        }

    }

    private  boolean compareWithCurrentDate(LocalDateTime deadline, LocalDateTime assignDate, boolean isLessThanDay) {
        boolean isReadyToNotify=false;
        if(isLessThanDay){
            if(deadline.until(LocalDateTime.now(),HOURS)==HALF_A_DAY){
                isReadyToNotify=true;
            }
        }else{
            if(deadline.until(LocalDateTime.now(),HOURS) == deadline.until(assignDate,HOURS)/2){
                isReadyToNotify=true;
            }
        }
        return isReadyToNotify;
    }

    private  long getDaysBetween(LocalDateTime d1, LocalDateTime d2) {
        return d1.until(d2,DAYS);
    }


    private  void sendEmails(NoneExpiredTaskMapper taskMapper){
        System.out.println("User email : " + taskMapper.getEmail());
        System.out.println("Task header : " + taskMapper.getHeader());
        System.out.println("Task assign date : " + taskMapper.getAssignDate());
        System.out.println("Task deadline : " + taskMapper.getDeadline());
        taskService.setTaskNotified(taskMapper.getId());
    }
}
