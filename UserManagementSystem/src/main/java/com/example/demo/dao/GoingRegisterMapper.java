package com.example.demo.dao;

import java.time.LocalDate;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.GoingRegisterEntity;

@Mapper
public interface GoingRegisterMapper {

	/**
	 * 当日出勤情報の存在チェック
	 * 
	 * @param userId
	 * @param startDate
	 * @return
	 */
	boolean existsAttendance(@Param("userId") Integer userId, @Param("startDate") LocalDate startDate);

	/**
	 * 出勤情報を登録
	 * 
	 * @param entity
	 * @return
	 */
	void addAttendance(GoingRegisterEntity entity);

}
