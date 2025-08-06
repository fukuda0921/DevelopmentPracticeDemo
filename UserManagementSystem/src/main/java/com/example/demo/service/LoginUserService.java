package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserPermission;
import com.example.demo.repository.UserPermissionRepository;
import com.example.demo.security.LoginUser;
import com.example.demo.security.LoginUserDetails;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginUserService implements UserDetailsService {
	private final UserPermissionRepository userPermissionRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// ログインユーザーIDに基づく権限情報を取得
		UserPermission permission = userPermissionRepository.findWithUserByUserId(username)
			.orElseThrow(() -> new UsernameNotFoundException("ユーザーが見つかりません"));
		
		// UserPermission から LoginUser を生成
		   LoginUser loginUser = new LoginUser(
			        permission.getUser().getUserId(),  // UserPermission から UserIdを取得
			        permission.getUser().getPassword(), // UserPermission から Passwordを取得
			        Arrays.asList(permission.getRole()) // ← String を List<String> に変換
	);// 役割情報（ロール）を取得
				// LoginUserDetails にラップして返却
				return new LoginUserDetails(loginUser);
	}

	// 任意：別用途で全ユーザー取得
	public List<UserPermission> getAllUsers() {
		return userPermissionRepository.findAll();
	}
}
