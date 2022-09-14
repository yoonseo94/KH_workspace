package com.meshop.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.meshop.member.entity.Member;
import com.meshop.member.entity.MemberRole;
@WebFilter(
    "/product/productEnroll"
)
public class ProductFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;
        
        //로그인 여부 검사 - loginMember에 담아둔 Member 객체 가져오기
        HttpSession session = httpReq.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        
        //로그인하지 않거나, 일반 사용자가 단순히 url를 요청하게 되면 리다이렉트
        if(loginMember == null){
            String msg = "로그인 후 이용가능합니다.";
            session.setAttribute("msg",msg);

            // login 으로 리다이렉트
            httpRes.sendRedirect(httpReq.getContextPath() + "/login");
            return; //조기 리턴
        }
        chain.doFilter(request, response);
	}
}
