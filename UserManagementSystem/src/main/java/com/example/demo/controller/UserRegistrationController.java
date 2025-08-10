package com.example.demo.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import jakarta.validation.Valid;

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

	/** ユーザー登録Service */
	private final UserRegistrationService userRegistrationService;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userRegistrationService
	 */
	public UserRegistrationController(UserRegistrationService userRegistrationService) {
		this.userRegistrationService = userRegistrationService;
	}

	/**
	 * ユーザー登録画面表示
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/userDetail/userRegistration/{userId}")
	public String userRegistration(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId); // ← 画面に渡す
		model.addAttribute("userRegistrationDto", new UserRegistrationDto());

		//権限の選択肢
		Map<Integer, String> roleOptions = new LinkedHashMap<Integer, String>();
		roleOptions.put(1, "管理者");
		roleOptions.put(2, "一般");

		model.addAttribute("roleOptions", roleOptions);
		return "userRegistration";
	}

	/**
	 * ユーザー情報新規登録
	 * 
	 * @param form
	 * @param result
	 * @param userId
	 * @param model
	 * @return
	 */
	@PostMapping("/userDetail/userRegistration/{userId}")
	public String userRegistrationP(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto form,
			BindingResult result,
			@PathVariable("userId") String userId,
			Model model) {
		//バリデーションチェック
		if (result.hasErrors()) {
			model.addAttribute("userId", userId);

			Map<Integer, String> roleOptions = new LinkedHashMap<Integer, String>();
			roleOptions.put(1, "管理者");
			roleOptions.put(2, "一般");

			model.addAttribute("roleOptions", roleOptions);
			return "userRegistration";
		} else {
			
			//メールアドレス存在するかチェック
			 if (userRegistrationService.checkEmailExists(form.getAddress())) { 
		            result.rejectValue("address","duplicateEmail",  "このメールアドレスは既に登録されています"  );
		            model.addAttribute("userId", userId);
		            return "userRegistration";
		        }
;			
			userRegistrationService.saveUser(form);
			return "redirect:/home/userDetail/" + userId;
		}
	}
}