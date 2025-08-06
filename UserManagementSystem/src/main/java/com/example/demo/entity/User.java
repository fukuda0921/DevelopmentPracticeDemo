package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_tbl")
public class User {

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

	@Column(name = "role")
	private String role;

	@OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
	private UserPermission userPermission;

	// --- Getter ---
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

	public UserPermission getUserPermission() {
		return userPermission;
	}

	public String getRole() {
		return role;
	}

}
