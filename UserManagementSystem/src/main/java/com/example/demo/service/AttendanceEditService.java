package com.example.demo.service;

import java.time.Duration;
import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.example.demo.common.ErrorMessage;
import com.example.demo.dao.AttendanceEditMapper;
import com.example.demo.dto.AttendanceEditDto;
import com.example.demo.entity.AttendanceEditEntity;

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

	/**
	 * 勤怠情報を更新
	 * 
	 * @param attendanceEditentity
	 */
	public void updateAttendance(AttendanceEditEntity attendanceEditentity) {
		//
		AttendanceEditEntity entity = attendanceEditMapper.findByAttendanceId(attendanceEditentity.getAttendanceId());

		if (entity == null) {
			// 存在しない場合はエラーをスロー
			throw new RuntimeException(
					String.format(ErrorMessage.ATTENDANCE_NOT_FOUND, attendanceEditentity.getAttendanceId()));
		}

		//稼働を再計算するためにセット
		LocalTime startTime = attendanceEditentity.getStartTime();
		LocalTime endTime = attendanceEditentity.getLeavingTime();
		LocalTime breakTime = attendanceEditentity.getBreakTime();

		//稼働時間の計算
		LocalTime calculatedWorkTime = calculateWorkTime(startTime, endTime, breakTime);

		//再計算した稼働時間をattendanceEditentityにセット
		attendanceEditentity.setOperationTime(calculatedWorkTime);

		int updatedRows = attendanceEditMapper.update(attendanceEditentity);

		if (updatedRows == 0) {
			// ここが0になることは通常ありませんが、念のため防御的にチェック
			throw new RuntimeException("勤怠情報の更新に失敗しました（ID: " + attendanceEditentity.getAttendanceId() + "）");
		}
	}

	/**
	 * 勤怠情報を削除
	 * 
	 * @param attendanceId
	 */
	public void deleteAttendance(Integer attendanceId) {
		int deletedRows  = attendanceEditMapper.deleteByAttendanceId(attendanceId);
		
		if (deletedRows == 0) { // 0行削除された場合（見つからなかった場合）
	        throw new RuntimeException(String.format(ErrorMessage.ATTENDANCE_NOT_FOUND, attendanceId));
	    }
		
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
