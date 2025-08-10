package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.UserPermission;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, Integer>  {
//	@Query("SELECT a FROM UserPermission a JOIN FETCH a.user WHERE a.user.userId = :userId")
//    Optional<UserPermission> findWithUserByUserId(@Param("userId") String userId);

}

