package az.task.demo.Controller;


import az.task.demo.Domains.SignInUser;
import az.task.demo.Domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @Autowired
    private RestTemplate restTemplate;


    @PostMapping("/sign-in")
    public void signIn(@RequestBody SignInUser signInUser) {
        RequestEntity<SignInUser> requestEntity=RequestEntity
                    .post(URI.create("http://localhost:8081/service/authenticate"))
                    .accept(MediaType.APPLICATION_JSON)
                    .body(signInUser);
        ResponseEntity<User> responseEntity=restTemplate.exchange(requestEntity, User.class);
    }


}
