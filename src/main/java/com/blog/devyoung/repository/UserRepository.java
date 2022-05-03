package com.blog.devyoung.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.devyoung.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserId(String userId);
	
}
