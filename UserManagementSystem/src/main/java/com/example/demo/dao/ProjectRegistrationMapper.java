package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.entity.ProjectRegistrationEntity;

@Mapper
public interface ProjectRegistrationMapper {

	/**
	 * 案件登録
	 *
	 * @param entity 登録する案件情報
	 */
	void addProject(ProjectRegistrationEntity entity);

}
