package com.vaishnavi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaishnavi.blog.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
