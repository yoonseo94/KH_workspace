<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mypage.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
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

String memberId = loginMember.getMemberId();
String memberName = loginMember.getMemberName();
String nickname = loginMember.getNickname();
String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
String phone = loginMember.getPhone();
String gender = loginMember.getGender() != null ? loginMember.getGender() : "";
%>
</script>
<!-- 메뉴버튼설정css -->
<style>
.mypage-wrap {
    margin-top: 10px;
    width: 50%;
    margin-inline: auto;
}
.all-content {
	position: relative;
    padding: 0 0 40px;
}
.myhome-nav {
	position: relative;
    margin: 0 auto;
    background-color: #FFF;
    width: 68%;
    margin: 0 auto;
}
div {
	display : black;
}
.myhome-nav-top {
    font-size: 20px;
    overflow: visible;
}
.page-navigation {
    border-bottom: 1px solid #ededed;
    width: 100%;
    overflow: hidden;
}
.page-navigation>ul {
    text-align: center;
    white-space: nowrap;
    list-style: none;
}
.navigation_btn {
    display: inline-block;
}
.navigation_btn>a {
    display: block;
    padding: 0 20px;
    font-weight: 700;
    position: relative;
    height: 10px;
    line-height: 60px;
    transition: color .15s ease;
}
a {
    color: inherit;
    text-decoration: none;
}
.page-navigation>ul {
    text-align: center;
    white-space: nowrap;
}
.navigation_btn>a.active, .navigation_btn>a:not(.active):hover {
    color: #35c5f0;
}
.myhome-nav-bottom{
	font-size:16px;
}
body, html {
    line-height: 1;
    font-family: OhouseSans, "Noto Sans KR", "Apple SD Gothic Neo", "맑은 고딕", "Malgun Gothic", sans-serif;
    -webkit-font-smoothing: antialiased;
    letter-spacing: -0.4px;
    font-size: 13px;
}
</style>
<div class = "all-content">
	<div class="myhome-nav">
		<nav class="page-navigation myhome-nav-top">
			<ul style="transform: translateX(0px);">
				<li class="navigation_btn">
					<a class="" href="<%= request.getContextPath() %>/member/mypage" target="_self" id="val1">마이페이지</a>
				</li>
				<li class="navigation_btn">
					<a class="" href="<%= request.getContextPath() %>/member/shopPage" target="_self" id="val2">나의쇼핑</a>
				</li>
			</ul>
		</nav>
	</div>
</div>
<article class="memberInfo">
	<fieldset id="memberUpdateField" class="mypage-wrap">
		<div class="col-md-6 col-md-offset-3">
			<h2 class="form-signin-heading">
				<b>회원정보수정</b>
			</h2>
			<input class="btn btn-info" type="button" value="비밀번호수정" onclick="location.href='<%=request.getContextPath() %>/member/memberPassword';" />
			<hr>
		</div>
		<div class="col-sm-6 col-md-offset-3">
			<form role="form" name="memberUpdateFrm" method="post"
				action="<%=request.getContextPath() %>/member/memberUpdate">
				<div class="form-group">
					<label for="inputName">아이디</label><input type="text" class="form-control" name="memberId" id="memberId" value="<%=memberId %>" readonly/>
				</div>
				<div class="form-group">
					<label for="InputName">이름</label><input type="text" class="form-control" name="memberName" id="memberName" value="<%=memberName %>" required/>
				</div>
				<div class="form-group">
					<label for="inputNickname">별명</label>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="memberNickname" id="memberNickname" value="<%=nickname %>" required> <input type="hidden" id="nickValid" value="0" />
						<button type="button" class="btn btn-info" onclick="openCheckNick();">중복확인</button>
					</div>
				</div>
				<div class="form-group">
					<label for="InputEmail">이메일</label>
					<div class="input-group mb-3">
						<input type="text" class="form-control" name="memberEmail" id="memberEmail" value="<%=email %>" required> <input type="hidden" id="emailValid" value="0" />
						<button type="button" class="btn btn-info" onclick="openCheckEmail();">중복확인</button>
					</div>
				</div>
				<div class="form-group">
					<label for="InputEmail">전화번호</label><input type="text" class="form-control" name="memberPhone" id="memberPhone" value="<%=phone %>" required/>
				</div>
				<div class="form-group">
					<label for="InputEmail">성별</label>
					<div>
						<label class="radio-inline"> <input type="radio" name="gender" id="gender0" value="M" <%= "M".equals(gender) ? "checked" : "" %> /> 남자 </label> 
						<label class="radio-inline"> <input type="radio" name="gender" id="gender1" value="F" <%= "F".equals(gender) ? "checked" : "" %> /> 여자 </label>
					</div>
				</div>
				<div class="form-group text-right">
					<input type="submit" class="btn btn-info" value="정보수정" /> 
					<input type="button" class="btn btn-default" onclick="deleteMember();" value="탈퇴" />
				</div>
			</form>
		</div>
	</fieldset>
