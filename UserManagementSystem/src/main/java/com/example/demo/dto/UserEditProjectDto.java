package com.example.demo.dto;

import com.example.demo.entity.UserEditProjectEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserEditProjectDto {
	
	private Integer userId;
	private Integer projectId;
    private String projectName;
    private String remoteAvailability;
    private String workLocation;
    private String jobDescription;
    private String programmingLanguage;
    private String remarks;
    
    public UserEditProjectDto() {
    }
    
    public UserEditProjectDto(UserEditProjectEntity entity) {
    	// userIdはentity.getUser()がnullじゃなければentity.getUser().getUserId()を使う
        this.userId = (entity.getUser() != null) ? entity.getUser().getUserId() : null;
        this.projectId = entity.getProjectId();
        this.projectName = entity.getProjectName();
        this.remoteAvailability = entity.getRemoteAvailability();
        this.workLocation = entity.getWorkLocation();
        this.jobDescription = entity.getJobDescription();
        this.programmingLanguage = entity.getProgrammingLanguage();
        this.remarks = entity.getRemarks();
    }

}
