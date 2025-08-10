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
@Table(name = "user_tbl")
public class UserRegistrationEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId; // ユーザーID
	
    @Column(nullable = false)
    private String name; // 氏名
    
    @Column(nullable = false)
    private String kana; // フリガナ
    
    @Column(nullable = false)
    private String address; // メールアドレス
    
    @Column(nullable = false)
    private String password; // パスワード
    
    @Column(nullable = false)
    private Integer role; // 権限
    
    @Column(nullable = false)
	private Integer deleteFlag;
	
}
