package az.task.demo.Repository;

import az.task.demo.Domains.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Query("select u from User u where u.userType = 0")
    List<User> getAdminList();

    Optional<User> getUserById(int userId);


    @Modifying(flushAutomatically =true,clearAutomatically = true)
    @Query(value = "insert into User(username,email,password,user_type,status) values(:username,:email,:password,:userType,:status)",nativeQuery = true)
    @Transactional
    void saveUser(@Param("username") String username,
                  @Param("email") String email,
                  @Param("password") String password,
                  @Param("userType") int userType,
                  @Param("status") int status);

    @Modifying
    @Query(value = "update User set status=:status where User.id= :id",nativeQuery = true)
    @Transactional
    int deleteUserById(@Param(value = "id") int userId,
                        @Param(value ="status") int deletedStatus);

//    fix
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update User set username=:username , email=:email, password=:password where id= :id",nativeQuery = true)
    int update(int id,String username,String email,String password);
}
