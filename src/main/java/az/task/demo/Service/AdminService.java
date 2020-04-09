package az.task.demo.Service;

import az.task.demo.Domains.RequestBodies.UserCreatingRequestBody;
import az.task.demo.Domains.User;

import java.util.List;

public interface AdminService {
    void addUser(UserCreatingRequestBody userCreatingRequestBody);
    void deleteUserById(int id);
}
