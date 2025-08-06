package com.example.demo.until;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashGenerator {

    public static void main(String[] args) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        // 任意の平文パスワード
        String rawPassword = "test1234";

        // ハッシュ化
        String hashedPassword = encoder.encode(rawPassword);

        // コンソール出力
        System.out.println("==== パスワードのハッシュ化確認 ====");
        System.out.println("元パスワード: " + rawPassword);
        System.out.println("ハッシュ化後: " + hashedPassword);
        System.out.println("===================================");
    }
}