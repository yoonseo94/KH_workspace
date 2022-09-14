package com.kh.spring.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;

/**
 * 
 * @Configuration 빈설정이 가능한 클래스
 * @Bean 메소드. 리턴값이 Spring Container가 관리할 bean 객체
 *
 */
@Configuration
public class UserConfig {

	/**
	 * 메소드명이 bean의 id
	 * @return
	 */
	@Bean
	@Scope
	@Lazy
	public UserController userController() {
		return new UserController(userService());
	}
	
	@Bean
	public UserService userService() {
		return new UserService();
	}
}
