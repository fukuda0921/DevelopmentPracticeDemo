package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dao.LeavingRegisterMapper;
import com.example.demo.entity.LeavingRegisterEntity;

@Service
public class LeavingRegisterService {
	
	/** 退勤登録Mapper */
	private final  LeavingRegisterMapper leavingRegisterMapper;
	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param leavingRegisterMapper 退勤登録Mapper
	 */
	public LeavingRegisterService (LeavingRegisterMapper leavingRegisterMapper) {
		this.leavingRegisterMapper = leavingRegisterMapper;
	}
	
	/**
	 * 退勤データの重複チェック（業務チェック）
	 * 
	 * @param userId
	 * @param endDate
	 * @return
	 */
	public boolean isDuplicateAttendance(Integer userId, LocalDate endDate) {
		return leavingRegisterMapper.existsByUserIdAndEndDate(userId, endDate);
	}
	
	/**
	 * 指定したユーザーが出勤しているかを確認
	 * 
	 * @param userId
	 * @param date
	 * @return
	 */
	public boolean existsStartDateForUser(Integer userId, LocalDate endDate) {
	    return leavingRegisterMapper.existsByUserIdAndStartDate(userId, endDate);
	}
	
	/**
	 * 最新の出勤レコードを取得（Optional で返す）
	 * @param userId
	 * @return
	 */
    public Optional<LeavingRegisterEntity> getLatestAttendanceByUserId(Integer userId, LocalDate endDate) {
        return leavingRegisterMapper.findTopByUserIdOrderByStartDateDesc(userId, endDate);
    }
    
	/**
	 * 勤怠情報を更新する（save = insert or update）
	 * 
	 * @param attendance
	 */
	public void updateAttendance(LeavingRegisterEntity attendance) {
		
		//Entityの値を変数に詰め替える
		LocalTime startTime = attendance.getStartTime();
		LocalTime endTime = attendance.getEndTime();
		LocalTime breakTime = attendance.getBreakTime();

		//稼働時間の計算
		LocalTime calculatedWorkTime = calculateWorkTime(startTime, endTime, breakTime);

		//稼働時間を Entity に格納
		attendance.setWorkTime(calculatedWorkTime);

		leavingRegisterMapper.updateAttendance(attendance);
	}
    
    
	/**
	 * 稼働時間を計算するメソッド
	 * 
	 * @param startTime
	 * @param endTime
	 * @param breakTime
	 * @return
	 */
	private LocalTime calculateWorkTime(LocalTime startTime, LocalTime endTime, LocalTime breakTime) {
		if (startTime == null || endTime == null) {
			return null;
		}

		// 出勤時間・退勤時間の差を求める
		Duration totalWorkDuration = Duration.between(startTime, endTime);

		// 休憩時間を Duration に変換し、実稼働時間から引く
		if (breakTime != null) {
			// LocalTimeの休憩時間をDurationに変換
			Duration actualBreakDuration = Duration.ofHours(breakTime.getHour())
					.plusMinutes(breakTime.getMinute());
			totalWorkDuration = totalWorkDuration.minus(actualBreakDuration);
		}

		// DurationをLocalTimeに変換
		// (例: 25時間30分: 01:30:00)
		return LocalTime.MIDNIGHT.plus(totalWorkDuration);
	}
}

	
