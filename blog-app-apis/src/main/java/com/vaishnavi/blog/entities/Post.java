package com.vaishnavi.blog.entities;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vaishnavi.blog.payloads.CommentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@Getter
@Setter
@NoArgsConstructor
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(nullable = false, length = 100)
	private String postTitle;
	
	@Column(length = 10000, nullable = false)
	private String postContent;
	
	private String imageName;
	
	private Date addedDate;
	
	@ManyToOne
	@JoinColumn(name = "categoryId")
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments; //one post can have many comments
}
