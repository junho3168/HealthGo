package com.healthgo.domain.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthgo.domain.security.UserDetailsImpl;
import com.healthgo.domain.user.dto.req.SignupRequest;
import com.healthgo.domain.user.dto.req.UpdateUserRequest;
import com.healthgo.domain.user.dto.res.SignupResponse;
import com.healthgo.domain.user.dto.res.UserInfoResponse;
import com.healthgo.domain.user.entity.User;
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

	@GetMapping("/me")
	public ResponseEntity<UserInfoResponse> getMyInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		User user = userService.findByEmail(userDetails.getUsername());
		return ResponseEntity.ok(new UserInfoResponse(user));
	}

	@PutMapping
	public ResponseEntity<UserInfoResponse> updateUserInfo(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@RequestBody UpdateUserRequest request) {

		User updatedUser = userService.updateUser(userDetails.getUsername(), request);
		UserInfoResponse response = new UserInfoResponse(updatedUser);

		return ResponseEntity.ok(response);
	}
}
