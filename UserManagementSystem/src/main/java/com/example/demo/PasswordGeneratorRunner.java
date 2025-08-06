//package com.example.demo;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component // これを付与することでSpringがコンポーネントとして認識し、自動的に実行してくれる
//public class PasswordGeneratorRunner implements CommandLineRunner {
//
//    private final PasswordEncoder passwordEncoder;
//
//    // コンストラクタインジェクションでPasswordEncoderを取得
//    public PasswordGeneratorRunner(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        String rawPassword = "testpassword123"; // DBに入れたい生のパスワード
//        String hashedPasswordForTest = passwordEncoder.encode(rawPassword);
//        System.out.println("--------------------------------------------------");
//        System.out.println("テスト用ハッシュパスワード (生: " + rawPassword + "): " + hashedPasswordForTest);
//        System.out.println("--------------------------------------------------");
//    }
//}
