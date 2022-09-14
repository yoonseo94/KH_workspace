<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberPointPage.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script>
<% 
String saveId = null; 
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if("saveId".equals(cookie.getName())){
			saveId = cookie.getValue();
		}
	}
}



%>
</script>

</style>
<script>
//a태그 class 활성화
window.onload = () => {
	const val2 = document.querySelector("#val2");
	const val4 = document.querySelector("#val4");
	console.log(val2);
	val2.classList.add('active');
	val4.classList.add('active');
};
</script>

<div class = "all-content">
	<div class="myhome-nav">
		<nav class="page-navigation myhome-nav-top">
			<ul style="transform: translateX(0px);">
				<li class="navigation_btn">
					<a class="" href="<%= request.getContextPath() %>/member/mypage" target="_self" id = "val1">마이페이지</a>
				</li>
				<li class="navigation_btn">
					<a class="" href="<%= request.getContextPath() %>/member/shopPage" target="_self" id = "val2">나의쇼핑</a>
				</li>
			</ul>
		</nav>
		<nav class="page-navigation myhome-nav-bottom">
			<ul style="transform: translateX(0px);">
				<li class="navigation_btn">
					<a class="" href="<%= request.getContextPath() %>/member/shopPage" target="_self" id = "val3">주문배송내역 조회</a>
				</li>
				<li class="navigation_btn">
					<a class="" href="<%= request.getContextPath() %>/member/pointPage" target="_self" id = "val4">포인트</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<div class="point-content">
	<div class="point-container">
		<div class="title-area">
			<h2 class="point-title">사용 가능한 포인트</h2>
				<p class="point-content">0 P</p>
		</div>
		<div class="point-info">
			<p class="point-info-con">30일 이내 소멸 예정 포인트
				<b>0 P</b>
			</p>
		</div>
	</div>
	<div class="presence-absence">
	<% %>
	포인트 내역이 없습니다.
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>