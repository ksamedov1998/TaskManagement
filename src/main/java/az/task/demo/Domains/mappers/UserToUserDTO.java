package az.task.demo.Domains.mappers;

import az.task.demo.Domains.User;
import az.task.demo.Domains.dto.UserDTO;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED,
        componentModel = "spring")
public interface UserToUserDTO {
    UserDTO userToUserDTO(User source);
}
