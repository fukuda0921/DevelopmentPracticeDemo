package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dto.AttendanceEditDto;
import com.example.demo.entity.AttendanceEditEntity;
import com.example.demo.service.AttendanceEditService;

@Controller
@RequestMapping
public class AttendanceEditController {

	@Autowired
	private AttendanceEditService attendanceEditService;

	//	ユーザー情報
	public AttendanceEditController(AttendanceEditService attendanceEditService) {
		this.attendanceEditService = attendanceEditService;
	}

	//	 一覧表示
	@GetMapping("/attendanceEdit/{userId}/{attendanceId}")
	public String AttendanceEdit(@PathVariable Integer userId,
								 @PathVariable Integer attendanceId, 
								 Model model) {
//		// 複数件の勤怠情報を取得
//		List<AttendanceEditDto> attendanceList = attendanceEditService.findByUserId(userId);
//		model.addAttribute("attendanceList", attendanceList);

		// 編集対象：1件のみ取得
		AttendanceEditDto attendanceEditDto = attendanceEditService.findByAttendanceId(attendanceId);
		model.addAttribute("attendanceEditDto", attendanceEditDto);
		model.addAttribute("userId", userId);
		return "attendanceEdit";
	}

	// ユーザー情報の更新
//	@PostMapping
	@RequestMapping(value = "/attendanceEdit/{userId}/{attendanceId}" ,method = RequestMethod.POST)
	public String handleAttendance(
	        @PathVariable Integer userId,
	        @PathVariable Integer attendanceId,
	        @RequestParam(name = "action", required = false) String action,
	        @ModelAttribute("AttendanceEditDto") AttendanceEditDto attendanceEditDto,
	        BindingResult bindingResult,
	        Model model) {
		
		if ("delete".equals(action)) {
	        attendanceEditService.deleteAttendance(attendanceId);
	        return "redirect:/home/attendance/attendanceList/" + userId;
	    }
		
		if (bindingResult.hasErrors()) {
			return "attendanceEdit"; // バリデーションエラー時はフォームに戻す
		}

		// DTO → Entity に変換
		AttendanceEditEntity entity = new AttendanceEditEntity();
		entity.setAttendanceId(attendanceEditDto.getAttendanceId());
		entity.setUserId(attendanceEditDto.getUserId());
		entity.setStartDate(attendanceEditDto.getStartDate());
		entity.setEndDate(attendanceEditDto.getEndDate());
		entity.setStartTime(attendanceEditDto.getStartTime());
		entity.setEndTime(attendanceEditDto.getEndTime());
		entity.setWorkTime(attendanceEditDto.getWorkTime());
		entity.setBreakTime(attendanceEditDto.getBreakTime());
		entity.setRemarks(attendanceEditDto.getRemarks());

		// サービス層で更新処理
		attendanceEditService.updateAttendance(entity);

		// 更新後の画面へリダイレクト（詳細または一覧）
		return "redirect:/home/attendance/attendanceList/{userId}";
	}
	
	// 勤怠情報を削除
	@PostMapping("/attendanceEdit/{userId}/{attendanceId}/delete")
	public String deleteAttendance(@PathVariable Integer userId,
	                               @PathVariable Integer attendanceId) {

	    attendanceEditService.deleteAttendance(attendanceId);

	    // 削除後のリダイレクト先
	    return "redirect:/home/attendance/attendanceList/{userId}";
	}

}
