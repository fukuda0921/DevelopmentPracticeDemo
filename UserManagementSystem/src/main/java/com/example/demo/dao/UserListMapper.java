package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserListEntity;

@Mapper
public interface UserListMapper {
	
	/**
     * 全てのユーザーリストを取得します。
     * @return ユーザー情報のリスト
     */
    List<UserListEntity> findAll();
    

}
