package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserEditMapper;
import com.example.demo.dto.UserEditDto;
import com.example.demo.entity.UserEditEntity;

@Service
public class UserEditService {

	/** ユーザー編集・削除Mapper */
	private final UserEditMapper userEditMapper;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userEditMapper
	 * @param passwordEncoder
	 */
	public UserEditService(UserEditMapper userEditMapper) {
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

	/**
	 * 画面に入力された値を保持しているDTOからEntityへの変換メソッドを追加
	 * 
	 * @param dto
	 * @return
	 */
	public UserEditEntity convertToEntity(UserEditDto dto) {
		UserEditEntity entity = new UserEditEntity();
		entity.setUserId(dto.getUserId());
		entity.setName(dto.getName());
		entity.setKana(dto.getKana());
		entity.setAddress(dto.getAddress());
		entity.setRole(dto.getRole());
		return entity;
	}
	

	/**
     * 指定したユーザーID以外,メールアドレスが既に登録されているかをチェック
     * @param address 
     * @param userId 
     * @return 既に存在すれば true、存在しなければ false
     */
    public boolean isEmailDuplicateForOtherUsers(String address, Integer userId) { 
    	
        int count = userEditMapper.countByAddressAndExcludeUserId(address, userId);
        
        return count > 0;
    }
	
	/**
	 * ユーザー情報の更新
	 * 
	 * @param userEditEntity
	 */
	public void updateUser(UserEditEntity userEditEntity) {

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
}
