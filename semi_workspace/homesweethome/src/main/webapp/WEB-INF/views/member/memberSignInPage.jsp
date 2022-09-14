<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/signin.css" />	
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>

<script>


<% if(loginMember == null){ %>

Kakao.init('e8297c1ed4b33061177ef12c15580963'); 

//카카오로그인
function loginWithKakao() {
    Kakao.API.request({
	    url: '/v1/user/access_token_info',
	    success: function (response) {
	    	  const accessToken = response.access_token;
	    	  const aTokenExpiresTime = response.expires_in;
	    	  const refreshToken = response.refresh_token_expires_in;
	    	  const refTokenExpiresTime = response.refresh_token_expires_in;

	    	//액세스 토큰 확인 완료되어, 정보 가져오기
	    	  Kakao.API.request({
	              url: '/v2/user/me',
	              success: function (response) {
	            	  console.log(response);
	            	  const kakaoAccount = response.kakao_account;
	            	  const socialType = "kakao";
	            	  const memberId = socialType + response.id;
	            	  const gender = kakaoAccount.gender;
	            	  const email = kakaoAccount.email;
	            	
	            	// 아이디 찾기   	  
	         	  $.ajax({
	            		  url : "<%= request.getContextPath() %>/member/memberIdCheckSocial",
	            		  method: "GET",
	            		  data : {"memberId" : memberId },
	            		  dataType : "text",
	            		  success : function(res){
	            		  // 로그인
	            			  if(res == "T"){
	            				  createHiddenSignForm(memberId);
	            			  }
	            			  else { 
	            		  // 사이트 연결은 하였으나 회원가입이 완료되지 않은 경우로 추가 정보 입력을 위한 회원가입 진행 
	            					 SubmitSignUpSocialForm(memberId, email, gender, socialType);
	            			   } 
	            		  },
	            		  error : console.log
	            	  });
	              },
	              fail: function (error) {
	                console.log(error)
	              },
	            })
	    },
	    // 액세스 토큰 확인이 안 되는 경우(비회원/액세스토큰 만료)
	    fail: function(error){
	        // 로그인
	    	  Kakao.Auth.login({
	    	      success: function (response) {
	    	    	  console.log(response);
	    	    	  const accessToken = response.access_token;
	    	    	  const aTokenExpiresTime = response.expires_in;
	    	    	  const refreshToken = response.refresh_token_expires_in;
	    	    	  const refTokenExpiresTime = response.refresh_token_expires_in;
	    	    	  Kakao.Auth.setAccessToken(accessToken); 
	    	    	 
	    		    	//액세스 토큰 확인 완료되어, 정보 가져오기
	    	    	  Kakao.API.request({
	    	              url: '/v2/user/me',
	    	              success: function (response) {
	    	            	  console.log(response);
	    	            	  const kakaoAccount = response.kakao_account;
	    	            	  const socialType = "kakao";
	    	            	  const memberId = socialType + response.id;
	    	            	  const gender = kakaoAccount.gender;
	    	            	  const email = kakaoAccount.email;
	    	            	
	    	            	// 아이디 찾기   	  
	    	         	  $.ajax({
	    	            		  url : "<%= request.getContextPath() %>/member/memberIdCheckSocial",
	    	            		  method: "GET",
	    	            		  data : {"memberId" : memberId },
	    	            		  dataType : "text",
	    	            		  success : function(res){
	    	            		  // 로그인
	    	            			  if(res == "T"){
	    	            				  createHiddenSignForm(memberId);
	    	            			  }
	    	            			  else { 
	    	            		  // 사이트 연결은 하였으나 회원가입이 완료되지 않은 경우로 추가 정보 입력을 위한 회원가입 진행 
	    	            					 SubmitSignUpSocialForm(memberId, email, gender, socialType);
	    	            			   } 
	    	            		  },
	    	            		  error : console.log
	    	            	  });
	    	              },
	    	              fail: function (error) {
	    	                console.log(error)
	    	              },
	    	            })
	    	     },
	    	      fail: function (error) {
	    	        console.log(error)
	    	      },
	    	    }) 
	    },
	});
 
  }


 
function SubmitSignUpSocialForm(memberId, email, gender, socialType){
	const frm = document.socialSignUpFrm;
	frm.id.value = memberId;
	frm.email.value = email;
	frm.gender.value = gender;
	frm.socialType.value = socialType;
	frm.submit(); 
}

function createHiddenSignForm(memberId){
	
	const frm = document.createElement('form');
	frm.setAttribute('method', 'POST');
	frm.setAttribute('action', '<%= request.getContextPath() %>/member/memberSocialSignIn');
	const hiddenInput = document.createElement('input');
	hiddenInput.setAttribute('type','hidden');
	hiddenInput.setAttribute('name', "memberId");
	hiddenInput.setAttribute('value', memberId);
	frm.appendChild(hiddenInput);
	document.body.appendChild(frm);
	frm.submit();
}

<% } %>
</script>
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
								<div class="social-login">
									<p>SNS계정으로 간편 로그인/회원가입</p>	
									<div class="social-site">
										<div id="kakaoLogin"><a id="custom-login-btn" href="javascript:loginWithKakao()"><img id="kakao-icon" src="<%=request.getContextPath() %>/images/kakao_login_small.png" alt="카카오 로그인 버튼"  /></a></div>
										<div id="naverIdLogin"></div>
										<div id="buttonDiv" class="google-btn-icon"></div>
										
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
						<form action="<%= request.getContextPath() %>/member/memberSignUpSocial" method="GET" name="socialSignUpFrm">
							<input type="hidden" name="id" />
							<input type="hidden" name="email" />
							<input type="hidden" name="gender" />
							<input type="hidden" name="socialType" />
						</form>
