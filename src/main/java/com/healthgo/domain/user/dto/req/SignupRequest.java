package com.healthgo.domain.user.dto.req;

public record SignupRequest(
	String email,
	String password,
	String nickname
) {
}
