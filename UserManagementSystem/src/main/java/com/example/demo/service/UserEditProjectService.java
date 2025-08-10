package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class UserEditProjectService {

//	@Autowired
//	private UserEditProjectRepository userEditProjectRepository;
//
//	@Autowired
//	private UserEditRepository userEditRepository;
//
//	public UserEditProjectEntity convertToEntity(UserEditProjectDto projectDto, UserEditEntity user) {
//	    UserEditProjectEntity entity = new UserEditProjectEntity();
//	    entity.setProjectId(projectDto.getProjectId());
//	    entity.setProjectName(projectDto.getProjectName());
//	    entity.setRemoteAvailability(projectDto.getRemoteAvailability());
//	    entity.setWorkLocation(projectDto.getWorkLocation());
//	    entity.setJobDescription(projectDto.getJobDescription());
//	    entity.setProgrammingLanguage(projectDto.getProgrammingLanguage());
//	    entity.setRemarks(projectDto.getRemarks());
//	    entity.setUser(user); // UserEditEntity のセット
//	    return entity;
//	}
//
//	// プロジェクト情報更新
//	public void updateProject(UserEditProjectEntity projectEntity) {
//		UserEditProjectEntity entity = userEditProjectRepository.findById(projectEntity.getProjectId())
//				.orElseThrow(() -> new RuntimeException("プロジェクトが見つかりません（ID: " + projectEntity.getProjectId() + "）"));
//
//		entity.setProjectId(projectEntity.getProjectId());
//		entity.setProjectName(projectEntity.getProjectName());
//		entity.setRemoteAvailability(projectEntity.getRemoteAvailability());
//		entity.setWorkLocation(projectEntity.getWorkLocation());
//		entity.setJobDescription(projectEntity.getJobDescription());
//		entity.setProgrammingLanguage(projectEntity.getProgrammingLanguage());
//		entity.setRemarks(projectEntity.getRemarks());
//
//		// UserEditEntity の取得
//		UserEditEntity user = userEditRepository.findById(projectEntity.getUser().getUserId())
//				.orElseThrow(() -> new RuntimeException("ユーザーが見つかりません"));
//
//		// UserEditProjectEntity の user フィールドに UserEditEntity をセットする
//		entity.setUser(user);
//
//		userEditProjectRepository.save(entity);
//	}
//
//	// プロジェクト削除
//	public void deleteProject(Integer projectId) {
//		userEditProjectRepository.deleteById(projectId);
//	}
//	
//	// プロジェクトIDからDTOを取得
//	public UserEditProjectDto findByProjectId(Integer projectId) {
//		UserEditProjectEntity entity = userEditProjectRepository.findById(projectId)
//				.orElseThrow(() -> new RuntimeException("プロジェクトが見つかりません（ID: " + projectId + "）"));
//
//		return convertToDto(entity);
//	}
//
//	// Entity → DTO 変換メソッド
//	private UserEditProjectDto convertToDto(UserEditProjectEntity entity) {
//		UserEditProjectDto dto = new UserEditProjectDto();
//		dto.setProjectId(entity.getProjectId());
//		dto.setProjectName(entity.getProjectName());
//		dto.setRemoteAvailability(entity.getRemoteAvailability());
//		dto.setWorkLocation(entity.getWorkLocation());
//		dto.setJobDescription(entity.getJobDescription());
//		dto.setProgrammingLanguage(entity.getProgrammingLanguage());
//		dto.setRemarks(entity.getRemarks());
//		// 必要に応じてユーザー情報なども設定
//		return dto;
//	}

}
