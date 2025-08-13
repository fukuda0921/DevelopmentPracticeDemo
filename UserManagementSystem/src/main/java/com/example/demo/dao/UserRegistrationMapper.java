package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.UserRegistrationEntity;

@Mapper
public interface UserRegistrationMapper {

	/**
	 * ユーザー情報を登録
	 * @param entity ユーザーエンティティ
	 * @return 挿入されたレコードの数
	 */
	int insertUser(UserRegistrationEntity entity);
	
	/**
	 * 既にメールアドレスが存在するかチェック
	 * 
	 * @param address
	 * @return
	 */
	int countByAddress(@Param("address") String address);

}
