package com.blog.devyoung.config.jwt;

public interface JwtProperties {

	String SUB = "devyoung-sub";
	String SECRET = "DEVYoung";  // ������ �˰��ִ� secret key
	int EXPIRATION_TIME = 864000000;
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
	
}
