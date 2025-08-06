package com.example.demo.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.AttendanceEditEntity;

@Mapper
public interface AttendanceEditMapper {
	
	/**
	 * 勤怠IDに紐づくデータを取得
	 * 
	 * @param attendanceId
	 * @return
	 */
	AttendanceEditEntity findByAttendanceId(@Param("attendanceId") Integer attendanceId); 
	

}
