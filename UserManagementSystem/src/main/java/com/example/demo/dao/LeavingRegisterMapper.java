package com.example.demo.dao;

import java.time.LocalDate;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.LeavingRegisterEntity;

@Mapper
public interface LeavingRegisterMapper {

	/**
	 * 指定されたユーザーIDと日付に対応する出勤データが存在するかをチェック
	 * 
	 * @param userId
	 * @param endDate
	 * @return
	 */
	boolean existsByUserIdAndStartDate(@Param("userId") Integer userId, @Param("endDate") LocalDate endDate);

	/**
	 * 退勤データが既に存在しているかチェック
	 * 
	 * @param userId
	 * @param endDate
	 * @return
	 */
	boolean existsByUserIdAndEndDate(@Param("userId") Integer userId, @Param("endDate") LocalDate endDate);

	/**
	 * 指定されたユーザーIDの最新の出勤レコードを取得します。
	 * @param userId 
	 * @return 最新の出勤レコード:存在しない場合はOptional.empty()
	 */
	Optional<LeavingRegisterEntity> findTopByUserIdOrderByStartDateDesc(@Param("userId") Integer userId,
			@Param("endDate") LocalDate endDate);

	/**
	 * 勤怠レコードを更新します。
	 * @param entity 
	 */
	void updateAttendance(LeavingRegisterEntity attendanceEntity);
}
