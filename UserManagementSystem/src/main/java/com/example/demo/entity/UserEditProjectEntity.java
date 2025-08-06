package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "project")
public class UserEditProjectEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "remote_availability")
	private String remoteAvailability;

	@Column(name = "work_location")
	private String workLocation;

	@Column(name = "job_description")
	private String jobDescription;

	@Column(name = "programming_language")
	private String programmingLanguage;

	@Column(name = "remarks")
	private String remarks;

	// 複数のプロジェクトは1人のユーザーに
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id") // projectテーブルの外部キー
	private UserEditEntity user;

}
