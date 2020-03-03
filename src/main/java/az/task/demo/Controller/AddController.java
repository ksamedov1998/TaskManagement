package az.task.demo.Controller;


import az.task.demo.Domains.User;
import az.task.demo.Domains.UserType;
import az.task.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/add")
public class AddController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    public void addUser(@RequestParam(value = "username") String username,
                        @RequestParam(value = "email") String email,
                        @RequestParam(value = "password") String password
                        ){

//        create User do smth
        User user= new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(BCrypt.hashpw(password,BCrypt.gensalt()));
        System.out.println(user);
       userService.addUser(new User());
    }

    @RequestMapping("/admin/{admin}")
    public void addAdmin(@PathVariable(value = "admin") String username){
        userService.addUser(new User(username,UserType.getStatus(UserType.ADMIN)));
    }
}
