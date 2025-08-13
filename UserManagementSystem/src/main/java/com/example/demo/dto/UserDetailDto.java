package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDetailDto {
	
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
