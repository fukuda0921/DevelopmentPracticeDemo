package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "user_permission_tbl")
public class UserPermission {
	
	@Id
    @Column(name = "user_id")
	private Integer userId;
	
	 @Column(name = "role")
	    private String role;
	 
	 @OneToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
	    private User user;
	 
	   // --- Getter ---
	    public Integer getUserId() {
	        return userId;
	    }

	    public String getRole() {
	        return role;
	    }

	    public User getUser() {
	        return user;
	    }

}
