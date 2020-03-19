package az.task.demo.Controller;


import az.task.demo.Domains.AuthorizationServiceRequestUser;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.Firebase.AuthenticationServiceResponseUser;
import az.task.demo.Domains.Log;
import az.task.demo.Domains.SignInUser;
import az.task.demo.Domains.User;
import az.task.demo.Service.AdminService;
import az.task.demo.Service.UserService;
import az.task.demo.Util.LogHandler;
import az.task.demo.Util.PasswordUtil;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.*;
import java.net.URI;
import java.util.List;
import java.util.logging.Level;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private RestTemplate restTemplate;

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

    @PostMapping(value = "/add/user")
    public void addUser(@RequestBody SignInUser user){

        RequestEntity<SignInUser> requestEntity=RequestEntity
                .post(URI.create("http://localhost:8081/service/add/user"))
                .accept(MediaType.APPLICATION_JSON)
                .body(user);
        ResponseEntity<AuthenticationServiceResponseUser> responseEntity=restTemplate.exchange(requestEntity,AuthenticationServiceResponseUser.class);

        AuthorizationServiceRequestUser requestUser=new AuthorizationServiceRequestUser();
        requestUser.setId(responseEntity.getBody().getId());
        requestUser.setRole(user.getRole());
        RequestEntity<AuthorizationServiceRequestUser> authorizationRequestEntity=RequestEntity
                .post(URI.create("http://localhost:8082/service/autho"))
                .accept(MediaType.APPLICATION_JSON)
                .body(requestUser);
        ResponseEntity<Void> authorizationResponseEntity=restTemplate.exchange(authorizationRequestEntity,Void.class);

    }

    @GetMapping("/delete/{userID}")
    public void deleteUser(@PathVariable(value = "userID") int userId){
        adminService.deleteUserById(userId);
    }



}
