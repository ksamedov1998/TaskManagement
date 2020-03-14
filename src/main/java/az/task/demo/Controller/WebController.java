package az.task.demo.Controller;


import az.task.demo.Domains.Firebase.SignInResponse;
import az.task.demo.Domains.SignInUser;
import az.task.demo.Util.SecurityConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@RestController
@RequestMapping("")
public class WebController {

//    @Autowired
//    SecurityUtil securityUtil;

    @GetMapping("/me")
    public void login(@RequestParam("email") String email ,@RequestParam("password") String password) {
    }


    @PostMapping("/sign-in")
    public void signIn(@RequestBody SignInUser signInUser, HttpServletResponse response) {
        RestTemplate restTemplate = new RestTemplate();
        try {
            RequestEntity<String> requestEntity= RequestEntity.post(URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAXpj6DU7x9eAz_S_AgU_Guaq_0chySwzc"))
                    .body(new ObjectMapper().writeValueAsString(signInUser));
            ResponseEntity<SignInResponse> signInResponseResponseEntity=restTemplate.exchange(requestEntity, SignInResponse.class);
            System.out.println(signInResponseResponseEntity.getBody());
            response.addHeader(SecurityConstants.TOKEN_HEADER, SecurityConstants.TOKEN_PREFIX+signInResponseResponseEntity.getBody().getIdToken());
            System.out.println(signInResponseResponseEntity.getBody().getIdToken());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


}
