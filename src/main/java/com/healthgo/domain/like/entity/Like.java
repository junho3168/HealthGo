package com.healthgo.domain.like.entity;

import com.healthgo.domain.post.entity.Post;
import com.healthgo.domain.user.entity.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Like {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private Post post;

	@ManyToOne
	private User user;
}
