package com.vaishnavi.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaishnavi.blog.entities.User;
import com.vaishnavi.blog.exceptions.ResourceNotFoundException;
import com.vaishnavi.blog.repositories.UserRepo;

//This class is authenticating the username from the DB
@Service
public class CustomerUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//loading user from DB by Username
		User user = this.userRepo.findByUserEmail(username).orElseThrow(()-> new ResourceNotFoundException("User", "email : "+ username, 0));
		
		return user;
	}

}
