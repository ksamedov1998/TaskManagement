package az.task.demo.Security;

import az.task.demo.Domains.Firebase.FirebaseUser;
import com.google.api.client.util.SecurityUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class TokenFilter extends OncePerRequestFilter {

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
            String idToken = securityUtil.getTokenFromRequest(httpServletRequest);
            FirebaseToken decodedToken = null;
            if (idToken != null) {
                try {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                } catch (FirebaseAuthException e) {
                    System.out.println(e.getErrorCode());
                }
            }
            if (decodedToken != null) {
                FirebaseUser user = new FirebaseUser();
                user.setId(decodedToken.getUid());
                user.setUsername(decodedToken.getName());
                user.setEmail(decodedToken.getEmail());
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
                        decodedToken, null);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    }

