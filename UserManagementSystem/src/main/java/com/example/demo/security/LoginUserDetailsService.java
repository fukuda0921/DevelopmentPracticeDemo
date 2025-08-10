package com.example.demo.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.UserMapper;
import com.example.demo.entity.User;

//Spring Securityが認証時に呼び出すサービスクラス
@Component
public class LoginUserDetailsService implements UserDetailsService {
	
	/** ユーザーMapper */
	private final UserMapper userMapper; 
	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param userMapper
	 */
	public LoginUserDetailsService(UserMapper userMapper) { 
        this.userMapper = userMapper;
    }
	
	// ユーザー名（ここでは userId）をもとに、ユーザー情報をロードするメソッド
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Integer userId;
		 try {
		        userId = Integer.valueOf(username);
		    } catch (NumberFormatException e) {
		        throw new UsernameNotFoundException("ユーザーIDの形式が不正です: " + username);
		    }
		  
		    Optional<User> userOptional = userMapper.findByUserId(userId);
		    User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + username));
		    
		    String roleString = user.getRole() != null ? String.valueOf(user.getRole()) : "UNKNOWN_ROLE";
		    List<String> roles = Arrays.asList(roleString);

		    LoginUser loginUser = new LoginUser(
		        user.getUserId(),    // UserからUserIdを取得
		        user.getPassword(),  // UserからPasswordを取得
		        roles                // 変換したロールリスト
		    );
		    
		    return new LoginUserDetails(loginUser);
		}
	
}
