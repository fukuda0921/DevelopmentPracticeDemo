package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectListMapper;
import com.example.demo.entity.ProjectListEntity;

@Service
public class ProjectListService {

	/** 案件一覧Mapper */
	private final ProjectListMapper projectListMapper;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param projectListRepository
	 * @param projectListMapper
	 */
	public ProjectListService(ProjectListMapper projectListMapper) {
		this.projectListMapper = projectListMapper;
	}

	/**
	 * 指定したユーザーIDの案件データを取得
	 * 
	 * @param userId
	 * @return
	 */
	public List<ProjectListEntity> getProjectListByUserId(Integer userId) {
		return projectListMapper.findByUserId(userId);
	}

}
