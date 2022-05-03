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
		  .addFilter(corsFilter)   // @CrossOrigin(인증X), 시큐리티 필터에 등록(인증O)
		  .formLogin().disable()   // form로그인 사용(X)
		  .httpBasic().disable()   // httpBasic방식은 id,pw가 그대로 전달되기 때문에, Bearer JWT Web Token을 사용하여 구현해야 안전하므로 disable설정
		  .addFilter(new JwtAuthenticationFilter(authenticationManager()))  // AuthenticationManager를 파라미터로 전달해야함
		  .addFilter(new JwtAuthorizationFilter(authenticationManager(), respository))  // AuthenticationManager를 파라미터로 전달해야함
          .authorizeRequests()
          .antMatchers("/api/v1/user/**").authenticated()
		  .antMatchers("/api/v1/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
		  .antMatchers("/api/v1/admin/**").access("hasRole('ROLE_ADMIN')")
          .anyRequest().permitAll();  // Preflight Request 허용해주기
	}
	
}
