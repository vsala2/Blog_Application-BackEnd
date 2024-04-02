package com.vaishnavi.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vaishnavi.blog.entities.Role;

public interface RoleRepo extends JpaRepository<Role, Integer> {

}
