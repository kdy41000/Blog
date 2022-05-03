package com.blog.devyoung.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.devyoung.model.Board;
import com.blog.devyoung.service.PermitAllService;

@RestController
@RequestMapping("/api/v1")
public class PermitAllController {

	@Autowired
	private PermitAllService permitAllService;
	
	@GetMapping("/board/list")
	public List<Board> boardList() {
		return permitAllService.boardList();
	}
	
	@GetMapping("/board/detail")
	public Board boardDetail(String id) {
		return permitAllService.boardDetail(id);
	}
	
}
