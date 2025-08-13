package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.ProjectListEntity;

@Mapper
public interface ProjectListMapper {

	/**
	 * 指定したユーザーIDの案件取得
	 *
	 * @param userIdユーザーのID
	 * @return 案件情報
	 */
	List<ProjectListEntity> findByUserId(Integer userId);

}
