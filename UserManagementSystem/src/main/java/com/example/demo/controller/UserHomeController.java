package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserHomeController {
	
	/**
	 * ユーザー情報ページ
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/home/userDetail/{userId}")
	public String userDetailPage(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "userDetail"; // userDetail.htmlに遷移
	}

}
