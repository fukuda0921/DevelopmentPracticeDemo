package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.UserEditEntity;

@Mapper
public interface UserEditMapper {

	/**
	 * 指定したユーザーIDのユーザー情報取得
	 *
	 * @param targetUserId 検索するユーザーID
	 */
	List<UserEditEntity> findByUserId(Integer targetUserId);

	/**
	 * 指定したユーザーID以外で同じメールアドレス存在するかチェック
	 * 
	 * @param address
	 * @param userId
	 * @return
	 */
	int countByAddressAndExcludeUserId(@Param("address") String address, @Param("userId") Integer userId);

	/**
	 * ユーザー情報を更新
	 * 
	 * @param userEditEntity
	 */
	void updateUser(UserEditEntity userEditEntity);

	/**
	 * 指定したユーザーIDの情報を削除する（delete_flagを1に更新）
	 * 
	 * @param userId
	 */
	void deleteById(@Param("userId") Integer userId);

}
