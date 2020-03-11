package az.task.demo.Controller.Firebase;

import az.task.demo.Domains.Firebase.FirebaseUser;
import az.task.demo.Service.FirebaseService.AdminService;
import com.google.firebase.auth.ExportedUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "firebaseAdminController")
@RequestMapping("/firebase/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;


    @GetMapping("/user")
    public List<ExportedUserRecord> getAllUsers(){
        return adminService.getAllUsers();
    }

    @PostMapping("/create/user")
    public void createUser(@RequestBody FirebaseUser user){
        adminService.createUser(user);
    }

    @GetMapping("get/user/{email}")
    public FirebaseUser getAllUsers(@PathVariable String email){
        System.out.println(email);
        return adminService.getUser(email);
    }

    @GetMapping("delete/user/{email}")
    public void deleteUser(@PathVariable String email){
        adminService.deleteUser(email);
    }

}
