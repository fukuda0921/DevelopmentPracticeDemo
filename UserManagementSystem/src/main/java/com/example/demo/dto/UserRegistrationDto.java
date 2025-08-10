package com.example.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserRegistrationDto {

	private Integer userId; // ユーザーID

	@NotBlank(message = "氏名を入力してください")
	@Size(max = 20, message = "20文字以内で入力してください")
	@Pattern(regexp = "^[^\\x00-\\x7F]+$", message = "全角で入力してください")
	private String name; // 氏名

	@NotBlank(message = "フリガナを入力してください")
	@Size(max = 20, message = "20文字以内で入力してください")
	@Pattern(regexp = "^[^\\x00-\\x7F]+$", message = "全角で入力してください")
	private String kana; // フリガナ

	@NotBlank(message = "メールアドレスを入力してください")
	@Size(max = 60, message = "60文字以下で入力してください")
	@Email(regexp = "[\\w\\-._]+@[\\w\\-._]+\\.[A-Za-z]+",
			message = "メールアドレスの形式で入力してください")
//	@Pattern(regexp = "^[a-zA-Z0-9._-]+$",message = "半角で入力してください")
	private String address; // メールアドレス

	@NotBlank(message = "パスワードを入力してください")
	@Size(max = 20, message = "60文字以下で入力してください")
	@Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "半角で入力してください")
	private String password; // パスワード

	@NotNull(message = "権限を選択してください")
	private Integer role; // 権限

	// ゲッターとセッター

	// ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	// 氏名
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	// フリガナ
	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}
	
	// メールアドレス
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	// パスワード
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	// 権限
	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}
}
