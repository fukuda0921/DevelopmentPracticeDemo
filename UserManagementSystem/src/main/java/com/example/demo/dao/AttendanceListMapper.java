package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.entity.AttendanceListEntity;

@Mapper
public interface AttendanceListMapper {

	/**
	 * 指定されたユーザーIDの全ての勤怠情報を取得
	 * 
	 * @param userId ユーザーID
	 * @return 勤怠情報のリスト
	 */
	List<AttendanceListEntity> findByUserId(@Param("userId") Integer userId);

}
