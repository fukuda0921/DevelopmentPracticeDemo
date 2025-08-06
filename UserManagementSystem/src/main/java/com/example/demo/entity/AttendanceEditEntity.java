package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance_tbl")
public class AttendanceEditEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendanceId")
	private Integer attendanceId; // 勤怠ID（主キー）

	@Column(name = "userId", nullable = false)
	private Integer userId; // ユーザーID

	@Column(name = "startDate")
	private LocalDate startDate; // 出勤日

	@Column(name = "endDate")
	private LocalDate endDate; // 退勤日

	@Column(name = "startTime")
	private LocalTime startTime; // 出勤時間

	@Column(name = "endTime")
	private LocalTime endTime; // 退勤時間

	@Column(name = "workTime")
	private LocalTime workTime; // 勤務時間

	@Column(name = "breakTime")
	private LocalTime breakTime; // 休憩時間
	
	@Column(name = "remarks")
	private String remarks; // 備考

	// --- コンストラクタ ---
	public AttendanceEditEntity() {
	}

	// --- Getter & Setter ---

	// 勤怠ID（主キー）
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
