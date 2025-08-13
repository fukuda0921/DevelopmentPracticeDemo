package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.handler.SuccessHandler;
import com.example.demo.security.LoginUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class UserSecurityConfig {
	
	private final SuccessHandler successHandler;
	public UserSecurityConfig(SuccessHandler successHandler) {
        this.successHandler = successHandler;
    }
	
	@Autowired
	private LoginUserDetailsService loginUserDetailsService;
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
	    provider.setUserDetailsService(loginUserDetailsService);
	    provider.setPasswordEncoder(passwordEncoder()); // BCryptを使う
	    return provider;
	}
	
	 //フォームの値と比較するDBから取得したパスワードは暗号化されているのでフォームの値も暗号化するために利用
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	// セキュリティ設定
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                		.requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()// ログインページへのアクセスを許可
                		.anyRequest().authenticated() // その他のページには認証が必要
                		)
        .formLogin(formLogin -> formLogin
        		.loginPage("/login")
                .permitAll() // ログインページを許可
                .successHandler(successHandler) 
                .failureUrl("/login?error") // ログイン失敗時の遷移先
            );

	    return http.build();
	}
}
