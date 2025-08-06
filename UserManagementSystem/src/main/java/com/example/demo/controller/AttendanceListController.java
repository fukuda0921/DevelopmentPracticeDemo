package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.AttendanceListEntity;
import com.example.demo.service.AttendanceListService;

@Controller
public class AttendanceListController {

	@Autowired
	private final AttendanceListService attendanceListService;

	//	ユーザー情報
	public AttendanceListController(AttendanceListService attendanceListService) {
		this.attendanceListService = attendanceListService;
	}

	//	 一覧表示
	@GetMapping("/home/attendance/attendanceList/{userId}")
	public String showAttendanceList(@PathVariable Integer userId, Model model) {
		List<AttendanceListEntity> attendanceList = attendanceListService.getAttendanceByUserId(userId);
		model.addAttribute("attendanceList", attendanceList);
		model.addAttribute("userId", userId);
		return "attendanceList";
	}
}
