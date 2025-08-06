package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.ErrorMessage;
import com.example.demo.dao.AttendanceEditMapper;
import com.example.demo.dto.AttendanceEditDto;
import com.example.demo.entity.AttendanceEditEntity;
import com.example.demo.repository.AttendanceEditRepository;

@Service
public class AttendanceEditService {
	
	/** 勤怠編集Mapper */
	private final AttendanceEditMapper attendanceEditMapper;
	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param attendanceEditMapper 勤怠編集Mapper
	 */
	public AttendanceEditService(AttendanceEditMapper attendanceEditMapper) {
		this.attendanceEditMapper = attendanceEditMapper;
	}
	
	@Autowired
	private AttendanceEditRepository attendanceEditRepository;


	/**
	 * 勤怠IDで勤怠情報を取得
	 * 
	 * @param attendanceId
	 * @return
	 */
	public AttendanceEditDto findByAttendanceId(Integer attendanceId) {
		AttendanceEditEntity entity = attendanceEditMapper.findByAttendanceId(attendanceId);
		
		// 結果がnullだった場合、エラーをスロー
        if (entity == null) {
        	throw new RuntimeException(String.format(ErrorMessage.ATTENDANCE_NOT_FOUND, attendanceId));
        }
		
		return new AttendanceEditDto(entity);
	}

	// 勤怠情報を更新
	public void updateAttendance(AttendanceEditEntity attendanceEditentity) {
		AttendanceEditEntity entity = attendanceEditRepository.findById(attendanceEditentity.getAttendanceId())
				.orElseThrow();
		entity.setStartDate(attendanceEditentity.getStartDate());
		entity.setLeavingDate(attendanceEditentity.getLeavingDate());
		entity.setStartTime(attendanceEditentity.getStartTime());
		entity.setLeavingTime(attendanceEditentity.getLeavingTime());
		entity.setOperationTime(attendanceEditentity.getOperationTime());
		entity.setBreakTime(attendanceEditentity.getBreakTime());
		entity.setRemarks(attendanceEditentity.getRemarks());
		attendanceEditRepository.save(entity);
	}

	// 勤怠情報を削除
	public void deleteAttendance(Integer attendanceId) {
		attendanceEditRepository.deleteById(attendanceId);
	}

}
