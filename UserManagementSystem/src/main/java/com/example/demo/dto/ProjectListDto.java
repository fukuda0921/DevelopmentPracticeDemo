package com.example.demo.dto;

import com.example.demo.entity.ProjectListEntity;

public class ProjectListDto {

	private Integer projectId; //案件ID
	private Integer userId; //ユーザーID
	private String projectName; //案件名
	private String remoteAvailability; //リモート可否
	private String workLocation; //稼働場所
	private String jobDescription; //業務内容
	private String programmingLanguage; //使用言語
	private String remarks; //備考

	public ProjectListDto() {
	}

	// ProjectListEntityを引数に取るコンストラクタ
	public ProjectListDto(ProjectListEntity projectListentity) {
		this.projectId = projectListentity.getProjectId();
		this.userId = projectListentity.getUserId();
		this.projectName = projectListentity.getProjectName();
		this.remoteAvailability = projectListentity.getRemoteAvailability();
		this.workLocation = projectListentity.getWorkLocation();
		this.jobDescription = projectListentity.getJobDescription();
		this.programmingLanguage = projectListentity.getProgrammingLanguage();
		this.remarks = projectListentity.getRemarks();
	}

	// --- ゲッターとセッター ---

	//案件ID
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	//ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	//案件名
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	//リモート可否
	public String getRemoteAvailability() {
		return remoteAvailability;
	}

	public void setRemoteAvailability(String remoteAvailability) {
		this.remoteAvailability = remoteAvailability;
	}

	//稼働場所
	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	//業務内容
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	//使用言語
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	// 備考
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
