package com.example.demo.controller;

import java.time.LocalDate;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.ErrorMessage;
import com.example.demo.dto.GoingRegisterDto;
import com.example.demo.service.GoingRegisterService;

@Controller
public class GoingRegisterController {

	/** 出勤登録画面Service */
    private final GoingRegisterService goingRegisterService;

    
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param goingRegisterService 出勤登録画面Service
	 */
	public GoingRegisterController (GoingRegisterService goingRegisterService) {
		this.goingRegisterService = goingRegisterService;
	}

	/**
	 * 出勤情報登録画面表示
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/home/attendance/attendanceStart/{userId}")
	public String attendanceStart(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId); // ← 画面に渡す
		model.addAttribute("GoingRegisterDto", new GoingRegisterDto()); // フォーム用オブジェクト
		return "attendanceStart";
	}

	/**
	 * 出勤情報登録
	 * 
	 * @param form
	 * @param result
	 * @param userId
	 * @param model
	 * @return
	 */
	@PostMapping("/home/attendance/attendanceStart/{userId}")
	public String registerAttendance(@Valid @ModelAttribute("GoingRegisterDto") GoingRegisterDto form,
			BindingResult result, @PathVariable("userId") String userId, Model model) {

		// 出勤日が本日と異なる場合エラー（業務チェック）
		if (form.getStartDate() == null || !form.getStartDate().equals(LocalDate.now())) {
			result.rejectValue("startDate", null, ErrorMessage.INVALID_DATE);
		}

		// 出勤日がすでにDBに存在（重複）している場合エラー（業務チェック）
		//	サービス層で「出勤データが重複していないか」をチェック、結果をcontrollerに返却
		if (form.getStartDate() != null &&
				goingRegisterService.isDuplicateAttendance(form.getUserId(), form.getStartDate())) {
			result.rejectValue("startDate", "", ErrorMessage.DUPLICATE_ATTENDANCE);
		}

		//	バリデーションチェック
		if (result.hasErrors()) {
			return "attendanceStart";
		} else {
			goingRegisterService.saveAttendance(form);
			return "redirect:/home/attendance/{userId}";
		}
	}
}
