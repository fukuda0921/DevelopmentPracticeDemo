package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.AttendanceEditEntity;

@Repository
public interface AttendanceEditRepository extends JpaRepository<AttendanceEditEntity, Integer> {
	Optional<AttendanceEditEntity> findByUserId(Integer userId);
	Optional<AttendanceEditEntity> findByAttendanceId(Integer attendanceId);
}