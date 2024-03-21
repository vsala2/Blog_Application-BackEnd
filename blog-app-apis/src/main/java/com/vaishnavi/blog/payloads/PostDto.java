package com.vaishnavi.blog.payloads;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
	
	private int postId;
	private String postTitle;
	private String postContent;
	private String imageName;
	private Date addedDate;
	private CategoryDto category;
	private UserDto user;
	
}
