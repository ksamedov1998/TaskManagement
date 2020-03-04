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

    User getUserAndTaskById(int userId);



    @Modifying
    @Query(value = "insert into User(username,email,password,user_type,status) values(:username,:email,:password,:userType,:status)",nativeQuery = true)
    @Transactional
    void saveUser(@Param("username") String username,
                  @Param("email") String email,
                  @Param("password") String password,
                  @Param("userType") int userType,
                  @Param("status") int status);

    @Modifying
    @Query(value = "update into User(status) values(:status) where User.id= :id",nativeQuery = true)
    @Transactional
    void deleteUserById(@Param(value = "id") int userId,
                        @Param(value ="status") int deletedStatus);

}
