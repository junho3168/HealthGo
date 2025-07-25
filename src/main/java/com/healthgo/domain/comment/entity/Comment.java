package com.healthgo.domain.comment.entity;

import java.time.LocalDateTime;

import com.healthgo.domain.post.entity.Post;
import com.healthgo.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Post post;

	@ManyToOne
	private User author;

	private String content;
	private LocalDateTime createdAt;
}
