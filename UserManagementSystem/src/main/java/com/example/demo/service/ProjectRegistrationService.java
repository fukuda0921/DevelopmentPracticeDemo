package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.ProjectRegistrationMapper;
import com.example.demo.dto.ProjectRegistrationDto;
import com.example.demo.entity.ProjectRegistrationEntity;

@Service
public class ProjectRegistrationService {

	/** 案件登録Mapper */
	private final ProjectRegistrationMapper projectRegistrationMapper;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param projectRegistrationMapper
	 */
	public ProjectRegistrationService(ProjectRegistrationMapper projectRegistrationMapper) {
		this.projectRegistrationMapper = projectRegistrationMapper;
	}

	/**
	 * 案件登録処理
	 * 
	 * @param form
	 */
	public void saveProject(ProjectRegistrationDto form) {
		ProjectRegistrationEntity entity = new ProjectRegistrationEntity();
		entity.setUserId(form.getUserId());
		entity.setProjectName(form.getProjectName());
		entity.setRemoteAvailability(form.getRemoteAvailability());
		entity.setWorkLocation(form.getWorkLocation());
		entity.setJobDescription(form.getJobDescription());
		entity.setProgrammingLanguage(form.getProgrammingLanguage());
		entity.setRemarks(form.getRemarks());
		entity.setDeleteFlag(0);

		projectRegistrationMapper.addProject(entity);
	}

}
