package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.ProjectListEntity;
import com.example.demo.security.LoginUserDetails;
import com.example.demo.service.ProjectListService;

@Controller
public class ProjectListController {

	/** 案件一覧Service */
	private final ProjectListService projectListService;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param projectListService
	 */
	public ProjectListController(ProjectListService projectListService) {
		this.projectListService = projectListService;
	}

	/**
	 * 
	 * @param userId
	 * @param model
	 * @param loginUser
	 * @return
	 */
	@GetMapping("/userDetail/projectList/{userId}")
	public String showProjectList(@PathVariable Integer userId, Model model, LoginUserDetails loginUser) {

		List<ProjectListEntity> projectList = projectListService.getProjectListByUserId(userId);
		model.addAttribute("projectList", projectList);
		model.addAttribute("userId", userId);
		return "projectList";

	}

}
