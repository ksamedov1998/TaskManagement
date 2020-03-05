package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.User;
import az.task.demo.Repository.AdminRepository;
import az.task.demo.Service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public User getAdminById(int userId) {
        Optional<User> user=adminRepository.getUserById(userId);
        if(!user.isPresent()){
            throw new UserNotFound("User not found");
        }
        return user.get();
    }

    @Override
    public List<User> getAdminList() {
        return adminRepository.getAdminList();
    }


    @Override
    public void addUser(String username, String email, String password, int userType, int status) {
        adminRepository.saveUser(username,email,password,userType,status);
    }


    @Override
    public void deleteUserById(int userId) {
        adminRepository.deleteUserById(userId, UserStatus.getStatus(UserStatus.DELETED));
    }

    @Override
    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }
}
