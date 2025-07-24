package com.healthgo.domain.user.dto.req;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
	private String nickname;
	private String email;
}
