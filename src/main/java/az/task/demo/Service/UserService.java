package az.task.demo.Service;

import az.task.demo.Domains.User;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UserService {
    User getUserById(int userId);
    List<User> getUserListByStatus(int userStatus);
    List<User> getUserListByType(int userType);
    User updateUser(int id, User user);
}
