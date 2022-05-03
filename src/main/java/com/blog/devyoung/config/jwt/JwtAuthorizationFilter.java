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
		
		System.out.println("�����̳� ������ �ʿ��� �ּ� ��û�� ��.");
		
		String jwtHeader = request.getHeader("Authorization");
		System.out.println("jwtHeader: " + jwtHeader);
		
		// header�� �ִ��� Ȯ��, "Bearer " �� �����ϴ��� Ȯ�� 
		if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
			chain.doFilter(request, response);
			return;
		}
		
		// Jwt ��ū�� ������ �ؼ� �������� ��������� Ȯ��
		String jwtToken = jwtHeader.replace(TOKEN_PREFIX, "");
		
		String username = JWT.require(Algorithm.HMAC512(SECRET)).build().verify(jwtToken).getClaim("username").asString();
		System.out.println("username:" + username);
		//������ ���������� ��
		if(username != null) {
			System.out.println("jwtToken verified.");
			User userEntity = repository.findByUserId(username);
			
			PrincipalDetails principalDetails = new PrincipalDetails(userEntity);
			
			// Jwt��ū ������ ���ؼ� ������ �Ǿ����Ƿ� ������ Authentication��ü�� ����� ������Ŵ
			Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, true, principalDetails.getAuthorities());
		
			// ������ ��ť��Ƽ�� ���ǿ� �����Ͽ� Authentication��ü�� ����.
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			chain.doFilter(request, response);
		}
		
	}

}
