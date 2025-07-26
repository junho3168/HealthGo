package com.healthgo.domain.post.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthgo.domain.post.dto.req.CreatePostRequest;
import com.healthgo.domain.post.dto.res.PostResponse;
import com.healthgo.domain.post.service.PostService;
import com.healthgo.domain.security.UserDetailsImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

	private final PostService postService;

	@PostMapping
	public ResponseEntity<PostResponse> createPost(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody CreatePostRequest request) {
		return ResponseEntity.ok(
			postService.createPost(userDetails.getUsername(), request));
	}

	@GetMapping
	public ResponseEntity<List<PostResponse>> getAllPosts() {
		return ResponseEntity.ok(postService.getAllPosts());
	}

	@GetMapping("/{postId}")
	public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
		return ResponseEntity.ok(postService.getPost(postId));
	}
}
