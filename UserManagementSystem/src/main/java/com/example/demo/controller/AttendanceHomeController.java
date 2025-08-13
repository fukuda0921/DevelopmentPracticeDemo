package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AttendanceHomeController {
	
	/**
	 * 勤怠ページ
	 * 
	 * @param userId
	 * @param model
	 * @return
	 */
	@GetMapping("/home/attendance/{userId}")
	public String attendancePage(@PathVariable("userId") String userId, Model model) {
		model.addAttribute("userId", userId);
		return "attendance"; // attendance.htmlに遷移
	}

}
