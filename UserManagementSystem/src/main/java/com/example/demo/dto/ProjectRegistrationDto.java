package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ProjectRegistrationDto {

	private Integer projectId; // 案件ID

	private Integer userId; // ユーザーID

	@NotBlank(message = "案件名を入力してください")
	@Size(max = 100, message = "100文字以下で入力してください")
	private String projectName; // 案件名

	@NotNull(message = "リモート可否を選択してください")
	private String remoteAvailability; // リモート可否

	@NotBlank(message = "稼働場所を入力してください")
	@Size(max = 60, message = "60文字以下で入力してください")
	private String workLocation; // 稼働場所

	@NotBlank(message = "業務内容を入力してください")
	@Size(max = 100, message = "100文字以下で入力してください")
	private String jobDescription; // 業務内容

	@NotBlank(message = "使用言語を入力してください")
	@Size(max = 20, message = "20文字以下で入力してください")
	private String programmingLanguage; // 使用言語

	@Size(max = 100, message = "備考は100文字以内で入力してください")
	private String remarks; // 備考

	// ゲッターとセッター

	// 案件ID
	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	// ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	// 案件名
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	// リモート可否
	public String getRemoteAvailability() {
		return remoteAvailability;
	}

	public void setRemoteAvailability(String remoteAvailability) {
		this.remoteAvailability = remoteAvailability;
	}

	// 稼働場所
	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	// 業務内容
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	// 使用言語
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
