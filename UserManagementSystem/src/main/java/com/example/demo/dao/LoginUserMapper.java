package com.example.demo.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserEntity;

@Mapper
public interface LoginUserMapper {

	/**
	 * user_id を使って user_tbl からユーザー情報を取得
	 *
	 * @param userId 検索するユーザーID
	 * @return 該当するユーザー情報
	 */
	Optional<UserEntity> findByUserId(Integer userId);

}
