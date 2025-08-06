package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.service.UserRegistrationService;

@Controller
public class UserRegistrationController {
	@Autowired
	private UserRegistrationService userRegistration;
	
	@GetMapping("/userDetail/userRegistration/{userId}")
	public String userRegistration(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId); // ← 画面に渡す
		model.addAttribute("userRegistrationDto", new UserRegistrationDto()); // フォーム用オブジェクト
		
		 //権限の選択肢
	    Map<String, String> roleOptions = new LinkedHashMap<String, String>();
	    roleOptions.put("1", "管理者");
	    roleOptions.put("2", "一般");

	    model.addAttribute("roleOptions", roleOptions);
		return "userRegistration";
	}
	
	@PostMapping("/userDetail/userRegistration/{userId}")
	public String userRegistrationP(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto form,
			BindingResult result,
			@PathVariable("userId") String userId,
			Model model) {
//		バリデーションチェック
			if (result.hasErrors()) {
				model.addAttribute("userId", userId);
				
				Map<String, String> roleOptions = new LinkedHashMap<String, String>();
			    roleOptions.put("1", "管理者");
			    roleOptions.put("2", "一般");
			    
			    model.addAttribute("roleOptions", roleOptions);
				return "userRegistration";
			} else {
				userRegistration.saveUser(form);
				return "redirect:/home/userDetail/" + userId;
			}
		}
	}

