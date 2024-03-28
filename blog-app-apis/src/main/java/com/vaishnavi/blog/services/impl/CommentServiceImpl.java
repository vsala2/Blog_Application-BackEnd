package com.vaishnavi.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vaishnavi.blog.entities.Comment;
import com.vaishnavi.blog.entities.Post;
import com.vaishnavi.blog.exceptions.ResourceNotFoundException;
import com.vaishnavi.blog.payloads.CommentDto;
import com.vaishnavi.blog.repositories.CommentRepo;
import com.vaishnavi.blog.repositories.PostRepo;
import com.vaishnavi.blog.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepo postRepo;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "postId", postId));
		
		Comment comment = this.dtoToComment(commentDto);
		comment.setPost(post);
		Comment savedComment = this.commentRepo.save(comment);
		
		return this.commentToDto(savedComment);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
		
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));
		
		comment.setContent(commentDto.getContent());
		Comment savedComment = this.commentRepo.save(comment);
		return this.commentToDto(savedComment);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "commentId", commentId));

		this.commentRepo.delete(comment);

	}
	
	public Comment dtoToComment(CommentDto commentDto) {
		Comment comment = this.modelMapper.map(commentDto, Comment.class);
		return comment;
	}
	
	public CommentDto commentToDto(Comment comment) {
		CommentDto commentDto = this.modelMapper.map(comment, CommentDto.class);
		return commentDto;
	}

}
