package az.task.demo.Repository.Firebase;

import az.task.demo.Domains.Firebase.FirebaseUser;
import az.task.demo.Util.Firebase.UserRecordToUserConverter;
import com.google.firebase.auth.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Repository("firebaseAdminRepository")
public class AdminRepository {
    private FirebaseAuth auth;

    public Iterable<ExportedUserRecord> getAllUsers(){
        ListUsersPage usersPage;
        Iterable<ExportedUserRecord> iterableUser=null;
        try {
            usersPage= auth.listUsers(null);
            iterableUser=usersPage.getValues();
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        return iterableUser;
    }

    public void createUser(FirebaseUser user){
        UserRecord.CreateRequest createRequest=new UserRecord.CreateRequest();
        createRequest.setDisplayName(user.getUsername());
        createRequest.setEmail(user.getEmail());
        createRequest.setPassword(user.getPassword());
        try {
            auth.createUser(createRequest);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }

    public FirebaseUser getUser(String email){
        UserRecord userRecord=null;
        try {
            userRecord=auth.getUserByEmail(email);
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
        return UserRecordToUserConverter.convertToUser(userRecord);
    }

    public void deleteUser(String email){
        FirebaseUser user=getUser(email);
        try {
            auth.deleteUser(user.getId());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }
    }
}
