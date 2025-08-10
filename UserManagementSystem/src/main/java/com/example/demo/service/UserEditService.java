package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dao.UserEditMapper;
import com.example.demo.dto.UserEditDto;
import com.example.demo.entity.UserEditEntity;

@Service
public class UserEditService {

	/** ユーザー編集・削除Mapper */
	private final UserEditMapper userEditMapper;

	/** パスワードエンコーダー */
	private PasswordEncoder passwordEncoder;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userEditMapper
	 * @param passwordEncoder
	 */
	public UserEditService(UserEditMapper userEditMapper, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.userEditMapper = userEditMapper;

	}

	/**
	 * ユーザー情報取得
	 * 
	 * @param targetUserId
	 * @return
	 */
	public List<UserEditDto> findByUserId(Integer targetUserId) {

		List<UserEditEntity> entityList = userEditMapper.findByUserId(targetUserId);

		//リストが空かチェック
		if (entityList.isEmpty()) {
			throw new RuntimeException("ユーザーが見つかりません（ID: " + targetUserId + "）");
		}

		// List<UserEditEntity>をList<UserEditDto>に変換
		List<UserEditDto> dtoList = new ArrayList<>();
		for (UserEditEntity entity : entityList) {
			dtoList.add(new UserEditDto(entity));
		}

		return dtoList;
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

	/**
	 * ユーザー情報の更新
	 * 
	 * @param userEditEntity
	 */
	public void updateUser(UserEditEntity userEditEntity) {

		if (StringUtils.hasText(userEditEntity.getPassword())) {
			// パスワードが入力されていればハッシュ化
			userEditEntity.setPassword(passwordEncoder.encode(userEditEntity.getPassword()));
		} else {
			// パスワードが入力されていなければ、現在の（エンコード済みの）パスワードをDBから取得して設定
			String currentPassword = getCurrentPassword(userEditEntity.getUserId());
			userEditEntity.setPassword(currentPassword);
		}

		userEditMapper.updateUser(userEditEntity);
	}

	/**
	 * ユーザー削除
	 * 
	 * @param userId
	 */
	public void deleteUser(Integer userId) {
		userEditMapper.deleteById(userId);
	}

	/**
	 * 現在のパスワードを取得するメソッド
	 * 
	 * @param userId
	 * @return
	 */
	public String getCurrentPassword(Integer userId) {
		String password = userEditMapper.findByIdGetPassword(userId);

		// 対象ユーザーが見つからなかった場合（パスワードがnullの場合）に例外をスロー
		if (password == null) {
			throw new RuntimeException("対象ユーザーが見つかりません（ID: " + userId + "）");
		}
		return password;
	}
}
