package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEditEntity;

@Repository
public interface UserEditRepository extends JpaRepository<UserEditEntity, Integer>{
	// ユーザーIDごとにデータを取得
	@Query("SELECT u FROM UserEditEntity u LEFT JOIN FETCH u.projects WHERE u.userId = :userId")
	Optional<UserEditEntity> findByUserId(@Param("userId") Integer userId);

}
