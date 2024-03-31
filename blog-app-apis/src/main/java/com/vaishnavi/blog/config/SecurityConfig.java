package com.vaishnavi.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.vaishnavi.blog.security.CustomerUserDetailService;
import com.vaishnavi.blog.security.JwtAuthenticationEntryPoint;
import com.vaishnavi.blog.security.JwtAuthenticationFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	
    	//Customizer
    	http.csrf(csrf-> csrf.disable())
    			.cors(cors -> cors.disable())
    			.authorizeHttpRequests(auth-> auth.antMatchers("/api/v1/auth/login").permitAll()
    			.antMatchers(HttpMethod.GET).permitAll()
    			.anyRequest()
    			.authenticated())
    			.exceptionHandling(ex->ex.authenticationEntryPoint(point))
    			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    	return http.build();
    }
    
    
	

	
	

}
