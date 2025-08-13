package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LoginUserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.security.LoginUser;
import com.example.demo.security.LoginUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserService implements UserDetailsService {

	private final LoginUserMapper loginUserMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// ユーザー名（ここではユーザーIDを想定）をIntegerに変換
		Integer userId = null;
		try {
			userId = Integer.valueOf(username); // user_id が String で渡される前提
		} catch (NumberFormatException e) {
			throw new UsernameNotFoundException("ユーザーIDの形式が不正です: " + username);
		}

		Optional<UserEntity> userOptional = loginUserMapper.findByUserId(userId);
		UserEntity userEntity = userOptional.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません: " + username));

		// User オブジェクトから LoginUser を生成
		// user.getRole() は Integer を返すので、String に変換してリストに入れる
		String roleString = userEntity.getRole() != null ? String.valueOf(userEntity.getRole()) : "UNKNOWN_ROLE";
		List<String> roles = Arrays.asList(roleString);

		LoginUser loginUser = new LoginUser(
				userEntity.getUserId(), // UserからUserIdを取得
				userEntity.getPassword(), // UserからPasswordを取得
				roles // 変換したロールリスト
		);

		// LoginUserDetails にラップして返却
		return new LoginUserDetails(loginUser);
	}

}
