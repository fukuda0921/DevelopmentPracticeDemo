package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.security.LoginUser;
import com.example.demo.security.LoginUserDetails;

@Controller
public class HomeController {
	@GetMapping("/home/{userId}")
	public String home(Model model, @AuthenticationPrincipal LoginUserDetails userDetails) {
		LoginUser user = userDetails.getLoginUser();
		model.addAttribute("userId", user.getUserId());
		return "home"; // ホーム画面に遷移
	}

	// 勤怠ページ
	@GetMapping("/home/attendance/{userId}")
	public String attendancePage(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "attendance"; // attendance.htmlに遷移
	}

	// ユーザー情報ページ
	@GetMapping("/home/userDetail/{userId}")
	public String userDetailPage(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "userDetail"; // userDetail.htmlに遷移
	}

	// ユーザー一覧ページ
	//    @GetMapping("/home/userList/{userId}")
	//    public String userListPage(@PathVariable("userId") String userId, Model model) {
	//        model.addAttribute("userId", userId);
	//        return "userList"; // userList.htmlに遷移
	//    }
	
	
	}