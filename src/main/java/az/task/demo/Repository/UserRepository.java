package az.task.demo.Repository;

import az.task.demo.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    User getUserById(int userId);

    @Query("select u from User u where u.userType =1")
    List<User> getAllUsers();

}
