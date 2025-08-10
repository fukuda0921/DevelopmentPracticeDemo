package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.security.LoginUser;
import com.example.demo.security.LoginUserDetails;

@Controller
public class HomeController {

	/**
	 * ホーム画面
	 * 
	 * @param model
	 * @param userDetails
	 * @return
	 */
	@GetMapping("/home/{userId}")
	public String home(Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		LoginUser user = userDetails.getLoginUser();
		model.addAttribute("userId", user.getUserId());
		return "home"; // ホーム画面に遷移
	}
}