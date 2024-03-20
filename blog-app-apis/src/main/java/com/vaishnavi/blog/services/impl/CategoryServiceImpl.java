package com.vaishnavi.blog.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaishnavi.blog.entities.Category;
import com.vaishnavi.blog.exceptions.ResourceNotFoundException;
import com.vaishnavi.blog.payloads.CategoryDto;
import com.vaishnavi.blog.repositories.CategoryRepo;
import com.vaishnavi.blog.services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepo categoryRepo;
	 
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		
		Category category = this.dtoToCategory(categoryDto);
		Category savedCategory = this.categoryRepo.save(category);
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryID", categoryId));
		
		category.setCategoryTitle(categoryDto.getCategoryTitle());
		category.setCategoryDescription(categoryDto.getCategoryDescription());
		Category savedCategory = this.categoryRepo.save(category);
		return this.categoryToCategoryDto(savedCategory);
	}

	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryID", categoryId));
		return this.categoryToCategoryDto(category);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = this.categoryRepo.findAll();
		List<CategoryDto> categoriesDto= categories.stream().map(category -> this.categoryToCategoryDto(category)).collect(Collectors.toList());
		return categoriesDto;
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "categoryID", categoryId));
		this.categoryRepo.delete(category);

	}
	
	public Category dtoToCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		return category;
	}
	
	public CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto categoryDto = this.modelMapper.map(category, CategoryDto.class);
		return categoryDto;
	}

}
