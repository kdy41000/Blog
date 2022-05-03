package com.blog.devyoung.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.devyoung.model.Board;
import com.blog.devyoung.repository.PermitAllRepository;
import com.blog.devyoung.service.PermitAllService;

@Service
public class PermitAllServiceImpl implements PermitAllService {
	
	@Autowired
	PermitAllRepository permitAllRepository;

	@Override
	public List<Board> boardList() {
		return permitAllRepository.findAllByOrderByWriteDateDesc();
	}

	@Override
	public Board boardDetail(String id) {
		long l = Long.parseLong(id);
		Board board = permitAllRepository.findById(l);
		if(board != null) {
			board.setView(board.getView() + 1);
			permitAllRepository.save(board);
		}
		return permitAllRepository.findById(l);
	}

}
