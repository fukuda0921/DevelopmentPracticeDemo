package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.UserDetailEntity;

@Mapper
public interface UserDetailMapper {
	/**
     * 指定されたユーザーIDのユーザー詳細情報を取得
     * @param userId ユーザーID
     * @return ユーザー詳細Entity
     */
    UserDetailEntity findByUserId(@Param("userId") Integer userId);

}
