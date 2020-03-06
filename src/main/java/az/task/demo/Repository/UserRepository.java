package az.task.demo.Repository;

import az.task.demo.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> getUserById(int userId);

    List<User> findAllByStatusEquals(int userStatus);

    List<User> findAllByUserTypeEquals(int userType);
}
