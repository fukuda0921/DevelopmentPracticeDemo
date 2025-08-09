package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserRegistrationEntity;

@Mapper
public interface UserRegistrationMapper {
	
	/**
     * ユーザー情報を登録
     * @param entity ユーザーエンティティ
     * @return 挿入されたレコードの数
     */
    int insertUser(UserRegistrationEntity entity);

}
