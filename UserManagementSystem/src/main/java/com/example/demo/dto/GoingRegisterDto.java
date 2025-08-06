package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class GoingRegisterDto {

	private Integer attendanceId; // 勤怠ID（編集用）

	private Integer userId; // ユーザーID

	@NotNull(message = "出勤日を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate; // 出勤日

	@NotNull(message = "出勤時間を入力してください")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime startTime; // 出勤時間

	@Size(max = 100, message = "備考は100文字以内で入力してください")
	private String remarks; // 備考

	// ゲッターとセッター

	// ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId( Integer userId) {
		this.userId = userId;
	}

	// 出勤日
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// 出勤時間
	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	// 備考
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
