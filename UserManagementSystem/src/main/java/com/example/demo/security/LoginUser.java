package com.example.demo.security;

import java.util.List;

//ログインユーザーの情報を保持するクラス（アプリケーション内部用のユーザーモデル）

public class LoginUser {
	private Integer userId;// ユーザーID（ログインに使用）
    private String password;// ユーザーのパスワード（ハッシュ化された値を想定）
    private List<String> roleList;// 権限（"1" = 管理者、"2" = 一般）を表す文字列のリスト

 // コンストラクタですべてのフィールドを初期化
    public LoginUser(Integer userId, String password, List<String> roleList) {
        this.userId = userId;
        this.password = password;
        this.roleList = roleList;
    }

 // ユーザーIDの取得
    public Integer getUserId() {
        return userId;
    }
    
 // パスワードの取得
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

 // ロール一覧の取得
    public List<String> getRoleList() {
        return roleList;
    }
    
    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }
    
 // 役割（ロール）を返すメソッド
    public String getRole() {
        // 役割がリストに存在すればその最初のロールを返す
        return roleList.isEmpty() ? "ROLE_UNKNOWN" : roleList.get(0);
    }
}
