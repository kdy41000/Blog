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
		config.setAllowCredentials(true);  // �� ������ ������ �� �� json�� �ڹٽ�ũ��Ʈ���� ó���� �� �ְ� ������ �����ϴ� ��
		config.addAllowedOriginPattern("*");  // ��� ip�� ������ ���
		config.addAllowedHeader("*");  // ������ ���
		config.setAllowedHeaders(Arrays.asList("X-Requested-With","Origin","Content-Type","Accept","Authorization")); // cors��� �ش� ����� ����

	    // This allow us to expose the headers(cors��� �ش� ����� ���䰡���ϵ��� ����)
		config.setExposedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Authorization, x-xsrf-token, Access-Control-Allow-Headers, Origin, Accept, X-Requested-With, " +
				"Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers"));
		config.addAllowedMethod("*");  //��� get, post, put, delete, patch ��û ���
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);	
	}
	
}
