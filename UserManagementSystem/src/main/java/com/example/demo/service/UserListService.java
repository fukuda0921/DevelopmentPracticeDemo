package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserListMapper;
import com.example.demo.entity.UserListEntity;

@Service
public class UserListService {

	/** ユーザー一覧Mapper */
	private final UserListMapper userListMapper;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userListRepository
	 * @param userListMapper ユーザー一覧Mapper
	 */
	public UserListService(UserListMapper userListMapper) {
		this.userListMapper = userListMapper;
	}

	/**
	 * 人員情報取得
	 * 
	 * @return
	 */
	public List<UserListEntity> getAllUserList() {

		List<UserListEntity> userList = userListMapper.findAll();

		return userList;
	}

}