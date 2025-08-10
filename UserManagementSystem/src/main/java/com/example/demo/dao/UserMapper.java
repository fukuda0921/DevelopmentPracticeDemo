package com.example.demo.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.User;

@Mapper
public interface UserMapper {

	/**
	 * user_id を使って user_tbl からユーザー情報を取得します。
	 *
	 * @param userId 検索するユーザーID (Integer型)
	 * @return 該当するユーザー情報を含むOptional
	 */
	Optional<User> findByUserId(Integer userId);

}
