package com.healthgo.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthgo.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
