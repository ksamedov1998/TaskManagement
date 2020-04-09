package az.task.demo.Repository;

import az.task.demo.Domains.RequestBodies.UserCreatingRequestBody;
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

@Transactional
public interface AdminRepository extends JpaRepository<User,Integer>, JpaSpecificationExecutor<User> {

    @Modifying(flushAutomatically =true,clearAutomatically = true)
    @Query(value = "insert into User( username,email,password,user_type,status) values(:#{#body.username}," +
                                                                                      ":#{#body.email}," +
                                                                                        ":#{#body.password}," +
                                                                                       ":#{#body.userType}," +
                                                                                      ":#{#body.userStatus})",nativeQuery = true)
    int saveUser(UserCreatingRequestBody body);

    @Modifying
    @Query(value = "update User set status=:status where User.id= :id",nativeQuery = true)
    int deleteUserById(@Param(value = "id") int userId,
                        @Param(value ="status") int deletedStatus);

}
