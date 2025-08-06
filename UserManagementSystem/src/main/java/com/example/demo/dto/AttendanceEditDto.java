package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.AttendanceEditEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
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
	
	
//	private Integer attendanceId;     // 勤怠ID
//	private Integer userId;           // ユーザーID
//	private LocalDate startDate;      // 出勤日
//	private LocalDate endDate;        // 退勤日
//	private LocalTime startTime;      // 出勤時間
//	private LocalTime endTime;        // 退勤時間
//	private LocalTime workTime;       // 勤務時間（文字列として扱う場合）
//	private LocalTime breakTime;      // 休憩時間
//	private String remarks;           // 備考
//
	
	public AttendanceEditDto() {
	    // Springが使う空のコンストラクタ
	}
	
	public AttendanceEditDto(AttendanceEditEntity entity) {
		this.attendanceId = entity.getAttendanceId();
		this.userId = entity.getUserId();
		this.startDate = entity.getStartDate();
		this.endDate = entity.getEndDate();
		this.startTime = entity.getStartTime();
		this.endTime = entity.getEndTime();
		this.workTime = entity.getWorkTime();
		this.breakTime = entity.getBreakTime();
		this.remarks = entity.getRemarks();
	}

	// --- Getter & Setter ---
	
	// 勤怠ID
	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}
	
	// ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	// 出勤日
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// 退勤日
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	// 出勤時間
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	
	// 退勤時間
	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	// 勤務時間
	public LocalTime getWorkTime() {
		return workTime;
	}

	public void setWorkTime(LocalTime workTime) {
		this.workTime = workTime;
	}

	// 休憩時間
	public LocalTime getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(LocalTime breakTime) {
		this.breakTime = breakTime;
	}

	// 備考
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
