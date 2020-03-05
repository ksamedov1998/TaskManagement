package az.task.demo.Service.Implementations;

import az.task.demo.Domains.Enums.UserStatus;
import az.task.demo.Domains.Enums.UserType;
import az.task.demo.Domains.User;
import az.task.demo.Repository.UserRepository;
import az.task.demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(int userId) {
        return userRepository.getUserById(userId);
    }

    @Override
    public List<User> getUserList() {
        return userRepository.findAllByUserTypeEqualsAndStatusEquals(UserType.getStatus(UserType.USER),UserStatus.getStatus(UserStatus.ACTIVE));
    }


}
