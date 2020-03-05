package az.task.demo.Service.Implementations;

import az.task.demo.Domains.Enums.TaskState;
import az.task.demo.Domains.Enums.TaskStatus;
import az.task.demo.Domains.Task;
import az.task.demo.Repository.TaskRepository;
import az.task.demo.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional
public class TaskServiceImp implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public void addTask(String header,String description,String assignDateStr,String deadlineStr) {
        LocalDate assignDate=StringToLocalDateConverter(assignDateStr); //can throw Runtime exception
        LocalDate deadlineDate=StringToLocalDateConverter(deadlineStr); //can throw Runtime exception
        if(isAssignDateAfterDeadline(assignDate,deadlineDate)){
            taskRepository.save(header,description,assignDate,deadlineDate, TaskState.getState(TaskState.NOT_ASSIGNED),TaskStatus.getStatus(TaskStatus.ACTIVE));
        }else{
            System.out.println("Wrong");
        }
    }

    @Override
    public void deleteTask(int taskId) {
        taskRepository.deleteTaskById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTask(int taskId) {
        return taskRepository.getTaskById(taskId);
    }

    @Override
    public void assignTaskToUser(int taskId, int userId) {
            taskRepository.assignTaskToUser(taskId,userId);
    }

    @Override
    public void updateDeadline(int taskId,String newDeadline) {
        LocalDate assignDate=StringToLocalDateConverter(newDeadline); //can throw Runtime exception
        taskRepository.updateTaskDeadline(taskId,assignDate);
    }

    @Override
    public void updateTaskState(int taskId,int taskState) {
        if(TaskState.checkState(taskState)){
            taskRepository.updateTaskState(taskId,taskState);
        }else{
//            throw Exception
        }

    }

    private boolean isAssignDateAfterDeadline(LocalDate assignDate, LocalDate deadlineDate) {
        boolean isAfter=false;
        if(deadlineDate.isAfter(assignDate)){
            isAfter=true;
        }
        return isAfter;
    }

    private LocalDate StringToLocalDateConverter(String date){
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
