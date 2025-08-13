package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dao.UserDetailMapper;
import com.example.demo.dto.UserDetailDto;
import com.example.demo.entity.UserDetailEntity;

@Service
public class UserDetailService {
	
	 private final UserDetailMapper userDetailMapper;
	 
	 public UserDetailService(UserDetailMapper userDetailMapper){
		 this.userDetailMapper = userDetailMapper;
	 }
	    /**
	     * ユーザー詳細情報を取得
	     * @param userId ユーザーID
	     * @return ユーザー詳細DTO
	     * @throws RuntimeException ユーザーが見つからない場合
	     */
	    public UserDetailDto getUserDetail(Integer userId) {
	        // ユーザー情報を取得
	        UserDetailEntity userEntity = userDetailMapper.findByUserId(userId);
	        if (userEntity == null) {
	            throw new RuntimeException("指定されたユーザーが見つかりません。ID: " + userId);
	        }

	        // DTOに変換
	        UserDetailDto userDetailDto = convertToUserDetailDto(userEntity);

	        return userDetailDto;
	    }

	    /**
	     * UserDetailEntityをUserDetailDtoに変換する
	     */
	    private UserDetailDto convertToUserDetailDto(UserDetailEntity entity) {
	        UserDetailDto dto = new UserDetailDto();
	        dto.setUserId(entity.getUserId());
	        dto.setName(entity.getName());
	        dto.setKana(entity.getKana());
	        dto.setAddress(entity.getAddress());
	        dto.setRole(entity.getRole());
	        return dto;
	    }

}
