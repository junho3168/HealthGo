package com.healthgo.domain.post.dto.res;

import com.healthgo.domain.post.entity.Post;

public class PostResponse {
	private Long id;
	private String title;
	private String content;
	private String author;
	private int viewCount;
	private int likeCount;
	private int commentCount;

	public PostResponse(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.content = post.getContent();
		this.author = post.getAuthor().getNickname();
		this.viewCount = post.getViewCount();
		this.likeCount = post.getLikes().size();
		this.commentCount = post.getComments().size();
	}
}
