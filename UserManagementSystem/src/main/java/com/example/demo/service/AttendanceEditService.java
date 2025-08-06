package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AttendanceEditDto;
import com.example.demo.entity.AttendanceEditEntity;
import com.example.demo.repository.AttendanceEditRepository;

@Service
public class AttendanceEditService {
	@Autowired
	private AttendanceEditRepository attendanceEditRepository;

	//	// 勤怠情報をユーザーIDで全件取得
	//    public AttendanceEditDto findByUserId(Integer userId) {
	//        AttendanceEditEntity entity = attendanceEditRepository.findByUserId(userId);
	//        return entityList.stream()
	//                .map(AttendanceEditDto::new)
	//                .collect(Collectors.toList());
	//    }

	// 勤怠IDで勤怠情報を取得）
	public AttendanceEditDto findByAttendanceId(Integer attendanceId) {
		AttendanceEditEntity entity = attendanceEditRepository.findByAttendanceId(attendanceId)
				.orElseThrow(() -> new RuntimeException("勤怠情報が見つかりません（ID: " + attendanceId + "）"));
		return new AttendanceEditDto(entity);
		//.map(AttendanceEditDto::new)
		//.collect(Collectors.toList());
	}

	// 勤怠情報を更新
	public void updateAttendance(AttendanceEditEntity attendanceEditentity) {
		AttendanceEditEntity entity = attendanceEditRepository.findById(attendanceEditentity.getAttendanceId())
				.orElseThrow();
		entity.setStartDate(attendanceEditentity.getStartDate());
		entity.setEndDate(attendanceEditentity.getEndDate());
		entity.setStartTime(attendanceEditentity.getStartTime());
		entity.setEndTime(attendanceEditentity.getEndTime());
		entity.setWorkTime(attendanceEditentity.getWorkTime());
		entity.setBreakTime(attendanceEditentity.getBreakTime());
		entity.setRemarks(attendanceEditentity.getRemarks());
		attendanceEditRepository.save(entity);
	}

	// 勤怠情報を削除
	public void deleteAttendance(Integer attendanceId) {
		attendanceEditRepository.deleteById(attendanceId);
	}

}
