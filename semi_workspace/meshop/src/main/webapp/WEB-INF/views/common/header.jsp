<%@ page import="com.meshop.member.entity.Member" %>
<%@ page import="com.meshop.member.entity.MemberRole" %>
<%@page import="java.text.*"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<% 
	DecimalFormat df = new DecimalFormat("###,###");
	Member loginMember = (Member) session.getAttribute("loginMember");
	
	List<Integer> wishList = null;
	if(loginMember != null){
		wishList = loginMember.getWish();
		System.out.println("view" + wishList.toString());
	}
%>
<!DOCTYPE html>
<head>
  <meta charset="UTF-8">
  <title>#MESHOP</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/main.css" />
  
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@48,400,0,0" />
  
  <script src="https://kit.fontawesome.com/69223d03fa.js" crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <div class="header">
        <div class="auth-section">
            <ul class="nav-auth">
            <% if(loginMember == null){ %>
            	<li><a class="auth-menu" href="<%= request.getContextPath() %>/mypage">마이페이지</a></li>
                <li><a class="auth-menu" href="<%= request.getContextPath() %>/login">로그인</a></li>
            	<li><a class="auth-menu" href="<%= request.getContextPath() %>/member/join">회원가입</a></li>
            <%}else{ %>
            	<li><a class="auth-menu" href="<%= request.getContextPath() %>/mypage">마이페이지</a></li>
            	<li><a class="auth-menu" href="<%= request.getContextPath() %>/member/logout">로그아웃</a></li>
            <%} %>
            </ul>
        </div>
        <div class="container">
            <div class="logo">
                <a href="<%= request.getContextPath() %>/main"><img id="logo-img" src="<%= request.getContextPath() %>/images/logo.png" alt="메인 페이지"></a>
            </div>
            <div class="search-section">
                <div class="search-bar">
                    <input type="text" placeholder="검색어를 입력하세요"/>
                    <button><i class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
            <div class="menu">
                <ul class="nav-menu">
                	<li><a class="" href="<%= request.getContextPath() %>/product/productList">상품보기</a></li>
                    <li><a class="" href="<%= request.getContextPath() %>/product/productEnroll">글쓰기</a></li>
                    <li><a class="" href="<%= request.getContextPath() %>/chat">채팅방</a></li>
                    <% if(loginMember != null && loginMember.getMemberRole() == MemberRole.A){ %>
                    <li><a class="" href="<%= request.getContextPath() %>/admin">관리자페이지</a></li>
                    <% }else{ %>
                    <li><a class="" href="<%= request.getContextPath() %>/mystore/myProduct">내 상점</a></li>
                    <% } %>
                </ul>
            </div>
        </div>
    </div>
