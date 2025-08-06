package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ProjectListEntity;

@Repository
public interface ProjectListRepository extends JpaRepository<ProjectListEntity, Integer>{
	// ユーザーIDごとにデータを取得
    List<ProjectListEntity> findByUserId(Integer userId);

}
