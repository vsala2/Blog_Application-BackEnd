package com.vaishnavi.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaishnavi.blog.payloads.ApiResponse;
import com.vaishnavi.blog.payloads.CategoryDto;
import com.vaishnavi.blog.services.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	//Post- create category
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategoryDto = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategoryDto, HttpStatus.CREATED);
		
	}
	
	//Put - update category
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		CategoryDto updateCategoryDto = this.categoryService.updateCategory(categoryDto, categoryId);
		return new ResponseEntity<CategoryDto>(updateCategoryDto, HttpStatus.OK);
	}
	
	//Delete - delete category
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully!", true), HttpStatus.OK);		
		
	}
	
	//get - single category
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId) {
		CategoryDto getCategory = this.categoryService.getCategory(categoryId);
		return new ResponseEntity<CategoryDto>(getCategory, HttpStatus.OK);		
	}	
	
	//get- all category
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return new ResponseEntity<List<CategoryDto>>(this.categoryService.getAllCategory(),HttpStatus.OK);
	}
	
	
	
	

}
