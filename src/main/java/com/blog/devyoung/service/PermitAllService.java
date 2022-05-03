package com.blog.devyoung.service;

import java.util.List;

import com.blog.devyoung.model.Board;

public interface PermitAllService {

	public List<Board> boardList();
	
	public Board boardDetail(String id);
	
}
