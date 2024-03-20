package com.vaishnavi.blog.services;

import java.util.List;

import com.vaishnavi.blog.payloads.CategoryDto;

public interface CategoryService {
	
	CategoryDto createCategory(CategoryDto categoryDto);
	
	CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
	
	CategoryDto getCategory(Integer categoryId);
	
	List<CategoryDto> getAllCategory();
	
	void deleteCategory(Integer categoryId);

}
