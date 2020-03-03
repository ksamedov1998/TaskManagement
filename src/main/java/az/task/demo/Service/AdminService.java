package az.task.demo.Service;

import az.task.demo.Domains.User;

import java.util.List;

public interface AdminService {
    User getAdminById(int userId);
    List<User> getAdminList();
    void addUser(String username,String email,String password,int userType, int status);
    void deleteUserById(int id);
}
