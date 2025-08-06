package com.example.demo.repository;

import java.time.LocalDate;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeavingRegisterEntity;
@Repository
public interface LeavingRegisterRepository extends JpaRepository<LeavingRegisterEntity, Integer>{		
//		Spring Data JPA がこのメソッド名から自動的に SQL を生成
//		EXISTS:データが存在するかどうかを判定
		boolean existsByUserIdAndEndDate(Integer userId, LocalDate endDate);
		boolean existsByUserIdAndStartDate(Integer userId, LocalDate startDate);
		Optional<LeavingRegisterEntity> findTopByUserIdOrderByStartDateDesc(Integer userId);
		
		 // 最新の出勤レコードを1件取得（JPQLでPageableを使用）
	    Page<LeavingRegisterEntity> findByUserIdOrderByStartDateDesc(Integer userId, Pageable pageable);

		
	 // 稼働時間をSQLで更新する（ネイティブクエリ）
	    @Modifying
	    @Transactional
	    @Query(value = """
	        UPDATE attendance_tbl
	        SET work_time = (end_time - start_time - break_time)
	        WHERE attendance_id = :attendanceId
	    """, nativeQuery = true)
	    void updateWorkTime(@Param("attendanceId") Integer attendanceId);
	}
		
	



