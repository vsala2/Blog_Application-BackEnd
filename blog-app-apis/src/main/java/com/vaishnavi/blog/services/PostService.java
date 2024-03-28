package com.vaishnavi.blog.services;

import java.util.List;

import com.vaishnavi.blog.payloads.PostDto;
import com.vaishnavi.blog.payloads.PostResponse;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
	//List<PostDto> getAllPosts(Integer pageNumber, Integer pageSize);
	PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by category 
	PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize);
	 
	//get all post by user
	PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize);
	
	//search post
	List<PostDto> searchPosts(String keyword);
	 

}
