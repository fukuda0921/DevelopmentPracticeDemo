package com.example.demo.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.GoingRegisterEntity;

@Repository
public interface GoingRegisterRepository extends JpaRepository<GoingRegisterEntity, Integer> {
	
//	Spring Data JPA がこのメソッド名から自動的に SQL を生成
//	EXISTS:データが存在するかどうかを判定
	boolean existsByUserIdAndStartDate(Integer userId, LocalDate startDate);
}
