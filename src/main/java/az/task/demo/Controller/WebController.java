package az.task.demo.Controller;


import az.task.demo.Domains.Firebase.FirebaseUser;
import az.task.demo.Security.SecurityUtil;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class WebController {

    @Autowired
    SecurityUtil securityUtil;

    @GetMapping("/me")
    public void login(@RequestParam("username") String username ,@RequestParam("password") String password) {
        System.out.println("asdasds");
    }


//    @PostMapping("/sign-in")
//    public void signIn(@RequestBody SignInUser signInUser) {
//        securityUtil.setPrinciple(signInUser);
//    }


    @GetMapping("/create/token")
    public String getCustomToken() throws FirebaseAuthException {
        return FirebaseAuth.getInstance().createCustomToken(String.valueOf(securityUtil.getPrincipal().getId()));
    }
}
