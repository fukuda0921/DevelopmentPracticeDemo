package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserPermissionMapper;
import com.example.demo.dao.UserRegistrationMapper;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.entity.UserPermission;
import com.example.demo.entity.UserRegistrationEntity;

@Service
public class UserRegistrationService {

	/** ユーザー登録Mapper */
	private final UserRegistrationMapper userRegistrationMapper;
	
	/** ユーザー権限Mapper */
	private final UserPermissionMapper userPermissionMapper;
	
	/** パスワードエンコーダー */
	private final PasswordEncoder passwordEncoder;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userRegistrationMapper
	 * @param userPermissionMapper
	 * @param passwordEncoder
	 */
	public UserRegistrationService(UserRegistrationMapper userRegistrationMapper,UserPermissionMapper userPermissionMapper,
			PasswordEncoder passwordEncoder) {
		this.userRegistrationMapper = userRegistrationMapper;
		this.userPermissionMapper = userPermissionMapper;
		this.passwordEncoder = passwordEncoder;
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

		//ユーザーテーブルに登録
		userRegistrationMapper.insertUser(entity);

		//登録後に自動生成されたuserIdを取得
		Integer registeredUserId = entity.getUserId();

		if (registeredUserId == null) {
			throw new RuntimeException("ユーザー登録後にユーザーIDが取得できませんでした。");
		}

		UserPermission permissionEntity = new UserPermission();
		permissionEntity.setUserId(registeredUserId); // 登録されたユーザーのIDをセット
		permissionEntity.setRole(form.getRole()); // DTOからロールを取得

		//user_permission_tblテーブルを更新
		userPermissionMapper.insertUserPermission(permissionEntity);

	}

}
