package com.healthgo.domain.auth.service;

import java.util.NoSuchElementException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.healthgo.domain.auth.dto.req.LoginRequest;
import com.healthgo.domain.auth.jwt.JwtProvider;
import com.healthgo.domain.user.entity.User;
import com.healthgo.domain.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;

	public String login(LoginRequest request) {
		User user = userRepository.findByEmail(request.getEmail())
			.orElseThrow(() -> new NoSuchElementException("유저를 찾을 수 없습니다."));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return jwtProvider.generateToken(user.getEmail()); // or userId
	}
}
