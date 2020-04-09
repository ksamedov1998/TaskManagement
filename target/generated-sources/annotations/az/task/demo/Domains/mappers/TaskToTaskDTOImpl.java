package az.task.demo.Domains.mappers;

import az.task.demo.Domains.Comment;
import az.task.demo.Domains.Task;
import az.task.demo.Domains.dto.TaskDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-09T15:02:45+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class TaskToTaskDTOImpl implements TaskToTaskDTO {

    @Override
    public TaskDTO taskToTaskDTO(Task task) {
        if ( task == null ) {
            return null;
        }

        TaskDTO taskDTO = new TaskDTO();

        taskDTO.setId( task.getId() );
        taskDTO.setHeader( task.getHeader() );
        taskDTO.setDescription( task.getDescription() );
        taskDTO.setAssignDate( task.getAssignDate() );
        taskDTO.setDeadline( task.getDeadline() );
        List<Comment> list = task.getCommentList();
        if ( list != null ) {
            taskDTO.setCommentList( new ArrayList<Comment>( list ) );
        }

        return taskDTO;
    }
}
