package com.example.demo.service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.LeavingRegisterMapper;
import com.example.demo.entity.LeavingRegisterEntity;
import com.example.demo.repository.LeavingRegisterRepository;

@Service
public class LeavingRegisterService {
	
	/** 退勤登録Mapper */
	private final  LeavingRegisterMapper leavingRegisterMapper;
	
	/**
	 * コンストラクタインジェクション
	 * 
	 * @param leavingRegisterMapper 退勤登録Mapper
	 */
	public LeavingRegisterService (LeavingRegisterMapper leavingRegisterMapper) {
		this.leavingRegisterMapper = leavingRegisterMapper;
	}
	
	@Autowired
	private LeavingRegisterRepository leavingRegisterrepository;
	
	
	/**
	 * 退勤データの重複チェック（業務チェック）
	 * 
	 * @param userId
	 * @param endDate
	 * @return
	 */
	public boolean isDuplicateAttendance(Integer userId, LocalDate endDate) {
		return leavingRegisterMapper.existsByUserIdAndEndDate(userId, endDate);
	}
	
	/**
	 * 指定したユーザーが出勤しているかを確認
	 * 
	 * @param userId
	 * @param date
	 * @return
	 */
	public boolean existsStartDateForUser(Integer userId, LocalDate endDate) {
	    return leavingRegisterMapper.existsByUserIdAndStartDate(userId, endDate);
	}
	
//	
	/**
	 * 指定した userId の中で、startDate が一番新しい（降順）データを1件だけ取得
	 * 
	 * @param userId
	 * @return
	 */
//	public LocalDate getLatestStartDateByUserId(Integer userId) {
//	    return leavingRegisterrepository.findTopByUserIdOrderByStartDateDesc(userId)
//	            .map(LeavingRegisterEntity::getStartDate)  
//	            .orElse(null);
//	}
	
	/**
	 * 最新の出勤レコードを取得（Optional で返す）
	 * @param userId
	 * @return
	 */
    public Optional<LeavingRegisterEntity> getLatestAttendanceByUserId(Integer userId, LocalDate endDate) {
        return leavingRegisterMapper.findTopByUserIdOrderByStartDateDesc(userId, endDate);
    }
    
    /**
     * 勤怠情報を更新する（save = insert or update）
     * 
     * @param attendance
     */
    public void updateAttendance(LeavingRegisterEntity attendance) {
    	
    	 LocalTime startTime = attendance.getStartTime(); 
         LocalTime endTime = attendance.getEndTime();     
         LocalTime breakTime = attendance.getBreakTime(); 
         
         LocalTime calculatedWorkTime = null; // 計算結果の稼働時間 (LocalTime型)

         if (startTime != null && endTime != null) {
             // 出勤時間・退勤時間の差を求める (Duration を使用)
             Duration totalWorkDuration = Duration.between(startTime, endTime);

             // 休憩時間を Duration に変換し、実稼働時間から引く
             if (breakTime != null) {
                 // LocalTimeの休憩時間をDurationに変換 (例: 1時間30分 -> 90分間のDuration)
                 Duration actualBreakDuration = Duration.ofHours(breakTime.getHour())
                                                 .plusMinutes(breakTime.getMinute());
                 totalWorkDuration = totalWorkDuration.minus(actualBreakDuration);
             }

             // DurationをLocalTimeに変換
             // (例: 25時間30分:01:30:00)
             calculatedWorkTime = LocalTime.MIDNIGHT.plus(totalWorkDuration);
         }

         // 5. 算出した稼働時間を Entity に格納
         attendance.setWorkTime(calculatedWorkTime);
         
    	leavingRegisterMapper.updateAttendance(attendance); 
    }
}
    
//    /**
//     * フォームから渡された DTO を Entity に変換して保存
//     * 
//     * @param dto
//     */
//    public void saveAttendance(LeavingRegisterDto dto) {
//        LeavingRegisterEntity entity = new LeavingRegisterEntity();
//        entity.setUserId(dto.getUserId());
//        entity.setStartDate(dto.getStartDate());
//        entity.setStartTime(dto.getStartTime());
//        entity.setEndDate(dto.getEndDate());
//        entity.setEndTime(dto.getEndTime());
//        entity.setBreakTime(dto.getBreakTime());
//        entity.setRemarks(dto.getRemarks());
//
//        leavingRegisterrepository.save(entity); // insert
//        leavingRegisterrepository.updateWorkTime(entity.getAttendanceId());
//    }
//    
//
//    /**
//     * ユーザーの最新の出勤レコードに退勤情報を登録し、稼働時間を計算する
//     * 
//     * @param userId
//     * @param endTime
//     * @param breakTime
//     * @param remarks
//     */
//    @Transactional
//    public void registerLeaving(Integer userId, LocalTime endTime, LocalTime breakTime, String remarks) {
//        // 出勤日は必ず存在する前提で取得（最新1件）
//        LeavingRegisterEntity latest = leavingRegisterrepository
//            .findByUserIdOrderByStartDateDesc(userId, PageRequest.of(0, 1))
//            .getContent()
//            .get(0);
//        
//        // 情報を更新
//        latest.setEndDate(LocalDate.now());
//        latest.setEndTime(endTime);
//        latest.setBreakTime(breakTime);
//        latest.setRemarks(remarks);
//
//        // 保存（updateされる）
//        leavingRegisterrepository.save(latest);
//
//        // 稼働時間をSQLで計算・更新
//        leavingRegisterrepository.updateWorkTime(latest.getAttendanceId());
//    }
//    
//    
//    }
//
////	// 稼働時間をDBで計算・更新
////    public void calculateAndUpdateWorkTimeInDb(Integer userId, LocalDate endDate) {
////        repository.updateWorkTime(userId, endDate);
////    }
////    
//// // その他の処理（保存など）
////    public void saveAttendance(LeavingRegisterDto form) {
////        LeavingRegisterEntity entity = new LeavingRegisterEntity();
////        entity.setUserId(form.getUserId());
////        entity.setEndDate(form.getEndDate());
////        entity.setEndTime(form.getEndTime());
////        entity.setBreakTime(form.getBreakTime());
////        entity.setRemarks(form.getRemarks());
////
////        repository.save(entity);
////
////        // 保存後にSQLで稼働時間を計算・更新
////        calculateAndUpdateWorkTimeInDb(form.getUserId(), form.getEndDate());
////    }
//    

	
