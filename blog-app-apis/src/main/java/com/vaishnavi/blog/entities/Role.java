package com.vaishnavi.blog.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="role")
@Getter
@Setter
@NoArgsConstructor
public class Role {
	
	@Id
	private int roleId;
	
	private String name;

}
