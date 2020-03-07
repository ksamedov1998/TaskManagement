package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.LogBuilder;
import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.Enums.UserType;
import az.task.demo.Domains.User;
import az.task.demo.Repository.AdminRepository;
import az.task.demo.Service.AdminService;
import az.task.demo.Util.LogCreator;
import az.task.demo.Util.LogHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
@Transactional
public class AdminServiceImp implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private LogHandler logHandler;

    @Override
    public User getAdminById(int adminId) {
        Optional<User> user=adminRepository.getUserById(adminId);
        if(!user.isPresent()){
            logHandler.publish(new LogBuilder()
                                        .setPoint("AdminServiceImp.getAdminById")
                                        .setException("UserNotFoundException")
                                        .setLevel(Level.INFO.getName())
                                        .setState("FAIL")
                                        .build()
                                        );
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
            logHandler.publish(new LogBuilder()
                    .setPoint("AdminServiceImp.addUser")
                    .setException("StatusNotFoundException")
                    .setDescription("UserType is not correct")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new StatusNotFoundException(status,"USERTYPE");
        }
        if(!UserStatus.checkStatus(status)){
            logHandler.publish(new LogBuilder()
                    .setPoint("AdminServiceImp.addUser")
                    .setException("StatusNotFoundException")
                    .setDescription("UserStatus is not correct")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new StatusNotFoundException(status,"USERSTATUS");
        }
    }


    @Override
    public void deleteUserById(int userId) {
        if(adminRepository.deleteUserById(userId, UserStatus.getStatus(UserStatus.DELETED))==0){
            logHandler.publish(new LogBuilder()
                    .setPoint("AdminServiceImp.deleteUserById")
                    .setException("UserNotFound")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new UserNotFound(userId);
        }
    }

    @Override
    public List<User> getAllUsers() {
        return adminRepository.findAll();
    }
}
