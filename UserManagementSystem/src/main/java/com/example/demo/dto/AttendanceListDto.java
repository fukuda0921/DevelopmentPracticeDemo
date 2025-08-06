package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demo.entity.AttendanceListEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AttendanceListDto {

	private Integer attendanceId; // 勤怠ID
	private Integer userId; // ユーザーID
	private LocalDate startDate; // 出勤日
	private LocalDate endDate; // 退勤日
	private LocalTime startTime; // 出勤時間
	private LocalTime endTime; // 退勤時間
	private LocalTime workTime; // 稼働時間
	private LocalTime breakTime; // 休憩時間
	private String remarks; // 備考

	public AttendanceListDto() {
	}

	// UserListEntityを引数に取るコンストラクタ
	public AttendanceListDto(AttendanceListEntity attendanceListentity) {
		this.attendanceId = attendanceListentity.getAttendanceId();
		this.userId = attendanceListentity.getUserId();
		this.startDate = attendanceListentity.getStartDate();
		this.endDate = attendanceListentity.getLeavingDate();
		this.startTime = attendanceListentity.getStartTime();
		this.endTime = attendanceListentity.getLeavingTime();
		this.workTime = attendanceListentity.getOperationTime();
		this.breakTime = attendanceListentity.getBreakTime();
		this.remarks = attendanceListentity.getRemarks();

	}

	// --- ゲッターとセッター ---

	//勤怠ID
	public Integer getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Integer attendanceId) {
		this.attendanceId = attendanceId;
	}

	//ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	//出勤日
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	//退勤日
	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	//出勤時間
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	//退勤時間
	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	//稼働時間
	public LocalTime getWorkTime() {
		return workTime;
	}

	public void setWorkTime(LocalTime workTime) {
		this.workTime = workTime;
	}

	//休憩時間
	public LocalTime getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(LocalTime breakTime) {
		this.breakTime = breakTime;
	}

	//備考
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
