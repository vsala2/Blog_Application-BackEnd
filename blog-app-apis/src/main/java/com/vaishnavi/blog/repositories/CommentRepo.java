package com.vaishnavi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaishnavi.blog.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