</article>
<form name="nickCheckFrm" id="nickCheckFrm" action="<%= request.getContextPath() %>/member/nickCheck">
	<input type="hidden" name="memberNickname" />
</form>
<form name="EmailCheckFrm" action="<%= request.getContextPath() %>/member/EmailCheck">
	<input type="hidden" name="memberEmail" />
</form>
<form 
	name="memberDelFrm" 
	action="<%= request.getContextPath() %>/member/memberDelete" 
	method="POST">
	<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
</form>
<script>

// a태그 class 활성화
window.onload = () => {
	const val1 = document.querySelector("#val1");
	console.log(val1);
	val1.classList.add('active');
};


// 닉네임 중복검사
const openCheckNick = () => {
	const userNickVal = document.querySelector("#memberNickname").value;
	if(!userNickVal) {
		alert("닉네임이 입력되지 않았습니다.");
	      return false;
    }
	const title = "MemberNickCheckPopup";
	const spec = "width=510px, height=330px";
	const popup = open("", title, spec);
	
	const frm = document.nickCheckFrm;
	console.log("frm = " + frm);
	console.log("frm.target = " + frm.target);
	
	frm.target = title; // 해당팝업에서 폼을 제출!
	frm.memberNickname.value = userNickVal;
	frm.submit();
};

//이메일 중복검사
const openCheckEmail = () => {
	const userEmailVal = document.querySelector("#memberEmail").value;
	if(!userEmailVal) {
		alert("이메일이 입력되지 않았습니다.");
	      return false;
 	}
    if(!/^[a-z0-9]{4,12}[@].+[.][a-zA-Z]{2,3}$/i.test(userEmailVal)){
        alert('이메일은 @가 포함되어야 하며, 아이디의 길이는 4~12자리이어야 합니다.');
        return false;
    }
	const title = "MemberNickCheckPopup";
	const spec = "width=510px, height=330px";
	const popup = open("", title, spec);
	
	const frm = document.EmailCheckFrm;
	console.log("frm = " + frm);
	console.log("frm.target = " + frm.target);
	
	frm.target = title; // 해당팝업에서 폼을 제출!
	frm.memberEmail.value = userEmailVal;
	frm.submit();
};

/**
 * 맴버수정폼 유효성 검사
 */
document.memberUpdateFrm.onsubmit = () => {
	    const usernameVal = document.querySelector("#memberName").value;
	    const usernicknameVal = document.querySelector("#memberNickname").value;
	    const useremailVal = document.querySelector("#memberEmail").value;
	    const userphoneVal = document.querySelector("#memberPhone").value;
	    const usergenderVal = document.querySelector("#gender").value;
	  
	    if(!usernameVal) {
	      alert("이름을 작성해주세요.");
	      return false;
	    }
	    if(!usernicknameVal) {
	      alert("별명을 작성해주세요.");
	      return false;
	    }
	    if(!useremailVal) {
	      alert("이메일을 작성해주세요.");
	      return false;
	    }
	    if(!userphoneVal) {
	      alert("전화번호를 작성해주세요.");
	      return false;
	    }
	    if(!usergenderVal) {
	      alert("전화번호를 작성해주세요.");
	      return false;
	    }

	    //1.이름검사 : 한글2글자 이상만 허용. 
	    // 한글 검사
	    if(!/^[가-힣]{2,}$/.test(usernameVal)){
	        alert('이름에는 2글자 이상의 한글만 사용할 수 있습니다.');
	        return false;
	    }

	    //2. 휴대폰 번호 검사
	    // 01x 시작, 총 10~11자리
	    // 숫자 여부 검사
	    
	    if(!/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test(userphoneVal)){
	        alert('전화번호에는 숫자만 입력해야 합니다.');
	        return false;
	    }

	    //3.이메일 검사
	    if(!/^[a-z0-9]{4,12}[@].+[.][a-zA-Z]{2,3}$/i.test(useremailVal)){
	        alert('이메일은 @가 포함되어야 하며, 아이디의 길이는 4~12자리이어야 합니다.');
	        return false;
	    }
	}

// 탈퇴
const deleteMember = () => {
	if(confirm("정말로 탈퇴하시겠습니까?")){
		document.memberDelFrm.submit();
	}
}

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>