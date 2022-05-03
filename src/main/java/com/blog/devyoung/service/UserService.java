package com.blog.devyoung.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.blog.devyoung.model.User;

public interface UserService {

	public User info(User user);
}
