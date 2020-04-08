package az.task.demo.Controller;


import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.RequestBodies.UserCreatingRequestBody;
import az.task.demo.Domains.User;
import az.task.demo.Service.AdminService;
import az.task.demo.Service.UserService;
import az.task.demo.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }


    @GetMapping(value = "/users")
    public List<User> getUserListByType(@RequestParam(value = "user_type") int userType) {
        return userService.getUserListByType(userType);
    }


    @GetMapping(value = "/users}")
    public List<User> getUserListByStatus(@PathVariable(value = "status") int status) {
        return userService.getUserListByStatus(status);
    }

    @GetMapping(value = "/{adminID}")
    public User getAdminByID(@PathVariable(value = "adminID") int adminId) {
        return adminService.getAdminById(adminId);
    }

    @PostMapping(value = "/add/user")
    public void addUser(@RequestBody UserCreatingRequestBody userCreatingRequestBody) {
        adminService.addUser(userCreatingRequestBody);
    }

    @DeleteMapping("/delete/{userID}")
    public void deleteUser(@PathVariable(value = "userID") int userId) {
        adminService.deleteUserById(userId);
    }


}
