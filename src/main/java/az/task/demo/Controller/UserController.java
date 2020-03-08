package az.task.demo.Controller;

import az.task.demo.Domains.User;
import az.task.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userID}")
    public User getUserByID(@PathVariable(value = "userID") int userId){
        return userService.getUserById(userId);
    }

    @PostMapping(value = "/update/{userId}")
    public void addUser(@PathVariable(value = "userId") int id,
                        @RequestBody User user){
        userService.updateUser(id,user);
    }
}
