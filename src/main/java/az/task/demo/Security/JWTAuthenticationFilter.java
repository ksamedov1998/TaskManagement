package az.task.demo.Security;

import az.task.demo.Domains.Firebase.SignInResponse;
import az.task.demo.Domains.SignInUser;
import az.task.demo.Util.SecurityConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.http.SecurityHeaders;
import org.springframework.web.client.RestTemplate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private ObjectMapper objectMapper=new ObjectMapper();


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        setFilterProcessesUrl("/me");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("TEST");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationToken;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SignInUser user=new SignInUser();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        ResponseEntity<SignInResponse> responseEntity=null;
        FirebaseAuth auth = FirebaseAuth.getInstance();
        RestTemplate restTemplate=new RestTemplate();
        try {
            RequestEntity<String> requestEntity= RequestEntity.post(URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyAXpj6DU7x9eAz_S_AgU_Guaq_0chySwzc"))
                    .body(objectMapper.writeValueAsString(user));
            responseEntity=restTemplate.exchange(requestEntity,SignInResponse.class);
            System.out.println(responseEntity.getBody().getIdToken());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        response.addHeader(SecurityConstants.TOKEN_HEADER,SecurityConstants.TOKEN_PREFIX+responseEntity.getBody().getIdToken());

    }


}
