<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberShop.css" />
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
System.out.println("saveId = " + saveId);

String memberId = loginMember.getMemberId();
String memberName = loginMember.getMemberName();
String nickname = loginMember.getNickname();
String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
String phone = loginMember.getPhone();
String gender = loginMember.getGender() != null ? loginMember.getGender() : "";

// 장바구니 정보

%>
</script>

<script>
//a태그 class 활성화
window.onload = () => {
	const val2 = document.querySelector("#val2");
	const val3 = document.querySelector("#val3");
	console.log(val2);
	val2.classList.add('active');
	val3.classList.add('active');
};
</script>
<style>
.order-list-info-wrap-content-value {
    color: #35c5f0;
    font-weight: 700;
    margin-left: 5px;
    display: inline-block;
    min-width: 30px;
}
</style>

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
					<a class="" href="<%= request.getContextPath() %>/member/mypointPage" target="_self" id = "val4">포인트</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<div class="order-list-container">
	<div class="order-list-info">
		<div class="order-list-info-wrap">
			<a class="order-list__info__wrap__content" href="/user_shopping_pages/coupons">
				<svg class="order-list__info__wrap__content__icon--coupon" width="51" height="29" viewBox="0 0 51 29" preserveAspectRatio="xMidYMid meet">
					<g fill="none" fill-rule="evenodd">
						<path d="M46.493 1a3.5 3.5 0 012.48 1.025A3.482 3.482 0 0150 4.497h0v20.006A3.501 3.501 0 0146.493 28h0H4.507a3.5 3.5 0 01-2.48-1.025A3.482 3.482 0 011 24.503h0V4.497A3.501 3.501 0 014.507 1h0z" stroke="#757575" fill="#FFF">
						</path>
						<path fill="#757575" d="M7.167 1.06h1.111v26.32H7.167z">
						</path>
						<path d="M32.722 12.26c-.926-1.73-2.658-2.8-4.706-2.8h0c-2.895 0-5.294 2.382-5.294 5.6 0 2.658 2.4 5.04 5.294 5.04 2.048 0 3.78-1.07 4.706-2.8" stroke="#757575" stroke-width="3" stroke-linecap="round">
						</path>
					</g>
				</svg>
				<div class="order-list__info__wrap__content__text">
					<span>쿠폰</span>
					<span class="order-list-info-wrap-content-value">0</span>
				</div>
			</a>
			<a class="order-list__info__wrap__content" href="<%= request.getContextPath() %>/member/mypointPage">
				<svg class="order-list__info__wrap__content__icon--point" width="40" height="29" viewBox="0 0 40 29" preserveAspectRatio="xMidYMid meet">
					<g transform="translate(-5 .5)" stroke="#757575" fill="none" fill-rule="evenodd">
						<g transform="translate(23.889 6.16)" fill="#FFF">
							<path d="M.5 3.86h20.111v13.585c0 .316-.248.59-.599.852-.5.374-1.234.705-2.14.986-1.873.581-4.46.937-7.316.937-2.857 0-5.444-.356-7.316-.937-.907-.281-1.642-.612-2.14-.986-.351-.263-.6-.536-.6-.852h0V3.86z">
							</path>
							<ellipse cx="10.556" cy="3.36" rx="10.056" ry="2.86">
							</ellipse>
							<path d="M0 12.88c0 1.856 4.602 3.36 10.278 3.36h0c5.676 0 10.278-1.504 10.278-3.36M0 7.84c0 1.856 4.602 3.36 10.278 3.36h0c5.676 0 10.278-1.504 10.278-3.36">
							</path>
						</g>
						<ellipse fill="#FFF" cx="18.889" cy="14" rx="13.389" ry="13.5">
						</ellipse>
						<path d="M13.889 15.68h6.667c1.756.415 3.333-1.182 3.333-3.36 0-1.759-1.572-3.36-3.333-3.36h-3.334v11.2" stroke-linecap="round" stroke-width="3">
						</path>
					</g>
				</svg>
				<div class="order-list__info__wrap__content__text">
					<span>포인트</span>
					<span class="order-list-info-wrap-content-value">0P</span>
				</div>
			</a>
		</div>
	</div>
	<div class="order-list-menu">
		<a class="order-list-menu-list" href="/user_shopping_pages/order_list?status=0" id="wait-deposit">
			<div class="order-list-menu-list-wrap">
				<div class="order-list-menu-list-title">입금대기</div>
				<div class="order-list-menu-list-value">0</div>
			</div>
		</a>
		<a class="order-list-menu-list" href="/user_shopping_pages/order_list?status=1">
			<div class="order-list-menu-list-wrap">
				<div class="order-list-menu-list-title">결제완료</div>
				<div class="order-list-menu-list-value">0</div>
			</div>
		</a>
		<a class="order-list-menu-list" href="/user_shopping_pages/order_list?status=2">
			<div class="order-list-menu-list-wrap">
				<div class="order-list-menu-list-title">배송준비</div>
				<div class="order-list-menu-list-value">0</div>
			</div>
		</a>
		<a class="order-list-menu-list" href="/user_shopping_pages/order_list?status=3">
			<div class="order-list-menu-list-wrap">
				<div class="order-list-menu-list-title">배송중</div>
				<div class="order-list-menu-list-value">0</div>
			</div>
		</a>
		<a class="order-list-menu-list" href="/user_shopping_pages/order_list?status=4">
			<div class="order-list-menu-list-wrap">
				<div class="order-list-menu-list-title">배송완료</div>
				<div class="order-list-menu-list-value">0</div>
			</div>
		</a>
		<a class="order-list-menu-list" href="/user_shopping_pages/order_list?status=5">
			<div class="order-list-menu-list-wrap">
				<div class="order-list-menu-list-title">구매확정</div>
				<div class="order-list-menu-list-value">0</div>
			</div>
		</a>
	</div>
	
	<!-- 비동기처리 배송상태 정보 받아오기 -->
	<div class="order-list-menu">
		
	</div>
	
</div>
<script>
wait-deposit.addEventListener('click', () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/cart/cartInfo",
		method : "GET",
		dataType : "html",
		success(response) {
			
		},
		error : console.log
	});
});

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>