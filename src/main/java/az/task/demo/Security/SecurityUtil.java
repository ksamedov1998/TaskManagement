package az.task.demo.Security;

import az.task.demo.Domains.Firebase.FirebaseUser;
import az.task.demo.Domains.Firebase.SignInResponse;
import az.task.demo.Domains.SignInUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@Component
public class SecurityUtil {

    @Autowired
    HttpServletRequest request;

    @Autowired
    ObjectMapper objectMapper;


    public String getTokenFromRequest(HttpServletRequest request) {
        String token = null;
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                token = bearerToken.substring(7, bearerToken.length());
            }
        return token;
    }

    public FirebaseUser getPrincipal() {
        FirebaseUser userPrincipal = null;
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        System.out.println(principal);
        if (principal instanceof FirebaseUser) {
            userPrincipal = ((FirebaseUser) principal);
        }
        return userPrincipal;
    }
//
//    public void/* setPrinciple(SignInUser signInUser) {
//        FirebaseAuth auth=FirebaseAuth.getInstance();
//        RestTemplate restTemplate=new RestTemplate();
//        try {
//            String body=objectMapper.writeValueAsString(signInUser);
//            RequestEntity<String> requestEntity= RequestEntity.post(URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAXpj6DU7x9eAz_S_AgU_Guaq_0chySwzc"))
//                    .body(body);
//            ResponseEntity<SignInResponse> responseEntity=restTemplate.exchange(requestEntity,SignInResponse.class);
//            System.out.println(responseEntity.getBody().getIdToken());
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//    }*/
}
