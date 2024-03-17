package com.vaishnavi.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
	
	private int userId;
	private String userName;
	private String userEmail;
	private String userPassword;
	private String about;
	

}
