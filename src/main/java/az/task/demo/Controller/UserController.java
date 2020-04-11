package az.task.demo.Controller;

import az.task.demo.Domains.User;
import az.task.demo.Domains.dto.UserDTO;
import az.task.demo.Domains.mappers.UserToUserDTO;
import az.task.demo.Repository.HibernateRepository;
import az.task.demo.Service.UserService;
import az.task.demo.Util.DynamicQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    private final HibernateRepository hibernateRepository;

    private final UserToUserDTO userToUserDTO;

    public UserController(UserService userService, HibernateRepository hibernateRepository, UserToUserDTO userToUserDTO) {
        this.userService = userService;
        this.hibernateRepository = hibernateRepository;
        this.userToUserDTO = userToUserDTO;
    }

    @GetMapping(value = "/{userID}")
    public UserDTO getUserByID(@PathVariable(value = "userID") int userId){
        return userToUserDTO.userToUserDTO(userService.getUserById(userId));
    }

    @PatchMapping(value = "/update/{userId}")
    public UserDTO addUser(@PathVariable(value = "userId") int id,
                        @RequestBody User user){
        return userToUserDTO.userToUserDTO(userService.updateUser(id,user));
    }
}
