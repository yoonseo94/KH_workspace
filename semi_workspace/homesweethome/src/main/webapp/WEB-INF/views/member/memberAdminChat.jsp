<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <link rel="stylesheet" href="<%=request.getContextPath() %>/css/signin.css" />  보류 -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
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
else {
}
 %>
</script>
					<article class="signin">
						<br><br><br>
						<div class="signinTtitle">
							<h3>로그인</h3>
							<br><br><br>
								<form id="loginFrm" name="loginFrm" method="POST" class="form-area" action="<%= request.getContextPath() %>/member/signin">
									<div class="input-wrapper">
										<div class="input-container" id="id-container">
											<input id="input-user-id" type="text" name="input-user-id" placeholder="아이디" required>
										</div>
									</div>
									<div class="input-wrapper">
										<div class="input-container" id="pw-container">
											<input id="input-password" type="password"  name="input-password"  placeholder="비밀번호" required>
										</div>
									</div>
									<div class="btn-wrapper">
										<button id="btn-login">로그인</button>
									</div>
								</form>
								<div class="row">
									<div class="openBtn">
										<button type="button" class="openButton" onclick="location.href='<%= request.getContextPath() %>/member/findId'">아이디 찾기</button>
										<button type="button" class="openButton" onclick="location.href='<%= request.getContextPath() %>/member/resetPasswordCert'">비밀번호 찾기</button>
									</div>
								</div>
								<br>
								<div class="social-login">
									<p>SNS계정으로 간편 로그인/회원가입</p>	
									<div class="social-site">
										<div id="GgCustomLogin"><a href="javascript:void(0)"><img id="google-icon" src="<%=request.getContextPath() %>/images/google.png" alt="카카오 아이콘" /></div>
										<div id="kakaoLogin""><img id="kakao-icon" src="<%=request.getContextPath() %>/images/kakaotalk.png" alt="구글 아이콘" /></div>
									</div>								
								</div>
								<hr />
								<div class="non-member-order-view">
									<div id="btn-order-finder-wrapper">
										<div class="btn-wrapper">
											<button type="button" id="btn-non-member-order" onclick="showOrderFinder()">비회원 주문 조회하기</button>
										</div>
										<input type="hidden" id="btnValid" value="0" />
									</div>
									<div id="order-finder-wrapper">
										<form name="non-member-order-finderFrm" id="non-member-order-finderFrm" action="<%=request.getContextPath()%>/member/nonMemberOrderFinder">
											<div class="input-wrapper">
												<div class="input-container">
													<input id="input-order-no" type="text" name="orderno" placeholder="주문번호">
												</div>
											</div>
											<div class="input-wrapper">
												<div class="input-container">
													<input id="input-email" name="email" type="email" placeholder="이메일">
												</div>
											</div>
											<div class="btn-wrapper">
												<button id="btn-non-member-finder">주문조회</button>	
											</div>
										</form>
									</div>
								</div>
							</div>
						</article>
						
<script>
//처음 실행하는 함수
function init() {
	gapi.load('auth2', function() {
		gapi.auth2.init();
		options = new gapi.auth2.SigninOptionsBuilder();
		options.setPrompt('select_account');
        // 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
		options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
        // 인스턴스의 함수 호출 - element에 로그인 기능 추가
        // GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
		gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
	})
}

function onSignIn(googleUser) {
	var access_token = googleUser.getAuthResponse().access_token
	$.ajax({
		url: 'https://people.googleapis.com/v1/people/me'
		, data: {personFields:'birthdays', key:'AIzaSyDZ6JSp-pdG9nuMfkAgUOK61-RdQ9d2bzo', 'access_token': access_token}
		, method:'GET'
	})
	.done(function(e){
        //프로필을 가져온다.
		var profile = googleUser.getBasicProfile();
		console.log(profile)
	})
	.fail(function(e){
		console.log(e);
	})
}
function onSignInFailure(t){		
	console.log(t);
}
</script>					
<script>
<% if(loginMember == null) { %>
document.loginFrm.onsubmit = (e) => {
	const memberIdVal = document.querySelector("#input-user-id").value;
	const passwordVal = document.querySelector("#input-password").value;
	
    if(!memberIdVal) {
	      alert("아이디를 작성해주세요.");
	      return false;
	    }
    
    if(!passwordVal) {
	      alert("패스워드를 작성해주세요.");
	      return false;
	    }
    //1.아이디검사
    //아이디의 길이는(6~16자 영문, 숫자포함)
    if(!/^[a-zA-Z0-9]{6,16}$/.test(memberIdVal)){
        alert('규칙에 맞게 아이디를 6-16자 사이의 숫자를 포함하는 영문자를 입력하세요.');
        return false;
    }

    //2.비밀번호 확인 검사 
    //숫자/문자/특수문자 포함 형태의 8~16자리 이내의 암호 정규식 
    if(!/(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-z0-9!@#$%&*]{8,16}/i.test(passwordVal)){
        alert('규칙에 맞게 비밀번호를 8-16자 사이의 영문, 숫자, 특수문자를 포함시켜 입력해주세요.');
        return false;
    }
};	
<% } %>


showOrderFinder = () => {
	let changeVal = document.querySelector("#btnValid").value;
	if(changeVal == 0){
		document.querySelector("#order-finder-wrapper").style.display = "block";
		document.querySelector("#btnValid").value = 1;
	}
	else{
		document.querySelector("#order-finder-wrapper").style.display = "none";
		document.querySelector("#btnValid").value = 0;
	}
}
</script>
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>