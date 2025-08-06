package com.example.demo.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserEditDto;
import com.example.demo.dto.UserEditProjectDto;
import com.example.demo.entity.UserEditEntity;
import com.example.demo.entity.UserEditProjectEntity;
import com.example.demo.service.UserEditProjectService;
import com.example.demo.service.UserEditService;

@Controller
public class UserEditController {

	private UserEditService userEditService;
	private UserEditProjectService userEditProjectService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	//	ユーザー情報
	public UserEditController(UserEditService userEditService) {
		this.userEditService = userEditService;
		this.userEditProjectService = userEditProjectService;
	}

	//	 一覧表示
	@GetMapping("/home/userList/userEdit/{loginUserId}/{targetUserId}")
	public String showEditForm(
			@PathVariable Integer loginUserId,
			@PathVariable Integer targetUserId,
			Model model) {

		UserEditDto userEditDto = userEditService.findByUserId(targetUserId);
		//		System.out.println("プロジェクト数: " + userEditDto.getProjects().size());
		model.addAttribute("userEditDto", userEditDto);

		// 自分のID
		model.addAttribute("loginUserId", loginUserId);

		return "userEdit";
	}

	// ユーザー情報の更新
	@PostMapping("/home/userList/userEdit/{loginUserId}/{targetUserId}")
	public String updateUser(
			@PathVariable Integer loginUserId,
			@PathVariable Integer targetUserId,
			@ModelAttribute("userEditDto") @Valid UserEditDto dto,
			BindingResult result,
			@RequestParam String action,
			Model model) {

		if ("edit".equals(action)) {
			// 登録ボタンが押されたときの処理
			if (result.hasErrors()) {
				model.addAttribute("loginUserId", loginUserId);
				return "userEdit";
			}
			// ユーザー基本情報の更新
			UserEditEntity userEntity = userEditService.convertToEntity(dto);
			userEditService.updateUser(userEntity);

			// パスワードが入力されていればハッシュ化して更新、空なら元のパスワードを保持
			if (org.springframework.util.StringUtils.hasText(dto.getPassword())) {
				userEntity.setPassword(passwordEncoder.encode(dto.getPassword()));
			} else {
				String currentPassword = userEditService.getCurrentPassword(dto.getUserId());
				userEntity.setPassword(currentPassword);
			}
			userEditService.updateUser(userEntity);

			// 案件情報の更新（複数対応）
			for (UserEditProjectDto projectDto : dto.getProjects()) {
				UserEditProjectEntity projectEntity = userEditProjectService.convertToEntity(projectDto, userEntity);
				userEditProjectService.updateProject(projectEntity);
			}

			// 更新後に一覧画面などにリダイレクト
			return "redirect:/home/userList/" + loginUserId;

		} else if ("delete".equals(action)) {
			// 削除ボタンが押されたときの処理

			userEditService.deleteUser(targetUserId);
			userEditProjectService.deleteProject(targetUserId);

			return "redirect:/home/userList/" + loginUserId;
		} else {
			// actionがeditでもdeleteでもなければ戻す
			return "userEdit";
		}
	}

	// 勤怠情報を削除
	@PostMapping("/home/userList/userEdit/{loginUserId}/{targetUserId}/delete")
	public String deleteAttendance(@PathVariable Integer userId) {

		userEditService.deleteUser(userId);
		userEditProjectService.deleteProject(userId); // 案件情報も一緒に削除

		// 削除後のリダイレクト先
		return "redirect:/home/userList/" + userId;
	}

}

//	// ユーザー情報の更新
//	@RequestMapping(value = "/home/userList/userEdit/{userId}/{attendanceId}", method = RequestMethod.POST)
//	public String handleAttendance(
//			@PathVariable Integer userId,
//			@RequestParam(name = "action", required = false) String action,
//			@ModelAttribute("UserEditDto") UserEditDto UserEditDto,
//			BindingResult bindingResult,
//			Model model) {
//
//		if ("delete".equals(action)) {
//			userEditService.deleteUser(userId);
//			return "redirect:/home/userList/" + userId;
//		}
//
//		if (bindingResult.hasErrors()) {
//			return "userEdit"; // バリデーションエラー時はフォームに戻す
//		}
//
//		// DTO → Entity に変換
//		UserEditEntity entity = new UserEditEntity();
//		entity.setName(UserEditDto.getName());
//		entity.setKana(UserEditDto.getKana());
//		entity.setAddress(UserEditDto.getAddress());
//		entity.setPassword(UserEditDto.getPassword());
//		entity.setRole(UserEditDto.getRole());
//		entity.setProjectId(UserEditDto.getProjectId());
//		entity.setProjectName(UserEditDto.getProjectName());
//		entity.setRemoteAvailability(UserEditDto.getRemoteAvailability());
//		entity.setWorkLocation(UserEditDto.getWorkLocation());
//		entity.setJobDescription(UserEditDto.getJobDescription());
//		entity.setProgrammingLanguage(UserEditDto.getProgrammingLanguage());
//		entity.setRemarks(UserEditDto.getRemarks());
//
//		// サービス層で更新処理
//		userEditService.updateUser(entity);
//
//		// 更新後の画面へリダイレクト（詳細または一覧）
//		return "redirect:/home/userList/{userId}";
//	}
