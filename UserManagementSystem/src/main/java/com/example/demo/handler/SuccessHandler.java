package com.example.demo.handler;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.security.LoginUserDetails;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response,
			Authentication authentication)
			throws IOException, ServletException {
		// ログインユーザー情報から userId を取得
		var userDetails = (LoginUserDetails) authentication.getPrincipal();
		Integer userId = userDetails.getUserId(); // ← DBに保存されてるuserId

		// /home/{userId} にリダイレクトする
		response.sendRedirect("/home/" + userId);
	}

}
