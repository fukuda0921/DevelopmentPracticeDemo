package com.example.demo.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

//ユーザー情報をSpring Security用に変換するクラス
public class LoginUserDetails implements UserDetails{
	
	// アプリケーション独自のユーザー情報を保持する
	private final LoginUser loginUser;
	
	// コンストラクタでLoginUserを受け取り、保持
	 public LoginUserDetails(LoginUser loginUser) {
	        this.loginUser = loginUser;
	    }

	// ユーザーの権限リスト（ロール）をSpring Security用の形式に変換して返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + loginUser.getRole()));
		    }
	
	// ユーザーのパスワードを返す（認証で使用）
	 @Override
	 public String getPassword() {
	        return loginUser.getPassword();
	    }
	
	// ユーザー名（今回はuserId）を返す（認証で使用） 
	 @Override
	    public String getUsername() {
		 return String.valueOf(loginUser.getUserId()); // Integer → String に変換して返す
	    }
	 
	// アカウントの有効期限が切れていないか（今回は常に有効）
	 @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }
	 
	// アカウントがロックされていないか（今回は常に有効）
	 @Override
	    public boolean isAccountNonLocked() {
	        return true;
	    }
	 
	// 資格情報（パスワード）の有効期限が切れていないか（今回は常に有効）
	 @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }
	 
	// アカウントが有効かどうか（今回は常に有効） 
	 @Override
	    public boolean isEnabled() {
	        return true;
	    }

	// LoginUserオブジェクトを取得するためのメソッド（画面などで使いたいとき用）
	    public LoginUser getLoginUser() {
	        return loginUser;
	    }
	    // LoginUserの権限を返す（必要に応じて）
	    public String getRole() {
	        return loginUser.getRole(); // 例えば、"1"（管理者）や"2"（一般）を返す
	    }
	 // userIdを直接取り出すメソッド  
	    public Integer getUserId() {
	        return loginUser.getUserId();
	    }

}

