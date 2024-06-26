package com.vaishnavi.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.vaishnavi.blog.services.CustomerUserDetailService;

@Configuration
public class AppConfig {

	@Autowired
	private CustomerUserDetailService customerUserDetailService;
	
	
//	@Bean
//	public UserDetailsService userDetailsService() 
//	{
//		UserDetails user = User.builder().username("VAISHNAVI").password(passwordEncoder().encode("VAISHNAVI")).roles("ADMIN").build();
//		//UserDetails user = User.builder().username("VAISHNAVI").password("VAISHNAVI").roles("ADMIN").build();
//		UserDetails user1 = User.builder().username("VEDANT").password(passwordEncoder().encode("VEDANT")).roles("Normal").build();
//		
//		return new InMemoryUserDetailsManager(user, user1);
//	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
        return builder.getAuthenticationManager();
    }

}
