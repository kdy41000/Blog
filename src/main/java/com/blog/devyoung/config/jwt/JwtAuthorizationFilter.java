package com.blog.devyoung.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.blog.devyoung.config.auth.PrincipalDetails;
import com.blog.devyoung.model.User;
import com.blog.devyoung.repository.AuthRepository;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter implements JwtProperties {

	private AuthRepository repository;
	
	public JwtAuthorizationFilter(AuthenticationManager authenticationManager, AuthRepository repository) {
		super(authenticationManager);
		this.repository = repository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("인증이나 권한이 필요한 주소 요청이 됨.");
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader: " + jwtHeader);
		
		// header가 있는지 확인, "Bearer " 로 시작하는지 확인 
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// Jwt 토큰을 검증을 해서 정상적인 사용자인지 확인
		String jwtToken = jwtHeader.replace(TOKEN_PREFIX, "");
		
		String username = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("username").asString();
		System.out.println("username:" + username);
		//서명이 정상적으로 됨
		if(username != null) {
			System.out.println("jwtToken verified.");
			User userEntity = repository.findByUserId(username);
			
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			
			// Jwt토큰 서명을 통해서 서명이 되었으므로 강제로 Authentication객체를 만들어 인증시킴
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, true, principalDetails.getAuthorities());
		
			// 강제로 시큐리티의 세션에 접근하여 Authentication객체를 저장.
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		}
		
	}

}
