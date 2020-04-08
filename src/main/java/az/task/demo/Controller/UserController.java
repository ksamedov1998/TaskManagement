package az.task.demo.Controller;

import az.task.demo.Domains.User;
import az.task.demo.Domains.dto.UserDTO;
import az.task.demo.Domains.mappers.UserToUserDTO;
import az.task.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserToUserDTO userToUserDTO;

    @GetMapping(value = "/{userID}")
    public UserDTO getUserByID(@PathVariable(value = "userID") int userId){
        return userToUserDTO.userToUserDTO(userService.getUserById(userId));
    }

    @PatchMapping(value = "/update/{userId}")
    public void addUser(@PathVariable(value = "userId") int id,
                        @RequestBody User user){
        userService.updateUser(id,user);
    }
}
