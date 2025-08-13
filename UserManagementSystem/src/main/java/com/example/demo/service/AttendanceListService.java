package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dao.AttendanceListMapper;
import com.example.demo.entity.AttendanceListEntity;

@Service
public class AttendanceListService {

	/** 勤怠一覧Mapper */
	private final AttendanceListMapper attendanceListMapper;

	/**
	 * コンストラクタインジェクション
	 * 
	 * @param attendanceListMapper 勤怠一覧Mapper
	 */
	public AttendanceListService(AttendanceListMapper attendanceListMapper) {
		this.attendanceListMapper = attendanceListMapper;
	}

	/**
	 * ユーザー情報全検索
	 * 
	 * @param userId
	 * @return
	 */
	public List<AttendanceListEntity> getAttendanceByUserId(Integer userId) {
		
		List<AttendanceListEntity> attendanceList = attendanceListMapper.findByUserId(userId);
		
		return attendanceList;
	}

}
