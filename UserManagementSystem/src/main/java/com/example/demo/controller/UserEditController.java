package com.example.demo.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.UserEditDto;
import com.example.demo.entity.UserEditEntity;
import com.example.demo.service.UserEditService;

@Controller
public class UserEditController {

	/** ユーザー編集・削除Service */
	private final UserEditService userEditService;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userEditService
	 * @param userEditProjectService
	 * @param passwordEncoder
	 */
	public UserEditController(UserEditService userEditService) {
		this.userEditService = userEditService;
	}

	/**
	 * ユーザー編集・削除画面表示
	 * 
	 * @param loginUserId
	 * @param targetUserId
	 * @param model
	 * @return
	 */
	@GetMapping("/home/userList/userEdit/{loginUserId}/{targetUserId}")
	public String showEditForm(
			@PathVariable Integer loginUserId,
			@PathVariable Integer targetUserId,
			Model model) {

		//ユーザー情報登録取得
		List<UserEditDto> userEditDtoList = userEditService.findByUserId(targetUserId);
		UserEditDto userEditDto = userEditDtoList.get(0);

		model.addAttribute("userEditDto", userEditDto);
		model.addAttribute("loginUserId", loginUserId);

		return "userEdit";
	}

	/**
	 * ユーザー情報の更新
	 * 
	 * @param loginUserId
	 * @param targetUserId
	 * @param userEditDto
	 * @param result
	 * @param action
	 * @param model
	 * @return
	 */
	@PostMapping("/home/userList/userEdit/{loginUserId}/{targetUserId}")
	public String updateUser(
			@PathVariable Integer loginUserId,
			@PathVariable Integer targetUserId,
			@ModelAttribute("userEditDto") @Valid UserEditDto userEditDto,
			BindingResult result,
			@RequestParam String action,
			Model model) {

		// 登録ボタンが押されたときの処理
		if ("edit".equals(action)) {

			if (result.hasErrors()) {
				model.addAttribute("loginUserId", loginUserId);
				return "userEdit";
			}

			//メールアドレス存在するかチェック
			if (userEditService.isEmailDuplicateForOtherUsers(userEditDto.getAddress(), userEditDto.getUserId())) {
				result.rejectValue("address", "duplicateEmail", "このメールアドレスは既に他のユーザーに登録されています");
				model.addAttribute("loginUserId", loginUserId);
				return "userEdit";
			}

			// ユーザー基本情報の更新
			UserEditEntity userEntity = userEditService.convertToEntity(userEditDto);
			userEditService.updateUser(userEntity);

			// 更新後に一覧画面などにリダイレクト
			return "redirect:/home/userList/" + loginUserId;

			// 削除ボタンが押されたときの処理
		} else if ("delete".equals(action)) {

			userEditService.deleteUser(targetUserId);

			return "redirect:/home/userList/" + loginUserId;

		} else {
			// actionがeditでもdeleteでもなければ戻す
			return "userEdit";
		}
	}

}
