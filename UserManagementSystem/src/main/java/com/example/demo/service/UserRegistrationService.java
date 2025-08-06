package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.UserRegistrationEntity;
import com.example.demo.repository.UserRegistrationRepository;

@Service
public class UserRegistrationService {
	//	ユーザー情報（リポジトリ）
	@Autowired
	private final UserRegistrationRepository userRegistrationRepository;

	private final PasswordEncoder passwordEncoder;

	public UserRegistrationService(UserRegistrationRepository userRegistrationRepository,
			PasswordEncoder passwordEncoder) {
		this.userRegistrationRepository = userRegistrationRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void saveUser(UserRegistrationDto form) {
		UserRegistrationEntity entity = new UserRegistrationEntity();
//		entity.setUserId(form.getUserId());
		entity.setName(form.getName());
		entity.setKana(form.getKana());
		entity.setAddress(form.getAddress());

		// パスワードをハッシュ化してから設定
		String hashedPassword = passwordEncoder.encode(form.getPassword());
		entity.setPassword(hashedPassword);
		entity.setRole(form.getRole());

		userRegistrationRepository.save(entity);
	}

}
