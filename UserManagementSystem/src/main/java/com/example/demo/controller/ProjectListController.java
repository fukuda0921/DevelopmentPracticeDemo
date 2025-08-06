package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.ProjectListEntity;
import com.example.demo.service.ProjectListService;

@Controller
public class ProjectListController {
	
	private final ProjectListService projectListService;

	@Autowired
	public ProjectListController(ProjectListService projectListService) {
		this.projectListService = projectListService;
	}
	
//	 一覧表示
	@GetMapping("/userDetail/projectList/{userId}")
	public String showProjectList(@PathVariable Integer userId, Model model) {
		List<ProjectListEntity> projectList = projectListService.getProjectListByUserId(userId);
		model.addAttribute("projectList", projectList);
		model.addAttribute("userId", userId);
	    return "projectList";
	  }

}
