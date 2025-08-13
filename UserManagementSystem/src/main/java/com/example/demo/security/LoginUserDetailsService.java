package com.example.demo.security;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.dao.LoginUserMapper;
import com.example.demo.entity.UserEntity;

//Spring Securityが認証時に呼び出すサービスクラス
@Component
public class LoginUserDetailsService implements UserDetailsService {
	
	/** ユーザーMapper */
	private final LoginUserMapper loginUserMapper; 
	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param loginUserMapper
	 */
	public LoginUserDetailsService(LoginUserMapper loginUserMapper) { 
        this.loginUserMapper = loginUserMapper;
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
		  
		    Optional<UserEntity> userOptional = loginUserMapper.findByUserId(userId);
		    UserEntity userEntity = userOptional.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + username));
		    
		    String roleString = userEntity.getRole() != null ? String.valueOf(userEntity.getRole()) : "UNKNOWN_ROLE";
		    List<String> roles = Arrays.asList(roleString);

		    LoginUser loginUser = new LoginUser(
		        userEntity.getUserId(),    // UserからUserIdを取得
		        userEntity.getPassword(),  // UserからPasswordを取得
		        roles                // 変換したロールリスト
		    );
		    
		    return new LoginUserDetails(loginUser);
		}
	
}
