package com.blog.devyoung.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.devyoung.config.jwt.JwtAuthenticationFilter;
import com.blog.devyoung.model.User;
import com.blog.devyoung.service.AuthService;
import com.blog.devyoung.util.ResultObject;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private AuthService authService;

	@PostMapping("/signup")
	public ResultObject signUp(@RequestBody User params) {
		return authService.signUp(params); 
	}
	
}
