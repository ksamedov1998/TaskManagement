//package az.task.demo.Security;
//
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class TokenFilter extends OncePerRequestFilter {
//
//    @Autowired
//    private SecurityUtil securityUtil;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//            String idToken = securityUtil.getTokenFromRequest(httpServletRequest);
//            FirebaseToken decodedToken = null;
//        System.out.println(idToken);
//        System.out.println("snth");
//            if (idToken != null) {
//                try {
//                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
//                    if (decodedToken != null) {
//                        System.out.println(decodedToken);
//                        FirebaseUser user = new FirebaseUser();
//                        user.setId(decodedToken.getUid());
//                        user.setUsername(decodedToken.getName());
//                        user.setEmail(decodedToken.getEmail());
//                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
//                                decodedToken, null);
//                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
//                        SecurityContextHolder.getContext().setAuthentication(authentication);
//                    }
//                } catch (FirebaseAuthException e) {
//                    System.out.println(e.getErrorCode());
//                }
//            }
//
//        filterChain.doFilter(httpServletRequest,httpServletResponse);
//    }
//
//    }
//
