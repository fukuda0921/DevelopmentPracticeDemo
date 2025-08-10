package com.example.demo.dao;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.UserEntity;

@Mapper
public interface LoginUserMapper {

	/**
	 * user_id を使って user_tbl からユーザー情報を取得します。
	 *
	 * @param userId 検索するユーザーID (Integer型)
	 * @return 該当するユーザー情報を含むOptional
	 */
	Optional<UserEntity> findByUserId(Integer userId);

}
