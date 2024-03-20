package com.vaishnavi.blog.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaishnavi.blog.entities.Category;
import com.vaishnavi.blog.entities.Post;
import com.vaishnavi.blog.entities.User;
import com.vaishnavi.blog.exceptions.ResourceNotFoundException;
import com.vaishnavi.blog.payloads.PostDto;
import com.vaishnavi.blog.repositories.CategoryRepo;
import com.vaishnavi.blog.repositories.PostRepo;
import com.vaishnavi.blog.repositories.UserRepo;
import com.vaishnavi.blog.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer categoryId, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		
		Post post =  this.dtoToPost(postDto);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post savedPost= this.postRepo.save(post);
		return this.postToDto(savedPost);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		post.setPostTitle(postDto.getPostTitle());
		
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<PostDto> getAllPosts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//Dto to Post
	public Post dtoToPost(PostDto postDto) {
		
		Post post = this.modelMapper.map(postDto, Post.class);
		return post;
	}
	
	//post to Dto
	public PostDto postToDto(Post post) {
		
		PostDto postDto = this.modelMapper.map(post, PostDto.class);
		return postDto;
	}

}
