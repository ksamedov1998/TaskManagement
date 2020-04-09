package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.LogBuilder;
import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.Enums.UserType;
import az.task.demo.Domains.RequestBodies.UserCreatingRequestBody;
import az.task.demo.Domains.User;
import az.task.demo.Repository.AdminRepository;
import az.task.demo.Service.AdminService;
import az.task.demo.Util.LogHandler;
import az.task.demo.Util.PasswordUtil;
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
    public void addUser(UserCreatingRequestBody body) {
        body.setUserStatus(UserStatus.ACTIVE.getValue());
        body.setPassword(PasswordUtil.encryptPassword(body.getPassword()));
        checkUserType(body.getUserType());
        adminRepository.saveUser(body);
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



    private void checkUserType(int userType) throws StatusNotFoundException{
        if(!UserType.checkType(userType)){
            logHandler.publish(new LogBuilder()
                    .setPoint("AdminServiceImp.addUser")
                    .setException("StatusNotFoundException")
                    .setDescription("UserType is not correct")
                    .setLevel(Level.INFO.getName())
                    .setState("FAIL")
                    .build()
            );
            throw new StatusNotFoundException(userType,"USERTYPE");
        }
    }

}
