package az.task.demo.Schedule;

import az.task.demo.Domains.NoneExpiredTaskMapper;
import az.task.demo.Domains.Task;
import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MailSender {
    private TaskService service;
    private final  int MIN_MAIL_SIZE=10;

    @Autowired
    public MailSender(TaskService service) {
        this.service = service;
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void sendEmail() {
        List<NoneExpiredTaskMapper> taskList= service.allNoneExpiredTaskList();
        if(taskList.size()>0/*MIN_MAIL_SIZE*/){
            sendEmails(taskList);
        }
    }

    private void sendEmails(List<NoneExpiredTaskMapper> taskList) {
//        todo logic here if one day send it at 12 hour otherwise the half of interval
        NoneExpiredTaskMapper taskMapper=taskList.get(0);
        if(){

        }
        System.out.println("Send email - > " + taskMapper.getEmail()
                            +"Task header - > "+ taskMapper.getHeader()
                            +"Task assignDate - > "+taskMapper.getAssignDate()
                            +"Task deadline - > " + taskMapper.getDeadline());
    }

}
