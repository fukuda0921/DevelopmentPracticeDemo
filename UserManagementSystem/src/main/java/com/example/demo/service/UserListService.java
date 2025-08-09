package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserListMapper;
import com.example.demo.entity.UserListEntity;
import com.example.demo.repository.UserListRepository;
@Service
public class UserListService {
	
	private final UserListRepository userListRepository;
	/** ユーザー一覧Mapper */
	private final UserListMapper userListMapper;

	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userListRepository
	 * @param userListMapper ユーザー一覧Mapper
	 */
	public UserListService(UserListRepository userListRepository,UserListMapper userListMapper) {
		this.userListRepository = userListRepository;
		this.userListMapper = userListMapper;
	}

	//勤怠データ取得
	public List<UserListEntity> getAllUserList() {
		return userListMapper.findAll();
	}

	//	指定したユーザーIDの勤怠データを取得
	public List<UserListEntity> getUserListByUserId(Integer userId) {
		return userListRepository.findByUserId(userId);
	}

	//	勤怠データを ID で取得
	public Optional<UserListEntity> getUserListById(Integer id) {
		return userListRepository.findById(id);
	}

//	//	ユーザー情報 全検索
//	public UserListDto searchAll() {
//		
//		// ユーザー情報の取得
//		List<UserListEntity> userList = userListRepository.findAll();
//		UserListDto userListDto = new UserListDto();
//		List<UserListEntity> list = new ArrayList<UserListEntity>();
//		
//		 for(User user : userList) {
//			 
//		 }
	}