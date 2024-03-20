package com.vaishnavi.blog.payloads;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
	
	private int categoryId;
	
	@NotEmpty
	@Size(min = 5,max = 30,message = "Invalid title: min length is 5 and max length is 30!")
	private String categoryTitle;
	
	@Size(max = 100, message = "Max Length is 100!")
	private String categoryDescription;
	
	
}
