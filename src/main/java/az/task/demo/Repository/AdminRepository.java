package az.task.demo.Repository;

import az.task.demo.Domains.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedNativeQuery;
import java.util.List;

public interface AdminRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.userType = 0")
    List<User> getAdminList();

    User getUserById(int userId);



    @Modifying
    @Query(value = "insert into User(username,email,password,user_type,status) values(:username,:email,:password,:userType,:status)",nativeQuery = true)
    @Transactional
    void saveUser(@Param("username") String username,
                  @Param("email") String email,
                  @Param("password") String password,
                  @Param("userType") int userType,
                  @Param("status") int status);

    void deleteUserById(int userId);

}
