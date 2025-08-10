package com.example.demo.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.example.demo.dao.GoingRegisterMapper;
import com.example.demo.dto.GoingRegisterDto;
import com.example.demo.entity.GoingRegisterEntity;

@Service
public class GoingRegisterService {
	
	/**出勤登録画面Mapper*/
	private final GoingRegisterMapper goingRegisterMapper ;
	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param goingRegisterMapper 出勤登録画面Mapper
	 */
	public GoingRegisterService (GoingRegisterMapper goingRegisterMapper) {
		this.goingRegisterMapper = goingRegisterMapper; 
	}

	/**
	 * 出勤データの重複チェック（業務チェック）
	 * 
	 * @param userId
	 * @param startDate
	 * @return　Boolean
	 */
	public boolean isDuplicateAttendance(Integer userId, LocalDate startDate) {
				
		return goingRegisterMapper.existsAttendance(userId, startDate);
	}

	/**
	 * 出勤情報登録処理
	 * @param form
	 */
	public void saveAttendance(GoingRegisterDto form) {
		GoingRegisterEntity entity = new GoingRegisterEntity();
		entity.setUserId(form.getUserId());
		entity.setStartDate(form.getStartDate());
		entity.setStartTime(form.getStartTime());
		entity.setRemarks(form.getRemarks());
		entity.setDeleteFlag(0);//deleteFlagを0でセット

		goingRegisterMapper.addAttendance(entity);
	}

}
