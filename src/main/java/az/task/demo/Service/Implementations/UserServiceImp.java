package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.LogBuilder;
import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.User;
import az.task.demo.Repository.HibernateRepository;
import az.task.demo.Repository.UserRepository;
import az.task.demo.Service.UserService;
import az.task.demo.Util.DynamicQueryUtil;
import az.task.demo.Util.LogHandler;
import az.task.demo.Util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HibernateRepository hibernateRepository;

    @Autowired
    private LogHandler logHandler;

    @Override
    public User getUserById(int userId) {
        Optional<User> user=userRepository.getUserById(userId);
        if(!user.isPresent()){
            throw new UserNotFound(userId);
        }
        return user.get();
    }

    @Override
    public List<User> getUserListByStatus(int userStatus) {
        List<User> userList;
        if(UserStatus.checkStatus(userStatus)){
            userList=userRepository.findAllByStatusEquals(userStatus);
        }else{
            throw new StatusNotFoundException(userStatus,"USERSTATUS");
        }
        return userList;
    }

    @Override
    public List<User> getUserListByType(int userType) {
        List<User> userList;
        if(UserStatus.checkStatus(userType)){
            userList=userRepository.findAllByStatusEquals(userType);
        }else{
            throw new StatusNotFoundException(userType,"USERTYPE");
        }
        return userList;
    }

    @Override
    public void updateUser(int userId,User user) {
        user.setPassword(PasswordUtil.encryptPassword(user.getPassword()));
        String query= DynamicQueryUtil.createQuery(userId,user);
        if(!hibernateRepository.update(query)){
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


}
