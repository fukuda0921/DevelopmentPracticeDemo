package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "project_tbl")
public class ProjectRegistrationEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
    private Integer projectId; // 勤怠ID

	@Column(name = "user_id", nullable = false)
    private Integer userId; // ユーザーID
    
    @Column(name = "project_name", nullable = false) 
    private String projectName; // 案件名
    
    @Column(name = "remote_availability", nullable = false) 
    private String remoteAvailability; // リモート可否
    
    @Column(name = "work_location", nullable = false)
    private String workLocation; // 稼働場所
    
    @Column(name = "job_description", nullable = false)
    private String jobDescription; // 業務内容
    
    @Column(name = "programming_language", nullable = false)
    private String programmingLanguage; // 使用言語
    
    @Column(name = "remarks", nullable = false)
    private String remarks; // 備考
    
    @Column(name = "delete_flag", nullable = false)
    private Integer deleteFlag; 
    
}
