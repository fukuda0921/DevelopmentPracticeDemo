package com.example.demo.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LeavingRegisterDto {
	private Integer attendanceId; // 勤怠ID（編集用）

	private Integer userId; // ユーザーID

	@NotNull(message = "退勤日を入力してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate; // 退勤日

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

	//	業務チェックのため記載
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate; // 出勤日
	
//	業務チェックのため記載
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime startTime; // 出勤時間

}
