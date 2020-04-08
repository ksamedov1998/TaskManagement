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
    date = "2020-04-09T01:23:38+0400",
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

    @Override
    public User userDTOtoUser(UserDTO destination) {
        if ( destination == null ) {
            return null;
        }

        User user = new User();

        List<Task> list = destination.getTaskList();
        if ( list != null ) {
            user.setTaskList( new ArrayList<Task>( list ) );
        }
        user.setEmail( destination.getEmail() );
        if ( destination.getUserType() != null ) {
            user.setUserType( destination.getUserType() );
        }
        user.setId( destination.getId() );
        user.setUsername( destination.getUsername() );

        return user;
    }
}
