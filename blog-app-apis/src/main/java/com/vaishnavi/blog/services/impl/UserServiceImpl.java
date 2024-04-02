 package com.vaishnavi.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vaishnavi.blog.config.AppConstants;
import com.vaishnavi.blog.entities.Role;
import com.vaishnavi.blog.entities.User;
import com.vaishnavi.blog.exceptions.ResourceNotFoundException;
import com.vaishnavi.blog.payloads.UserDto;
import com.vaishnavi.blog.repositories.RoleRepo;
import com.vaishnavi.blog.repositories.UserRepo;
import com.vaishnavi.blog.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		//user.setUserPassword(passwordEncoder.encode(user.getPassword()));
		user.setUserPassword(passwordEncoder.encode(user.getPassword()));
		
		Role role = this.roleRepo.findById(AppConstants.ADMIN_USER).get();
		
		user.getRoles().add(role);
		
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		user.setUserName(userDto.getUserName());
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(userDto.getUserPassword());
		user.setAbout(userDto.getAbout());
		
		
		User updatedUser= this.userRepo.save(user);
		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUser(Integer userId) {
		
		User user= this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		
		List<User> users = this.userRepo.findAll();
		
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);

	}
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		
		User user = this.dtoToUser(userDto);
		
		//encoded password 
		user.setUserPassword(this.passwordEncoder.encode(user.getPassword())); 
		
		//role
		
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		
		user.getRoles().add(role);
		
		User newUser = this.userRepo.save(user);
		
		return this.userToDto(newUser);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto, User.class);
//		user.setUserId(userDto.getUserId());
//		user.setUserName(userDto.getUserName());
//		user.setUserEmail(userDto.getUserEmail());
//		user.setUserPassword(userDto.getUserPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto= this.modelMapper.map(user, UserDto.class);
//		userDto.setUserId(user.getUserId());
//		userDto.setUserName(user.getUserName());
//		userDto.setUserEmail(user.getUserEmail());
//		userDto.setUserPassword(user.getUserPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

}
