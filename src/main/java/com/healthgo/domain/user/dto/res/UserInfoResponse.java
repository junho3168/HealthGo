package com.healthgo.domain.user.dto.res;

import com.healthgo.domain.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {
	private String email;
	private String nickname;

	public UserInfoResponse(User user) {
		this.email = user.getEmail();
		this.nickname = user.getNickname();
	}
}
