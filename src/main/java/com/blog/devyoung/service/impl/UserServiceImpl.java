package com.blog.devyoung.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.devyoung.model.User;
import com.blog.devyoung.repository.UserRepository;
import com.blog.devyoung.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User info(User user) {
		return userRepository.findByUserId(user.getUserId()); 
	}

	
}
