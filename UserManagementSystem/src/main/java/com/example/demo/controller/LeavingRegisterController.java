package com.example.demo.controller;

import java.util.Optional;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.common.ErrorMessage;
import com.example.demo.dto.LeavingRegisterDto;
import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.service.LeavingRegisterService;

@Controller
public class LeavingRegisterController {

	/** 退勤登録Service */
	private final LeavingRegisterService leavingRegisterService;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param leavingRegisterService 退勤登録Service
	 */
	public LeavingRegisterController(LeavingRegisterService leavingRegisterService) {
		this.leavingRegisterService = leavingRegisterService;
	}

	/**
	 * 退勤登録画面表示
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/home/attendance/attendanceEnd/{userId}")
	public String attendanceEnd(@PathVariable String userId, Model model) {
		model.addAttribute("userId", userId); // ← 画面に渡す
		model.addAttribute("LeavingRegisterDto", new LeavingRegisterDto()); // フォーム用オブジェクト
		return "attendanceEnd";
	}

	/**
	 * 退勤情報登録処理
	 * 
	 * @param form
	 * @param result
	 * @param userId
	 * @param model
	 * @return
	 */
	@PostMapping("/home/attendance/attendanceEnd/{userId}")
	public String registerAttendanceEnd(@Valid @ModelAttribute("LeavingRegisterDto") LeavingRegisterDto form,
			BindingResult result, @PathVariable("userId") Integer userId, Model model) {

		// 入力された退勤日と比較
		if (form.getEndDate() == null || !leavingRegisterService.existsStartDateForUser(userId, form.getEndDate())) {
			result.rejectValue("endDate", null, ErrorMessage.NOT_MUCH_WORK_DATE);
		}

		// 入力された退勤情報が登録済みの場合（業務チェック）
		//	サービス層で「退勤日が重複していないか」をチェック、結果をcontrollerに返却
		if (form.getEndDate() != null &&
				leavingRegisterService.isDuplicateAttendance(userId, form.getEndDate())) {
			result.rejectValue("endDate", "", ErrorMessage.DUPLICATE_LEAVING_WORK);
		}

		//	バリデーションチェック
		if (result.hasErrors()) {
			return "attendanceEnd";
		}
		// --- ここから最新の出勤レコード取得して退勤時間更新処理 ---

		// 最新の出勤レコード取得
		Optional<LeavingRegisterEntity> latestAttendanceOpt = leavingRegisterService.getLatestAttendanceByUserId(userId,
				form.getEndDate());

		if (latestAttendanceOpt.isPresent()) {
			LeavingRegisterEntity attendance = latestAttendanceOpt.get();

			// 退勤日・退勤時間を現在日時にセット（必要に応じてフォーム値に変えてもOK）
			attendance.setEndDate(form.getEndDate());
			attendance.setEndTime(form.getEndTime());
			attendance.setBreakTime(form.getBreakTime());

			// 勤怠情報を更新
			leavingRegisterService.updateAttendance(attendance);
		}
		return "redirect:/home/attendance/{userId}";
	}
}

