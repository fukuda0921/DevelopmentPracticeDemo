package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.AttendanceEditEntity;

import lombok.Data;

@Data
public class AttendanceEditDto {
	
	private Integer attendanceId; // 勤怠ID（編集用）
	
	private Integer userId; // ユーザーID

	@NotNull(message = "出勤日を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate; // 出勤日
	
	@NotNull(message = "退勤日を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate; // 退勤日

	@NotNull(message = "出勤時間を入力してください")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime; // 出勤時間
	
	@NotNull(message = "退勤時間を入力してください")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime endTime; // 退勤時間
	
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime workTime; // 稼働時間
	
	@NotNull(message = "休憩時間を入力してください")
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime breakTime; // 休憩時間

	@Size(max = 100, message = "備考は100文字以内で入力してください")
	private String remarks; // 備考
	
	/*
	 * コンストラクタ
	 */
	public AttendanceEditDto() {
	    // Springが使う空のコンストラクタ
	}
	
	public AttendanceEditDto(AttendanceEditEntity entity) {
		this.attendanceId = entity.getAttendanceId();
		this.userId = entity.getUserId();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getLeavingDate();
		this.startTime = entity.getStartTime();
		this.endTime = entity.getLeavingTime();
		this.workTime = entity.getOperationTime();
		this.breakTime = entity.getBreakTime();
		this.remarks = entity.getRemarks();
	}

}
