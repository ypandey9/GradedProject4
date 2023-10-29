//package com.gl.springboot.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import com.gl.springboot.security.service.UserDetailsServiceImpl;
//
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//@Bean
//public UserDetailsService userDetailsService() {
//	return new UserDetailsServiceImpl();
//}
//	
////	 @Bean
////	    public UserDetailsService userDetailsService() {
////	        return new UserDetailsService() {
////	            @Override
////	            public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////	                if ("yogesh".equals(username)) {
////	                    return User.withUsername(username)
////	                            .password(passwordEncoder().encode("password"))
////	                            .roles("USER", "ADMIN")
////	                            .build();
////	                }
////	                throw new UsernameNotFoundException("User not found");
////	            }
////	        };
////	    }
//
//@Bean
//public BCryptPasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();
//}
//
//@Bean
//public DaoAuthenticationProvider authenticationProvider()
//{
//	DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//	authProvider.setUserDetailsService(userDetailsService());
//	authProvider.setPasswordEncoder(passwordEncoder());
//	return authProvider;
//}
//
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//	auth.authenticationProvider(authenticationProvider());
//}
//
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//	http.authorizeRequests()
//	.antMatchers(HttpMethod.GET,"/api/employees").hasAnyRole("USER","ADMIN")
//	.antMatchers(HttpMethod.POST,"/api/employees").hasAnyRole("ADMIN")
//	.antMatchers(HttpMethod.PUT,"/api/employees/**").hasAnyRole("ADMIN")
//	.antMatchers(HttpMethod.DELETE,"/api/employees/**").hasAnyRole("ADMIN")
//	.anyRequest()
//	.authenticated()
//.and()
//	.formLogin()
//.and()
//	.httpBasic()
//.and()
//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//}
//
//
//	
//}
