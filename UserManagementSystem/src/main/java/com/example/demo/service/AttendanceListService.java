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

//	//	ユーザー情報（リポジトリ）
//	@Autowired
//	private final AttendanceListRepository attendanceListRepository;
//
//	public AttendanceListService(AttendanceListRepository attendanceListRepository) {
//		this.attendanceListRepository = attendanceListRepository;
//	}

	// ユーザー情報取得
//	public List<AttendanceListEntity> getAllAttendanceList() {
//		return attendanceListMapper.findAll();
//	}

	//	
	/**
	 * ユーザー情報全検索
	 * 
	 * @param userId
	 * @return
	 */
	public List<AttendanceListEntity> getAttendanceByUserId(Integer userId) {
		return attendanceListMapper.findByUserId(userId);
	}
	
//	勤怠データを ID で取得
//	public Optional<AttendanceListEntity> getUserListById(Integer id) {
//		return attendanceListMapper.findById(id);
//	}

}
