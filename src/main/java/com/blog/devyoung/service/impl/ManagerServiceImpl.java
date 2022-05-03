package com.blog.devyoung.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.devyoung.model.Board;
import com.blog.devyoung.repository.ManagerRepository;
import com.blog.devyoung.service.ManagerService;
import com.blog.devyoung.util.ResultObject;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	ManagerRepository managerRepository;
	
	Logger logger = LoggerFactory.getLogger(ManagerServiceImpl.class);
	
	@Override
	public ResultObject boardWrite(Board board) {
		ResultObject resultObject = new ResultObject();
		try {
			managerRepository.save(board);
			resultObject.setReturnCode(1);
			resultObject.setReturnMsg("등록이 완료되었습니다.");
		} catch (Exception e) {
			resultObject.setReturnCode(-1);
			resultObject.setReturnMsg("등록에 실패하였습니다.");
			logger.error("boardWrite error {}",e);
		}
		return resultObject;
	}

}
