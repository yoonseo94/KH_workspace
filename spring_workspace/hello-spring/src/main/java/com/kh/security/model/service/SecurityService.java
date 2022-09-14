package com.kh.security.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.kh.security.model.dao.SecurityDao;
import com.kh.spring.member.model.dto.Member;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityService implements UserDetailsService {

	@Autowired
	SecurityDao securityDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug("username = {}", username);
		Member member = securityDao.loadUserByUsername(username);
		log.debug("member = {}", member);
		if(member == null)
			throw new UsernameNotFoundException(username); // 로그인페이지로 리다이렉트 /member/memberLogin.do?error
		return member;
	}

}
