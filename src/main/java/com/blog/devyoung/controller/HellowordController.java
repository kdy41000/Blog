package com.blog.devyoung.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HellowordController {

	@GetMapping("hello")
	public List<String> hello() {
		System.out.println("hjelloo");
		return Arrays.asList("¾È³ç","hello");
	}
	
	@PostMapping("/api/v1/login")
	public void login(@RequestBody Map<String, Object> param) {
		System.out.println("==== login =====");
		System.out.println(param.toString());
	}
	
	@GetMapping("/api/v1/login")
	public void login() {
		System.out.println("==== login =====");
	}
	
}
