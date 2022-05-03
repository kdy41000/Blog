package com.blog.devyoung.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.devyoung.model.User;
import com.blog.devyoung.service.UserService;
import com.blog.devyoung.util.ResultObject;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/info")
	public User info(@RequestBody User user) {
		return userService.info(user);
	}
	
}
