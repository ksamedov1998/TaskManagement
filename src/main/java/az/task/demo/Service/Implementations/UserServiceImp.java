package az.task.demo.Service.Implementations;

import az.task.demo.CustomExceptions.StatusNotFoundException;
import az.task.demo.CustomExceptions.UserNotFound;
import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.User;
import az.task.demo.Repository.UserRepository;
import az.task.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

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
        if(UserStatus.checkState(userStatus)){
            userList=userRepository.findAllByStatusEquals(userStatus);
        }else{
            throw new StatusNotFoundException(userStatus,"USERSTATUS");
        }
        return userList;
    }

    @Override
    public List<User> getUserListByType(int userType) {
        List<User> userList;
        if(UserStatus.checkState(userType)){
            userList=userRepository.findAllByStatusEquals(userType);
        }else{
            throw new StatusNotFoundException(userType,"USERTYPE");
        }
        return userList;
    }


}
