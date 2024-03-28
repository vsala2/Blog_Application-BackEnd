 package com.vaishnavi.blog.payloads;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private int userId;
	
	@NotEmpty
	@Size(min=4, message = "UserName must be min of 4 characters!")
	private String userName;
	
	@Email(message = "Email is not Valid!")
	private String userEmail;
	
	@NotEmpty
	@Size(min = 4, max = 10, message = "Password must be min of 4 and max of 10 characters")
	private String userPassword;
	
	@NotEmpty
	private String about;
	

}
