package com.blog.devyoung.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.devyoung.model.User;

public interface AuthRepository extends JpaRepository<User, Long> {

	long countByUserId(String userId);
	
	User findByUserId(String userId);
	
}
