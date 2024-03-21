package com.vaishnavi.blog.controllers;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vaishnavi.blog.payloads.ApiResponse;
import com.vaishnavi.blog.payloads.PostDto;
import com.vaishnavi.blog.payloads.PostResponse;
import com.vaishnavi.blog.services.PostService;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	//create Post
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer categoryId,@PathVariable Integer userId)
	{
		PostDto createPost = this.postService.createPost(postDto, categoryId, userId);
		return new ResponseEntity<PostDto>(createPost, HttpStatus.CREATED);
	}
	
	//Get Post by Category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "2", required = false) Integer pageSize)
	{
		PostResponse getPostByCategory = this.postService.getPostByCategory(categoryId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(getPostByCategory, HttpStatus.OK);
	}
	
	//Get Post by User
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(
			@PathVariable Integer userId,
			@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "2", required = false) Integer pageSize)
	{
		PostResponse getPostByUser = this.postService.getPostByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(getPostByUser, HttpStatus.OK);
	}
	
	//Get All Post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "2", required = false) Integer pageSize)
	{
		PostResponse allPosts = this.postService.getAllPosts(pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(allPosts, HttpStatus.OK);
	}
	
//	public ResponseEntity<List<PostDto>> getAllPost(
//			@RequestParam(value = "pageNumber",defaultValue = "1", required = false) Integer pageNumber,
//			@RequestParam(value = "pageSize",defaultValue = "2", required = false) Integer pageSize)
//	{
//		List<PostDto> allPosts = this.postService.getAllPosts(pageNumber, pageSize);
//		return new ResponseEntity<List<PostDto>>(allPosts, HttpStatus.OK);
//	}
	
	//Get By Post ID
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) 
	{
		PostDto post = this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(post, HttpStatus.OK);
	}
	
	//Update Post
	@PutMapping("/post/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId)
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost, HttpStatus.OK);
	}
	
	//delete post
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted successfully", true), HttpStatus.OK);
	}
}
