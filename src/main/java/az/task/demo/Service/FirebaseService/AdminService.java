package az.task.demo.Service.FirebaseService;

import az.task.demo.Domains.Firebase.FirebaseUser;
import az.task.demo.Domains.User;
import az.task.demo.Repository.Firebase.AdminRepository;
import com.google.firebase.auth.ExportedUserRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service("firebaseAdminService")
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;


    public List<ExportedUserRecord> getAllUsers(){
        List<ExportedUserRecord> userRecords=new ArrayList<>();
        adminRepository.getAllUsers().forEach(record->userRecords.add(record));
        return userRecords;
    }


    public void createUser(FirebaseUser user){
         adminRepository.createUser(user);
    }

    public FirebaseUser getUser(String email){
        return adminRepository.getUser(email);
    }

    public void deleteUser(String email){
        adminRepository.deleteUser(email);
    }

}
