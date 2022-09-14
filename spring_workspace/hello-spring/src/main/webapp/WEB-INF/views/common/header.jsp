<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>${param.title}</title>
	
	<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
	<!-- bootstrap js: jquery load 이후에 작성할것.-->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
	
	<!-- bootstrap css -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
	
	<!-- 사용자작성 css -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
	<script>
	<c:if test="${not empty msg}">
		alert('${msg}');
	</c:if>
	</script>
	
	<sec:authorize access="isAuthenticated()">
		<script>
		const memberId = '<sec:authentication property="principal.username"/>';
		</script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.6.1/sockjs.min.js" integrity="sha512-1QvjE7BtotQjkq8PxLeF6P46gEpBRXuskzIVgjFpekzFVF4yjRgrQvTG1MTOJ3yQgvTteKAcO7DSZI92+u/yZw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js" integrity="sha512-iKDtgDyTHjAitUDdLljGhenhPwrbBfqTKWO1mkhSFH3A7blITC9MhYon6SjnMhp4o0rADGw9yAC6EW4t5a4K3g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script src="${pageContext.request.contextPath}/resources/js/ws.js"></script>
	</sec:authorize>
</head>
<body>
<div id="container">
	<header>
		<div id="header-container">
			<h2>${param.title}</h2>
		</div>
		<!-- https://getbootstrap.com/docs/4.0/components/navbar/ -->
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<a class="navbar-brand" href="#">
				<img src="${pageContext.request.contextPath}/resources/images/logo-spring.png" alt="스프링로고" width="50px" />
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
		  	</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav mr-auto">
			    	<li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/board/boardList.do">게시판</a></li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/todo/todoList.do">Todo</a></li>
                    <!-- 데모메뉴 DropDown -->
                    <!--https://getbootstrap.com/docs/4.1/components/navbar/#supported-content-->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Demo
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/demo/devForm.do">Dev 등록</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/demo/devList.do">Dev 목록</a>
                        </div>
				    </li>
				    
				    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/menu/menu.do">Menu</a></li>
	
				    <sec:authorize access="isAuthenticated() && !hasRole('ADMIN')">
					    <li class="nav-item">
					    	<a class="nav-link" href="${pageContext.request.contextPath}/chat/chat.do">
					    	관리자와 1:1채팅
					    	<span class="badge badge-primary unread-count ${unreadCount == 0 ? 'd-none' : ''}">${unreadCount}</span>
					    	</a>
					    </li>
				    </sec:authorize>
				    
				    <sec:authorize access="hasRole('ADMIN')">
					    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/memberList.do">회원관리</a></li>
					    <li class="nav-item"><a class="nav-link" href="${pageContext.request.contextPath}/admin/chatList.do">채팅관리</a></li>
				    </sec:authorize>
			    </ul>
			    <sec:authorize access="isAuthenticated()">
			    	<%-- 로그인한 경우 --%>
			    	<span>
			    		<a href="${pageContext.request.contextPath}/member/memberDetail.do">
			    			<sec:authentication property="principal.username"/>
			    			<sec:authentication property="authorities"/>
				    	</a>님, 안녕하세요.
				    </span>
			    	&nbsp;&nbsp;
			    	<form action="${pageContext.request.contextPath}/member/memberLogout.do" method="post">
				    	<button 
					    	class="btn btn-outline-success my-2 my-sm-0" type="submit">로그아웃</button>
					    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />			    	
			    	</form>
			    </sec:authorize>
			    <sec:authorize access="isAnonymous()">
			    	<%-- 로그인하지 않은 경우 --%>
			    	<button 
				    	class="btn btn-outline-success my-2 my-sm-0" type="button" 
				    	onclick="location.href='${pageContext.request.contextPath}/member/memberLogin.do';">로그인</button>
	                &nbsp;
	                <button 
	                	class="btn btn-outline-success my-2 my-sm-0" type="button"
	                	onclick="location.href='${pageContext.request.contextPath}/member/memberEnroll.do';">회원가입</button>
			    </sec:authorize>
			</div>
		</nav>
	</header>
	<section id="content">