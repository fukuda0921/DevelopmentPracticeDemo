package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.dto.UserListDto;
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

//	 ログインユーザー情報
	@GetMapping("/home/userList/{userId}")
	public String getUserList(Model model, @AuthenticationPrincipal LoginUserDetails loginUser) {
		Integer loginUserId = loginUser.getUserId();
		boolean isAdmin = "1".equals(loginUser.getRole()); // ロールが1なら管理者

		// ユーザーリストを取得
		List<UserListEntity> userList = userListService.getAllUserList();
		
		
		// DTOに変換
		List<UserListDto> userDtoList = new ArrayList<>();
		for (UserListEntity userEntity : userList) { 
		    userDtoList.add(new UserListDto(userEntity)); 
		}
		
	    model.addAttribute("userList", userDtoList); // DTOに変換したリストをModelに追加する
		model.addAttribute("isAdmin", isAdmin);
		model.addAttribute("loginUserId", loginUserId);

		return "userList";
	}
}	

		//	//	ユーザー一覧を表示
		//	@GetMapping("/home/userList/{userId}")
		//	public String showUserList(@PathVariable String userId, Model model) {
		//		List<UserListEntity> userList = userListService.getllUserList();

//		// ユーザーリストのパスワードをマスクする
//		for (UserListEntity user : userList) {
//			user.setPassword(maskPassword(user.getPassword()));
//		}

		

//	public String maskPassword(String password) {
//		// パスワードをマスクする処理
//		if (password == null || password.isEmpty()) {
//			return "";
//		}
//		// パスワードの文字数分アスタリスクを表示
//		return "*".repeat(password.length());
//	}



