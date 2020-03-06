package az.task.demo.Controller;


import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.Log;
import az.task.demo.Domains.User;
import az.task.demo.Service.AdminService;
import az.task.demo.Service.UserService;
import az.task.demo.Util.LogHandler;
import az.task.demo.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private LogHandler logHandler;

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users/type/{user_type}")
    public List<User> getUserListByType(@PathVariable(value = "user_type") int userType){
        return userService.getUserListByType(userType);
    }


    @GetMapping(value = "/users/status//{status}")
    public List<User> getUserListByStatus(@PathVariable(value = "status") int status){
        return userService.getUserListByStatus(status);
    }

    @GetMapping(value = "/{adminID}")
    public User getAdminByID(@PathVariable(value = "adminID") int adminId){
        return adminService.getAdminById(adminId);
    }

    @PostMapping("/add/user")
    public void addUser(@RequestParam(value = "username") String username,
                        @RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password,
                        @RequestParam(value = "type") int userType
    ){
     password=PasswordUtil.encryptPassword(password);
        adminService.addUser(username,email,password,userType, UserStatus.getStatus(UserStatus.ACTIVE));
    }

    @GetMapping("/delete/{userID}")
    public void deleteUser(@PathVariable(value = "userID") int userId){
        Log log = new Log();
        log.setLevel(Level.FINE);
        log.setDescription("User deleted");
        logHandler.publish(log);
        adminService.deleteUserById(userId);
    }




}
