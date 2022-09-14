package com.meshop.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;

@WebFilter("/chat/*")
public class ChatFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        
        //로그인 여부 검사
        HttpSession session = httpReq.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        
        if(loginMember == null) {
            // index로 리다이렉트
            httpRes.sendRedirect(httpReq.getContextPath() + "/login");
            return; //조기 리턴
        }
        chain.doFilter(request, response);
	}

}
