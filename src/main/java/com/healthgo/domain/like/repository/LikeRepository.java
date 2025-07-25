package com.healthgo.domain.like.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthgo.domain.like.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

}
