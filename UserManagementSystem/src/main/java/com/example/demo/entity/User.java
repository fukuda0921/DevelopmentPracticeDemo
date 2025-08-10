package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
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
	private Integer  role;
}
