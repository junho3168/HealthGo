package com.healthgo.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthgo.domain.user.dto.req.SignupRequest;
import com.healthgo.domain.user.dto.res.SignupResponse;
import com.healthgo.domain.user.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/signup")
	public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest request) {
		SignupResponse response = userService.signup(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
