package az.task.demo.Domains.mappers;

import az.task.demo.Domains.Task;
import az.task.demo.Domains.dto.TaskDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TaskToTaskDTO {
        TaskDTO taskToTaskDTO(Task task);
}
