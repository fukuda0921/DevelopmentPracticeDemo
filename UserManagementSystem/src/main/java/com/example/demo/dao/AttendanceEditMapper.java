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

	/**
	 * 勤怠情報を更新
	 * 
	 * @param entity 更新対象の勤怠エンティティ
	 * @return 更新されたレコードの数
	 */
	int update(AttendanceEditEntity entity);

	/**
	 * 勤怠IDで勤怠情報を削除
	 * 
	 * @param attendanceId 削除対象の勤怠ID
	 * @return 削除されたレコードの数
	 */
	int deleteByAttendanceId(@Param("attendanceId") Integer attendanceId);

}
