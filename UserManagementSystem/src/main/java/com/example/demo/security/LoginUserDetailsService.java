package com.example.demo.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.repository.LoginUserRepository;

//Spring Securityが認証時に呼び出すサービスクラス
@Component
public class LoginUserDetailsService implements UserDetailsService {
	// ユーザーデータベースからユーザー情報を取得するリポジトリ
	private final LoginUserRepository loginUserRepository;
	
	// コンストラクタインジェクション（Springが自動でDIしてくれる）
	public LoginUserDetailsService(LoginUserRepository loginUserRepository) {
        this.loginUserRepository = loginUserRepository;
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
		  LoginUser loginUser = loginUserRepository.findByUserId(userId);
		    if (loginUser == null) {
		        throw new UsernameNotFoundException("User not found: " + username);
		    }
		    return new LoginUserDetails(loginUser);
		    
		    
		}
		 
//	        // Repositoryを使って userId に該当する LoginUser を取得（Optionalでnull対応）
//	        Optional<LoginUser> loginUserOptional = loginUserRepository.findByUserId(username);
	
//	     // LoginUser が取得できた場合は LoginUserDetails にラップして返す
//	        // なければ UsernameNotFoundException をスローして認証失敗
//	        return loginUserOptional
//	            .map(LoginUserDetails::new) // LoginUserDetails のコンストラクタに渡す
//	            .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
//	    }
	 
//	@Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		 // usernameは「userId」と同じ意味として扱う
////	    User user = userRepository.findByUserId(username);  // DBからユーザー情報を取得
////	    if (user == null) {
////	        throw new UsernameNotFoundException("User not found with username: " + username);
////	    }
////	    // 取得したユーザー情報を元にUserDetailsを作成して返す
////	    return new org.springframework.security.core.userdetails.User(user.getUserId(), user.getPassword(), new ArrayList<>());
////	
////        // ここでDBからユーザーを取得し、UserDetailsとして返す
////        // 例：UserRepository を使って user を取得する
//
//        // ダミーの実装（あとでDB連携に置き換える）
//        if (!username.equals("1")) {
//            throw new UsernameNotFoundException("ユーザーが見つかりません: " + username);
//        }
//
//        // username, password, 権限のリストを指定（ここでは仮の "password" と "ROLE_USER"）
//        return org.springframework.security.core.userdetails.User
//                .withUsername("1")
//             // BCryptで暗号化された仮のパスワード。ハッシュ化前は「test123」（BCryptEncryptionExample.java 参照）
//                .password("$2a$10$5PaQRLGKouDlo0CyfiJzMOaumzPjZe2kxrh.3Y4ewmKppVqsAIsTO\n") 
//                .roles("ADMIN")
//                .build();
//    }
	
	
}
