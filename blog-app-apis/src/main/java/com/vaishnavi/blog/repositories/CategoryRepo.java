package com.vaishnavi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaishnavi.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
