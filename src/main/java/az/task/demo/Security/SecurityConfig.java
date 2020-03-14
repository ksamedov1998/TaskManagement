package az.task.demo.Security;

//@Configuration
//@Order(2)
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Bean
//    public TokenFilter tokenAuthenticationFilter() {
//        return new TokenFilter();
//    }
//
//
//    @Bean
//    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
//        return (httpServletRequest, httpServletResponse, e) -> {
//            Map<String, Object> errorObject = new HashMap<String, Object>();
//            int errorCode = 401;
//            errorObject.put("message", "Access Denied");
//            errorObject.put("error", HttpStatus.UNAUTHORIZED);
//            errorObject.put("code", errorCode);
//            errorObject.put("timestamp", new Timestamp(new Date().getTime()));
//            httpServletResponse.setContentType("application/json;charset=UTF-8");
//            httpServletResponse.setStatus(errorCode);
//            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
//        };
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().csrf().disable().formLogin().disable().httpBasic().disable().exceptionHandling()
//                .authenticationEntryPoint(restAuthenticationEntryPoint()).and().authorizeRequests()
//                .antMatchers("/admin/**").authenticated()
//                .anyRequest().permitAll();
//        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//    }
//}
