package com.example.demo.dto;

import com.example.demo.entity.UserListEntity;

public class UserListDto {

	private Integer userId;
	private String name;
	private String kana;
	private String address;
	private String password; // 生のパスワード（外部にはマスク済みで出力）
	private String role;

	public UserListDto() {
	}

	 // UserListEntityを引数に取るコンストラクタ
    public UserListDto(UserListEntity entity) {
        this.userId = entity.getUserId();
        this.name = entity.getName();
        this.kana = entity.getKana();
        this.address = entity.getAddress();
        this.password = entity.getPassword();
//        this.role = entity.getRole();
    }

	// --- ゲッターとセッター ---

	//ユーザーID
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	//	氏名
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//	フリガナ
	public String getKana() {
		return kana;
	}

	public void setKana(String kana) {
		this.kana = kana;
	}

	//	アドレス
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	//	パスワード
	// ❗️パスワードのゲッターはマスク済み文字列を返す
	public String getPassword() {
		if (password == null || password.isEmpty()) {
			return "";
		}
		return "*".repeat(password.length());
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//	権限
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
