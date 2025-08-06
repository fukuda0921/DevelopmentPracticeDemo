package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserEditProjectEntity;

@Repository
public interface UserEditProjectRepository extends JpaRepository<UserEditProjectEntity, Integer> {
	// 案件IDごとにデータを取得
	Optional<UserEditProjectEntity> findByProjectId(Integer projectId);

}
