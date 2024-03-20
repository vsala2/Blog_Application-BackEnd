package com.vaishnavi.blog.services;

import java.util.List;

import com.vaishnavi.blog.payloads.PostDto;

public interface PostService {
	
	//create
	PostDto createPost(PostDto postDto, Integer categoryId, Integer userId);
	
	//update
	PostDto updatePost(PostDto postDto, Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all post
	List<PostDto> getAllPosts();
	
	//get single post
	PostDto getPostById(Integer postId);
	
	//get all post by category 
	List<PostDto> getPostByCategory(Integer categoryId);
	 
	//get all post by user
	List<PostDto> getPostByUser(Integer userId);
	
	//search post
	List<PostDto> searchPosts(String keyword);
	 

}
