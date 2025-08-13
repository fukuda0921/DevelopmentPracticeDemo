package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dto.UserDetailDto;
import com.example.demo.service.UserDetailService;


@Controller
public class UserDetailController {

	/** ユーザ詳細画面Service */
	private final UserDetailService userDetailService;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userDetailService
	 */
	public UserDetailController(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}

	/**
	 * ユーザー詳細画面の表示
	 * @param targetUserId 表示対象のユーザーID
	 * @param loginUserId ログインユーザーのID
	 * @param model モデル
	 * @return userDetail.html
	 */
	@GetMapping("/home/userDetail/{loginUserId}/{targetUserId}")
	public String showUserDetail(
			@PathVariable("targetUserId") Integer targetUserId,
			@PathVariable("loginUserId") Integer loginUserId, Model model) {

		// ユーザー詳細DTOを取得
		UserDetailDto userDetail = userDetailService.getUserDetail(targetUserId);
		model.addAttribute("userDetail", userDetail);
		model.addAttribute("loginUserId", loginUserId);

		return "userDetail";
	}
}
