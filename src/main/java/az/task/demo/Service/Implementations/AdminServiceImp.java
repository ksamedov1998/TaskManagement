package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.Enums.UserType;
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
    public User getAdminById(int adminId) {
        Optional<User> user=adminRepository.getUserById(adminId);
        if(!user.isPresent()){
            throw new UserNotFound(adminId);
        }
        return user.get();
    }

    @Override
    public List<User> getAdminList() {
        return adminRepository.getAdminList();
    }


    @Override
    public void addUser(String username, String email, String password, int userType, int status) {
        checkUserTypeAndStatus(userType,status);
        adminRepository.saveUser(username,email,password,userType,status);
    }

    private void checkUserTypeAndStatus(int userType, int status) throws StatusNotFoundException{
        if(!UserType.checkType(userType)){
            throw new StatusNotFoundException(status,"USERTYPE");
        }
        if(!UserType.checkType(userType)){
            throw new StatusNotFoundException(status,"USERSTATUS");
        }
    }


    @Override
    public void deleteUserById(int userId) {
        if(adminRepository.deleteUserById(userId, UserStatus.getStatus(UserStatus.DELETED))==0){
            throw new UserNotFound(userId);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }
}
