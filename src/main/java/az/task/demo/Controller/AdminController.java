package az.task.demo.Controller;


import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.RequestBodies.UserCreatingRequestBody;
import az.task.demo.Domains.User;
import az.task.demo.Domains.dto.UserDTO;
import az.task.demo.Domains.mappers.UserToUserDTO;
import az.task.demo.Service.AdminService;
import az.task.demo.Service.UserService;
import az.task.demo.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final UserToUserDTO userToUserDTO;
    private static final List<UserDTO> userDTOList = new LinkedList<>();

    public AdminController(AdminService adminService, UserService userService, UserToUserDTO userToUserDTO) {
        this.adminService = adminService;
        this.userService = userService;
        this.userToUserDTO = userToUserDTO;
    }

    @GetMapping(value = "/users/type")
    public ResponseEntity<?> getUserListByType(@RequestParam(value = "user_type") int userType) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>
                (userListToUserDTOList(userService.getUserListByType(userType)), HttpStatus.OK);
        return responseEntity;
    }


    @GetMapping(value = "/users/status")
    public ResponseEntity<?> getUserListByStatus(@RequestParam(value = "status") int status) {
        ResponseEntity<?> responseEntity = new ResponseEntity<>
                ((userListToUserDTOList(userService.getUserListByStatus(status))), HttpStatus.OK);
        return responseEntity;
    }


    @PostMapping(value = "/add/user")
    public void addUser(@RequestBody UserCreatingRequestBody userCreatingRequestBody) {
        adminService.addUser(userCreatingRequestBody);
    }

    @DeleteMapping("/delete/{userID}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userID") int userId) {
        adminService.deleteUserById(userId);
        ResponseEntity<?> responseEntity = new ResponseEntity<>
                (HttpStatus.OK);
        return responseEntity;
    }


    private List<UserDTO> userListToUserDTOList(List<User> userList) {
        userDTOList.clear();
        userList.
                forEach(user -> userDTOList.add(userToUserDTO.userToUserDTO(user)));
        return userDTOList;
    }

}
