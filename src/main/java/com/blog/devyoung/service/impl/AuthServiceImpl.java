package com.blog.devyoung.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.devyoung.config.jwt.JwtAuthenticationFilter;
import com.blog.devyoung.model.User;
import com.blog.devyoung.repository.AuthRepository;
import com.blog.devyoung.service.AuthService;
import com.blog.devyoung.util.ResultObject;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private AuthRepository authRepository;
	
	Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	
	@Override
	public ResultObject signUp(User params) {
		ResultObject resultObj = new ResultObject();
		params.setPassword(bCryptPasswordEncoder.encode(params.getPassword()));
		params.setRoles("ROLE_USER");
		
		try {
			long unique = authRepository.countByUserId(params.getUserId());
			if(unique > 0) {
				resultObj.setReturnCode(-1);
				resultObj.setReturnMsg("�ߺ��Ǵ� ���̵� �����մϴ�.");
			} else {
				authRepository.save(params);
				resultObj.setReturnCode(1);
				resultObj.setReturnMsg("ȸ�������� �Ϸ�Ǿ����ϴ�.");
			}
		} catch (Exception e) {
			resultObj.setReturnCode(-1);
			resultObj.setReturnMsg("������ �߻��Ͽ����ϴ�.");
			logger.error("signup error::{}", e);
		}
		return resultObj;
	}


}
