package com.healthgo.domain.post.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthgo.domain.post.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
