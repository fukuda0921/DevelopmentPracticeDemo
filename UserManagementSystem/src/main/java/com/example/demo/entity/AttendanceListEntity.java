package com.example.demo.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "attendance_tbl")
public class AttendanceListEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer attendanceId; // 勤怠ID

    @Column(nullable = false)
    private Integer userId; // ユーザーID

    @Column(nullable = false)
    private LocalDate startDate; // 出勤日
    
    @Column(name = "leaving_date", nullable = false)
	private LocalDate leavingDate; // 退勤日

    @Column(nullable = false)
    private LocalTime startTime; // 出勤時間
    
    @Column(name = "leaving_time", nullable = false)
	private LocalTime leavingTime; // 退勤時間

    @Column(name = "operation_time", nullable = false)
	private LocalTime operationTime; // 稼働時間

	@Column(nullable = false)
	private LocalTime breakTime; // 休憩時間

    @Column(length = 100)
    private String remarks; // 備考
    
    private String name; 

}
