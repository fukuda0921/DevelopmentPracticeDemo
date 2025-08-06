package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ProjectRegistrationDto;
import com.example.demo.entity.ProjectRegistrationEntity;
import com.example.demo.repository.ProjectRegistrationRepository;

@Service
public class ProjectRegistrationService {
	@Autowired
	private ProjectRegistrationRepository projectRegistrationRepository;
	
	public void saveProject(ProjectRegistrationDto form) {
		ProjectRegistrationEntity entity = new ProjectRegistrationEntity();
		entity.setUserId(form.getUserId());
		entity.setProjectName(form.getProjectName());
		entity.setRemoteAvailability(form.getRemoteAvailability());
		entity.setWorkLocation(form.getWorkLocation());
		entity.setJobDescription(form.getJobDescription());
		entity.setProgrammingLanguage(form.getProgrammingLanguage());
		entity.setRemarks(form.getRemarks());

		projectRegistrationRepository.save(entity);
	}

}
