package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ProjectListEntity;
import com.example.demo.repository.ProjectListRepository;

@Service
public class ProjectListService {

	private final ProjectListRepository projectListRepository;

	@Autowired
	public ProjectListService(ProjectListRepository projectListRepository) {
		this.projectListRepository = projectListRepository;
	}

	//案件データ取得
	public List<ProjectListEntity> getAllProjectList() {
		return projectListRepository.findAll();
	}

	//	指定したユーザーIDの案件データを取得
	public List<ProjectListEntity> getProjectListByUserId(Integer userId) {
		return projectListRepository.findByUserId(userId);
	}

	//	案件データを ID で取得
	public Optional<ProjectListEntity> getProjectListById(Integer id) {
		return projectListRepository.findById(id);
	}

}
