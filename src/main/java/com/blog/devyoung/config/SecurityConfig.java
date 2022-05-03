package com.blog.devyoung.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.filter.CorsFilter;

import com.blog.devyoung.config.jwt.JwtAuthenticationFilter;
import com.blog.devyoung.config.jwt.JwtAuthorizationFilter;
import com.blog.devyoung.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CorsFilter corsFilter;
	
	@Autowired
	AuthRepository respository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http.csrf().disable();
		  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
		  .addFilter(corsFilter)   // @CrossOrigin(����X), ��ť��Ƽ ���Ϳ� ���(����O)
		  .formLogin().disable()   // form�α��� ���(X)
		  .httpBasic().disable()   // httpBasic����� id,pw�� �״�� ���޵Ǳ� ������, Bearer JWT Web Token�� ����Ͽ� �����ؾ� �����ϹǷ� disable����
		  .addFilter(new JwtAuthenticationFilter(authenticationManager()))  // AuthenticationManager�� �Ķ���ͷ� �����ؾ���
		  .addFilter(new JwtAuthorizationFilter(authenticationManager(), respository))  // AuthenticationManager�� �Ķ���ͷ� �����ؾ���
          .authorizeRequests()
          .antMatchers("/api/v1/user/**").authenticated()
		  .antMatchers("/api/v1/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
		  .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
          .anyRequest().permitAll();  // Preflight Request ������ֱ�
	}
	
}
