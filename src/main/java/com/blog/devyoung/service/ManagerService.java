package com.blog.devyoung.service;

import org.springframework.web.bind.annotation.RequestBody;

import com.blog.devyoung.model.Board;
import com.blog.devyoung.util.ResultObject;

public interface ManagerService {

	public ResultObject boardWrite(Board board);
		
}
