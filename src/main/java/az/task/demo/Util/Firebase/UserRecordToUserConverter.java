package az.task.demo.Util.Firebase;

import az.task.demo.Domains.Firebase.FirebaseUser;
import az.task.demo.Domains.User;
import com.google.firebase.auth.UserRecord;

public class UserRecordToUserConverter {
    private static FirebaseUser user= new FirebaseUser();

    public static FirebaseUser convertToUser(UserRecord record){
        user.setId(record.getUid());
        user.setUsername(record.getDisplayName());
        user.setEmail(record.getEmail());
        return user;
    }


}
