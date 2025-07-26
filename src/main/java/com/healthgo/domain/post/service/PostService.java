package com.healthgo.domain.post.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.healthgo.domain.post.dto.req.CreatePostRequest;
import com.healthgo.domain.post.dto.res.PostResponse;
import com.healthgo.domain.post.entity.Post;
import com.healthgo.domain.post.repository.PostRepository;
import com.healthgo.domain.user.entity.User;
import com.healthgo.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;
	private final UserRepository userRepository;

	public PostResponse createPost(String nickname, CreatePostRequest request) {
		User user = userRepository.findByNickname(nickname)
			.orElseThrow(() -> new UsernameNotFoundException("유저 없음"));

		Post post = new Post();
		post.setTitle(request.getTitle());
		post.setContent(request.getContent());
		post.setAuthor(user);
		postRepository.save(post);

		return new PostResponse(post);
	}

	public List<PostResponse> getAllPosts() {
		return postRepository.findAll().stream()
			.map(PostResponse::new)
			.collect(Collectors.toList());
	}

	public PostResponse getPost(Long postId) {
		Post post = postRepository.findById(postId)
			.orElseThrow(() -> new IllegalArgumentException("게시글 없음"));
		post.setViewCount(post.getViewCount() + 1);
		return new PostResponse(post);
	}
}
