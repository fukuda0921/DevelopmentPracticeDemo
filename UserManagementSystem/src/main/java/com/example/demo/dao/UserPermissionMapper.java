package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserPermissionEntity;

@Mapper
public interface UserPermissionMapper {

	/**
	 * 新規ユーザー権限情報登録
	 * @param entity ユーザー権限エンティティ
	 * @return 挿入されたレコードの数
	 */
	int insertUserPermission(UserPermissionEntity entity);

}
