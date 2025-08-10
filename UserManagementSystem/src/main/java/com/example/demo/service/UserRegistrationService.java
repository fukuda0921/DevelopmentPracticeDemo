package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRegistrationMapper;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.UserRegistrationEntity;

@Service
public class UserRegistrationService {

	/** ユーザー登録Mapper */
	private final UserRegistrationMapper userRegistrationMapper;

	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userRegistrationMapper
	 * @param userPermissionMapper
	 * @param passwordEncoder
	 */
	public UserRegistrationService(UserRegistrationMapper userRegistrationMapper, PasswordEncoder passwordEncoder) {
		this.userRegistrationMapper = userRegistrationMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * メールアドレスの存在チェックを行う
	 * @param emailAddress 
	 * @throws RuntimeException 
	 */
	public boolean checkEmailExists(String emailAddress) {

		int existingUserCount = userRegistrationMapper.countByAddress(emailAddress);

		return existingUserCount > 0; 

	}

	/**
	 * ユーザー登録処理
	 * 
	 * @param form
	 */
	public void saveUser(UserRegistrationDto form) {

		UserRegistrationEntity entity = new UserRegistrationEntity();
		entity.setName(form.getName());
		entity.setKana(form.getKana());
		entity.setAddress(form.getAddress());
		// パスワードをハッシュ化してから設定
		String hashedPassword = passwordEncoder.encode(form.getPassword());
		entity.setPassword(hashedPassword);
		entity.setRole(form.getRole());
		entity.setDeleteFlag(0);
		//ユーザーテーブルに登録
		userRegistrationMapper.insertUser(entity);

	}

}
