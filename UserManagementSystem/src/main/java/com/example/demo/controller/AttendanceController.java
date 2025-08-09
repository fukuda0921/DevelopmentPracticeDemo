
package com.example.demo.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class AttendanceController {
	// 出勤登録ページ
		@GetMapping("/home/attendance/attendanceStart/{userId}")
		public String attendanceStartPage(@PathVariable("userId") String userId, Model model) {
	        model.addAttribute("userId", userId);
	        return "attendanceStart"; // attendance.htmlに遷移
	    }
		
//		// 退勤登録ページ
//		@GetMapping("/home/attendance/attendanceEnd/{userId}")
//		public String userDetailPage(@PathVariable("userId") String userId, Model model) {
//		    model.addAttribute("userId", userId);
//		    return "attendanceEnd"; // userDetail.htmlに遷移
//		}
	    
	 // 勤怠一覧ページ
	    @GetMapping("/home/attendance/attendanceList/{userId}")
	    public String userListPage(@PathVariable("userId") String userId, Model model) {
	        model.addAttribute("userId", userId);
	        return "attendanceList"; // userList.htmlに遷移
	    }
	    
	 // 戻る（勤怠情報ページ）
	    @GetMapping("/home/{userId}")
	    public String homePage(@PathVariable("userId") String userId, Model model) {
	        model.addAttribute("userId", userId);
	        return "home"; // userList.htmlに遷移
	    }


}
