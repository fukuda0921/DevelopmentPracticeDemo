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
		
		List<ProjectListEntity> list =projectListMapper.findByUserId(userId);
		
		for (ProjectListEntity project : list) {
	        project.setRemoteAvailabilityText(convertRemoteAvailability(project.getRemoteAvailability()));
	    }

	    return list;
	}
	
	/**
	 * 変換処理
	 * 
	 * @param code
	 * @return
	 */
	private String convertRemoteAvailability(String code) {
	    if (code == null) return "未設定";

	    switch (code) {
	        case "1": return "フルリモート";
	        case "2": return "リモート併用";
	        case "3": return "常駐";
	        default: return "未設定";
	    }
	}

}
