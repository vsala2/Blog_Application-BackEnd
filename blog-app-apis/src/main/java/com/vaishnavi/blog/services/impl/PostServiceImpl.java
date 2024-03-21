package com.vaishnavi.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.vaishnavi.blog.entities.Category;
import com.vaishnavi.blog.entities.Post;
import com.vaishnavi.blog.entities.User;
import com.vaishnavi.blog.exceptions.ResourceNotFoundException;
import com.vaishnavi.blog.payloads.PostDto;
import com.vaishnavi.blog.payloads.PostResponse;
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
		post.setPostContent(postDto.getPostContent());
		post.setImageName(postDto.getImageName());
		Post updatedPost = this.postRepo.save(post);
		return this.postToDto(updatedPost);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {
		
//		int pageSize = 2;
//		int pageNumber = 1;
		
		//object of pageable using PageRequest
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		//It will return page of post of data type post
		Page<Post> pagePost = this.postRepo.findAll(p);
		
		//using getContent we will get a original list of post from that page.
		List<Post> allPosts = pagePost.getContent();
		List<PostDto> allPostDtos = allPosts.stream().map(post-> this.postToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allPostDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPages(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		
		return this.postToDto(post);
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId, Integer pageNumber, Integer pageSize) {

		//WITHOUT PAGINATION
//		//get the category using the categoryId passed
//		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
//		
//		//using the category we will get a list of post with the category
//		List<Post> posts = this.postRepo.findByCategory(category);
//		
//		//however we want to return postDto 
//		List<PostDto> postDtos = posts.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
//		return postDtos;
		
		//pagination
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		//get the category using the categoryId passed
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		
		//using the category we will get a list of post with the category
		//List<Post> posts = this.postRepo.findByCategory(category);
		
		//with pagination
		Page<Post> pagePostsByCat = this.postRepo.findByCategory(category, p);
		
		List<Post> allPostByCat = pagePostsByCat.getContent();
		
		//however we want to return postDto 
		List<PostDto> allPostByCatDto = allPostByCat.stream().map(post -> this.postToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(allPostByCatDto);
		postResponse.setPageNumber(pagePostsByCat.getNumber());
		postResponse.setPageSize(pagePostsByCat.getSize());
		postResponse.setTotalElements(pagePostsByCat.getTotalElements());
		postResponse.setTotalPages(pagePostsByCat.getTotalPages());
		postResponse.setLastPages(pagePostsByCat.isLast());

		return postResponse;
	}

	@Override
	public PostResponse getPostByUser(Integer userId, Integer pageNumber, Integer pageSize) {

		//WITHOUT PAGINATION
//		//get the user of the userId
//		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
//		
//		//using the user we will get a list of post with the user removed above
//		List<Post> posts = this.postRepo.findByUser(user);
//		
//		List<PostDto> postDtos = posts.stream().map(post-> this.postToDto(post)).collect(Collectors.toList());
//		return postDtos;
		
		//WITH PAGINATION
		Pageable p = PageRequest.of(pageNumber, pageSize);
		
		User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		
		Page<Post> pagePostUser = this.postRepo.findByUser(user, p);
		
		List<Post> postByUser = pagePostUser.getContent();
		
		List<PostDto> postByUserDtos = postByUser.stream().map(post-> this.postToDto(post)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postByUserDtos);
		postResponse.setPageNumber(pagePostUser.getNumber());
		postResponse.setPageSize(pagePostUser.getSize());
		postResponse.setTotalElements(pagePostUser.getTotalElements());
		postResponse.setTotalPages(pagePostUser.getTotalPages());
		postResponse.setLastPages(pagePostUser.isLast());

		return postResponse;
		
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
