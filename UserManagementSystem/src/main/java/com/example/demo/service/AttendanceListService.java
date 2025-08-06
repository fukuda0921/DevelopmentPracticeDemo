package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AttendanceListEntity;
import com.example.demo.repository.AttendanceListRepository;

@Service
public class AttendanceListService {

	//	ユーザー情報（リポジトリ）
	@Autowired
	private final AttendanceListRepository attendanceListRepository;

	public AttendanceListService(AttendanceListRepository attendanceListRepository) {
		this.attendanceListRepository = attendanceListRepository;
	}

	// ユーザー情報取得
	public List<AttendanceListEntity> getAllAttendanceList() {
		return attendanceListRepository.findAll();
	}

	//	ユーザー情報全検索
	public List<AttendanceListEntity> getAttendanceByUserId(Integer userId) {
		return attendanceListRepository.findByUserId(userId);
	}
	
//	勤怠データを ID で取得
	public Optional<AttendanceListEntity> getUserListById(Integer id) {
		return attendanceListRepository.findById(id);
	}

}
