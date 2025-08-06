package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.ProjectRegistrationDto;
import com.example.demo.service.ProjectRegistrationService;

@Controller
public class ProjectRegistrationController {

	@Autowired
	private ProjectRegistrationService projectRegistrationservice;

	@GetMapping("/userList/projectRegistration/{userId}")
	public String projectRegistration(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId); // ← 画面に渡す
		model.addAttribute("ProjectRegistrationDto", new ProjectRegistrationDto()); // フォーム用オブジェクト
		return "projectRegistration";
	}

	@PostMapping("/userList/projectRegistration/{userId}")
	public String projectRegistrationP(@Valid @ModelAttribute("ProjectRegistrationDto") ProjectRegistrationDto form,
			BindingResult result,
			@PathVariable("userId") String userId,
			Model model) {

		// バリデーションチェック
		if (result.hasErrors()) {
			return "projectRegistration";
		} else {
			projectRegistrationservice.saveProject(form);
			return "redirect:/home/userList/" + userId;
		}
	}

}
