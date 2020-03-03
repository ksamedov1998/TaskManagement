package az.task.demo.Controller;

import az.task.demo.Domains.User;
import az.task.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @GetMapping(value = "/admin")
    public List<User> getUsersWithAdminPrivilage(){
        return userService.getAdmins();
    }

    @GetMapping(value = "{userID}")
    public User getUserByID(@PathVariable(value = "userID") int userId){
        return userService.getUserById(userId);
    }
}
