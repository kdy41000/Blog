package com.blog.devyoung.config.jwt;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.blog.devyoung.config.auth.PrincipalDetails;
import com.blog.devyoung.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter implements JwtProperties {
	
	private final AuthenticationManager authenticationManager;
	
	// login��û�� �ϸ� �α��� �õ��� ���ؼ� ����Ǵ� �Լ�
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws org.springframework.security.core.AuthenticationException {
		System.out.println("JwtAuthenticationFilter: �α��� �õ���");
	
		try {
			// 1.username, password�� �޴´�.(json���·� �Ѿ�� ���� �Ľ���)
			ObjectMapper om = new ObjectMapper();
			User user = om.readValue(request.getInputStream(), User.class);
			System.out.println("user:" + user);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword());
			
			// 2.�������� �α��� �õ��� �غ���. authenticate()�Լ� ȣ��� PrincipalDetailsService�� loadUserByUsername() �Լ��� ����� �� �����̸� Authentication�� ���ϵ�.
			// DB�� �ִ� username�� password�� ��ġ�Ѵ�.
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			System.out.println("authentication:" + authentication);
			// 3.PrincipalDetails�� ���ǿ� ��´�.(���Ѱ����� ���ؼ�, �ʿ������ �����ص� �ȴ�.)
			// authentication ��ü�� session������ ����� => �α����� �Ǿ��ٴ¶�
			PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
			System.out.println("�α��� �Ϸ�� : " + principalDetails.getUser().getUserId());
			// authentication ��ü�� session������ ������ �ؾ��ϰ� �� ����� return���ָ� ��.
			// ������ ������ ���� ������ security�� ��� ���ֱ� ������ ���Ϸ��� �ϴ°���.
			// ���� JWT��ū�� ����ϸ鼭 ������ ���� ������ ����. ���� ó�������� ���ǿ� �־���
			return authentication;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		PrincipalDetails principalDetailis = (PrincipalDetails) authResult.getPrincipal();
		System.out.println("successfulAuthentication ����� : ������ �Ϸ�Ǿ��ٴ� ����." + principalDetailis.getUserId());
		
		// ��ū ����ð� SimpleDateFormat���� ����Ͽ� Ȯ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tokenExpireDate = sdf.format(System.currentTimeMillis()+EXPIRATION_TIME);
		System.out.println("tokenExpireDate: " + tokenExpireDate);
		
		// RSA����� �ƴϰ� Hash��ȣ���
		String jwtToken = JWT.create()
					.withSubject(SUB)
					.withExpiresAt(new Date(System.currentTimeMillis()+EXPIRATION_TIME))  //����ð�: 10��(�����̽� ������ ª�� �����ϴ� ���� ����.)
					.withClaim("id", principalDetailis.getUser().getId())
					.withClaim("username", principalDetailis.getUser().getUserId())
					.sign(Algorithm.HMAC512(SECRET));
		
		response.addHeader(HEADER_STRING, TOKEN_PREFIX+jwtToken);
	}
	
}
