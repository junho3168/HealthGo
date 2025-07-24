package com.healthgo.domain.user.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthgo.domain.user.dto.req.SignupRequest;
import com.healthgo.domain.user.dto.req.UpdateUserRequest;
import com.healthgo.domain.user.dto.res.SignupResponse;
import com.healthgo.domain.user.entity.User;
import com.healthgo.domain.user.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository,
		PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Transactional
	public SignupResponse signup(SignupRequest request) {
		if (userRepository.existsByEmail(request.email())) {
			throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
		}
		if (userRepository.existsByNickname(request.nickname())) {
			throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
		}

		String encodedPassword = passwordEncoder.encode(request.password());

		User user = User.create(
			request.email(),
			encodedPassword,
			request.nickname()
		);

		User savedUser = userRepository.save(user);

		return new SignupResponse(savedUser.getId(), "회원가입 성공");
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
	}

	@Transactional
	public void updateUser(String currentEmail, UpdateUserRequest request) {
		User user = userRepository.findByEmail(currentEmail)
			.orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

		// 이메일 중복 체크 (변경 시)
		if (request.getEmail() != null && !request.getEmail().equals(user.getEmail())) {
			if (userRepository.existsByEmail(request.getEmail())) {
				throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
			}
			user.updateEmail(request.getEmail());
		}

		// 닉네임 중복 체크 (변경 시)
		if (request.getNickname() != null && !request.getNickname().equals(user.getNickname())) {
			if (userRepository.existsByNickname(request.getNickname())) {
				throw new IllegalArgumentException("이미 사용 중인 닉네임입니다.");
			}
			user.updateNickname(request.getNickname());
		}
	}
}