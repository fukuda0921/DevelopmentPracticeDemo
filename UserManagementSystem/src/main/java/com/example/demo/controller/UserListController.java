package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entity.UserListEntity;
import com.example.demo.security.LoginUserDetails;
import com.example.demo.service.UserListService;

@Controller
public class UserListController {

	/** ユーザー一覧Service */
	private final UserListService userListService;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userListService
	 */
	public UserListController(UserListService userListService) {
		this.userListService = userListService;
	}

	/**
	 *  ログインユーザー一覧
	 * 
	 * @param model
	 * @param loginUser
	 * @return
	 */
	@GetMapping("/home/userList/{userId}")
	public String getUserList(Model model, @AuthenticationPrincipal LoginUserDetails loginUser) {
		Integer loginUserId = loginUser.getUserId();
		boolean isAdmin = "1".equals(loginUser.getRole()); // ロールが1なら管理者

		// ユーザーリストを取得
		List<UserListEntity> userList = userListService.getAllUserList();

		model.addAttribute("userList", userList); 
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("loginUserId", loginUserId);

		return "userList";
	}
}