<script>
<% if(loginMember == null) { %>
function parseJwt (token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
};

function handleCredentialResponse(response) {
	 const responsePayload = parseJwt(response.credential);
      const socialType = "google";
 	  const memberId = socialType + responsePayload.sub;
 	  const gender = responsePayload.gender;
 	  const email = responsePayload.email;
 	  
 	 $.ajax({
 		  url : "<%= request.getContextPath() %>/member/memberIdCheckSocial",
 		  method: "GET",
 		  data : {"memberId" : memberId },
 		  dataType : "text",
 		  success : function(res){
 			  if(res == "T"){
 				  createHiddenSignForm(memberId);
 			  }
 			  else { 
 				  // 회원가입 
 					 SubmitSignUpSocialForm(memberId, email, gender, socialType);
 			   } 
 		  },
 		  error : console.log
 	  });
  }
  
  
  window.onload = function() {
    google.accounts.id.initialize({
      client_id: "876270899044-a3f5488k6dks11e8h33suurud5ov5am7.apps.googleusercontent.com",
      callback: handleCredentialResponse
    });
    
    google.accounts.id.renderButton(
      document.getElementById("buttonDiv"),
      { theme: "outline", size: "large" } 
    );
  }
  
  
//네이버 로그인
  const naverLogin = new naver.LoginWithNaverId({
 				clientId: "pEzgO6fzteyhuwakBpZd",
  				callbackUrl: "http://localhost:9090/homesweethome/member/SignInPage",
  				loginButton: {color: "green", type: 1, height: 45}
  			} );
  	naverLogin.init(); 


	

//네이버 로그인
   naverLogin.getLoginStatus(function (status) {
       if (status) {
      	 const socialType = "naver";
      	 const memberId = socialType + naverLogin.user.getId();
         const gender=naverLogin.user.getGender();
         const email=naverLogin.user.getEmail();
         
         $.ajax({
    		  url : "<%= request.getContextPath() %>/member/memberIdCheckSocial",
    		  method: "GET",
    		  data : {"memberId" : memberId },
    		  dataType : "text",
    		  success : function(res){
    			  if(res == "T"){		
    				  createHiddenSignForm(memberId);
    			  }
    			  else {	  // 회원가입 
    					 SubmitSignUpSocialForm(memberId, email, gender, socialType);
    			   } 
    		  },
    		  error : console.log
    	  });
          } 
     }); 
   


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
    if(!/^[a-zA-Z0-9]{5,16}$/.test(memberIdVal)){
        alert('규칙에 맞게 아이디를 5-16자 사이의 숫자를 포함하는 영문자를 입력하세요.');
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
<%@ include file="/WEB-INF/views/common/footer.jsp" %>