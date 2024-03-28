package com.vaishnavi.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vaishnavi.blog.payloads.ApiResponse;
import com.vaishnavi.blog.payloads.CommentDto;
import com.vaishnavi.blog.services.CommentService;

@RestController
@RequestMapping("/api")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/posts/{postId}/comment")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,@PathVariable Integer postId){
	
		CommentDto comment = this.commentService.createComment(commentDto, postId);
		return new ResponseEntity<CommentDto>(comment, HttpStatus.CREATED);
	}
	
	@PutMapping("/comment/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto,@PathVariable Integer commentId){
		CommentDto updateComment = this.commentService.updateComment(commentDto, commentId);
		return new ResponseEntity<CommentDto>(updateComment, HttpStatus.OK);
	}
	
	@DeleteMapping("/comment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId){
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully", true), HttpStatus.OK);
	}
}
