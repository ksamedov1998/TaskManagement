package az.task.demo.Service;

import az.task.demo.Domains.User;

import java.util.List;

public interface UserService {
    User getUserById(int userId);
    List<User> getUserList();
}
