package az.task.demo.Domains.mappers;

import az.task.demo.Domains.Task;
import az.task.demo.Domains.User;
import az.task.demo.Domains.dto.UserDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-04-11T18:03:52+0400",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.6 (JetBrains s.r.o)"
)
@Component
public class UserToUserDTOImpl implements UserToUserDTO {

    @Override
    public UserDTO userToUserDTO(User source) {
        if ( source == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( source.getId() );
        userDTO.setUserType( source.getUserType() );
        List<Task> list = source.getTaskList();
        if ( list != null ) {
            userDTO.setTaskList( new ArrayList<Task>( list ) );
        }
        userDTO.setUsername( source.getUsername() );
        userDTO.setEmail( source.getEmail() );

        return userDTO;
    }
}
