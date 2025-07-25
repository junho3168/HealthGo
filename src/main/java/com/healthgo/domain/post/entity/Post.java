package com.healthgo.domain.post.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.healthgo.domain.comment.entity.Comment;
import com.healthgo.domain.like.entity.Like;
import com.healthgo.domain.user.entity.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post {
	@Id
	@GeneratedValue
	private Long id;

	private String title;
	private String content;

	private int viewCount = 0;

	@ManyToOne
	private User author;

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	@OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private List<Like> likes = new ArrayList<>();

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
