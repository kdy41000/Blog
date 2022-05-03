package com.blog.devyoung.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);  // 내 서버가 응답을 할 때 json을 자바스크립트에서 처리할 수 있게 할지를 설정하는 것
		config.addAllowedOriginPattern("*");  // 모든 ip에 응답을 허용
		config.addAllowedHeader("*");  // 모든헤더 허용
		config.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization")); // cors경우 해당 헤더만 받음

	    // This allow us to expose the headers(cors경우 해당 헤더만 응답가능하도록 설정)
		config.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
				"Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
		config.addAllowedMethod("*");  //모든 get, post, put, delete, patch 요청 허용
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);	
	}
	
}
