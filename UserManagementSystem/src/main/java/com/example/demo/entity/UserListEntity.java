package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tbl")
public class UserListEntity {

	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "name")
	private String name;

	@Column(name = "kana")
	private String kana;

	@Column(name = "address")
	private String address;

	@Column(name = "password")
	private String password;

//	@Column(name = "role") 
//	private String role; // ロール情報を格納

	// --- ゲッターとセッター ---
	public Integer getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getKana() {
		return kana;
	}

	public String getAddress() {
		return address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public String getRole() {
//		return role;
//	}
//
//	public void setRole(String role) {
//		this.role = role;
//	}

}
