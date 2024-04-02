package com.vaishnavi.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.vaishnavi.blog.security.JwtAuthenticationEntryPoint;
import com.vaishnavi.blog.security.JwtAuthenticationFilter;
import com.vaishnavi.blog.services.CustomerUserDetailService;

@Configuration
@EnableWebMvc
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
	
	public static final String[] PUBLIC_URLS= {
			"/api/v1/auth/**",
			"/v3/api-docs",
			"/v2/api-docs",
			"/swagger-resources/**",
			"/swagger-ui/**",
			"/webjars/**"
	};
	
	@Autowired
    private JwtAuthenticationEntryPoint point;
    @Autowired
    private JwtAuthenticationFilter filter;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    	
    	//Customizer
    	http.csrf(csrf-> csrf.disable())
    			.cors(cors -> cors.disable())
    			.authorizeHttpRequests(auth-> 
    				auth.antMatchers(PUBLIC_URLS).permitAll()
	    			.antMatchers(HttpMethod.GET).permitAll()
	    			.anyRequest()
	    			.authenticated()
	    		)
    			.exceptionHandling(ex->ex.authenticationEntryPoint(point))
    			.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

    	return http.build();
    }
    
    @Bean
    public DaoAuthenticationProvider doDaoAuthenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(userDetailsService);
    	provider.setPasswordEncoder(passwordEncoder);
    	return provider;
    }
    
	

	
	

}
