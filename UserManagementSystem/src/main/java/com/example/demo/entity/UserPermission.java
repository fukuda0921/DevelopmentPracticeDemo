package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_permission_tbl")
public class UserPermission {
	
	@Id
    @Column(name = "role_id")
	private Integer orleId;
	
	 @Column(name = "name")
	    private String name;

}
