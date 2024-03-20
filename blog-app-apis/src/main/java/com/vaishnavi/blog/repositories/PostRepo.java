package com.vaishnavi.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaishnavi.blog.entities.Category;
import com.vaishnavi.blog.entities.Post;
import com.vaishnavi.blog.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer> {

	//custom finder methods in repository
	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);
}
