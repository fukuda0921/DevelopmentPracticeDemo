package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.UserEditDto;
import com.example.demo.entity.UserEditEntity;
import com.example.demo.repository.UserEditRepository;
@Service
public class UserEditService {

	@Autowired
	private UserEditRepository userEditRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// ユーザー情報取得
	public UserEditDto findByUserId(Integer targetUserId) {
		UserEditEntity entity = userEditRepository.findByUserId(targetUserId)
				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません（ID: " + targetUserId + "）"));
		return new UserEditDto(entity);
	}

	// DTOからEntityへの変換メソッドを追加
	public UserEditEntity convertToEntity(UserEditDto dto) {
		UserEditEntity entity = new UserEditEntity();
		entity.setUserId(dto.getUserId());
		entity.setName(dto.getName());
		entity.setKana(dto.getKana());
		entity.setAddress(dto.getAddress());
		entity.setPassword(dto.getPassword());
		entity.setRole(dto.getRole());
		return entity;
	}

	// ユーザー情報の更新
	public void updateUser(UserEditEntity userEditEntity) {
		UserEditEntity entity = userEditRepository.findById(userEditEntity.getUserId())
				.orElseThrow(() -> new RuntimeException("対象ユーザーが見つかりません（ID: " + userEditEntity.getUserId() + "）"));

		entity.setName(userEditEntity.getName());
		entity.setKana(userEditEntity.getKana());
		entity.setAddress(userEditEntity.getAddress());
		entity.setRole(userEditEntity.getRole());

		// パスワードが指定されていればハッシュ化して更新
		if (userEditEntity.getPassword() != null && !userEditEntity.getPassword().isEmpty()) {
			String hashedPassword = passwordEncoder.encode(userEditEntity.getPassword());
			entity.setPassword(hashedPassword);
		}

		userEditRepository.save(entity);
	}

	// ユーザー削除
	public void deleteUser(Integer userId) {
		userEditRepository.deleteById(userId);
	}
	
	// 現在のパスワードを取得するメソッド
	public String getCurrentPassword(Integer userId) {
	    return userEditRepository.findById(userId)
	            .map(UserEditEntity::getPassword)
	            .orElseThrow(() -> new RuntimeException("対象ユーザーが見つかりません（ID: " + userId + "）"));
	}
}
