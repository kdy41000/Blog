package com.blog.devyoung.service;

import java.util.Map;

import com.blog.devyoung.model.User;
import com.blog.devyoung.util.ResultObject;

public interface AuthService {

	public ResultObject signUp(User params);
	
}
