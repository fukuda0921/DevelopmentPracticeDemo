package com.example.demo.entity;

import lombok.Data;

@Data
public class UserDetailEntity {
	
	/*
	 * ユーザーID
	 */
	private Integer userId;
	
	/*
	 * 氏名
	 */
    private String name;
    
    /*
     * フリガナ
     */
    private String kana;
    
    /*
     * メールアドレス
     */
    private String address;
    
    /*
     * 権限
     */
    private Integer role;

}
