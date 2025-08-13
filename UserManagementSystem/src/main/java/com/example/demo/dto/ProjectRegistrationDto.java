package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
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


}
