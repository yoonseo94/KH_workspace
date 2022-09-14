<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/signup.css" />
<script	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="<%= request.getContextPath() %>/js/memberSignUp.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<% 
	String memberId = request.getParameter("id");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	String socialType = request.getParameter("socialType");
%>
<form class="form" id="signupFrm" name="signUpFrm" method="POST">
	<p class="signupTitle">소셜 회원정보 입력</p>
	<div class="signupInfoNecessary">
		<input type="hidden" id="id" name="id" value="<%= memberId %>" >
		<input type="hidden" id="socialType" name="socialType" value="<%= socialType %>" >
		<input type="hidden" id="email" name="email" value="<%= email %>" >
		<% if("undefiend".equals(gender) != true && socialType.equals("naver") != true){
			gender = gender ==    "male" ? "M" : "F"; %>
			<input type="hidden" id="gender" name="gender" value="<%= gender %>"> 	
		<% } %>
		<% if("undefiend".equals(gender) != true && socialType.equals("naver") == true){ %>
			<input type="hidden" id="gender" name="gender" value="<%= gender %>"> 	
		<% } %>
		<p class="input_name">이름<span class="neccesary-star">*</span>
		</p>
		<div class="input_wrapper">
			<div class="input_container" name="name-cont">
				<input type="text" id="user_name" name="user_name" placeholder="이름 입력" tabindex="4" required>
			</div>
		</div>
		<p class="input_nickname">닉네임<span class="neccesary-star">*</span>
		</p>
		<div class="input_wrapper">
			<div>
				<span class="notice-nick-text">다른 회원과 겹치지 않는 별명을 입력해주세요.(2~15자)</span>
			</div>
			<div class="input_container" name="nickname-cont">
				<input type="text" id="user_nickname" name="user_nickname" placeholder="별명 (2~15자)" tabindex="5" required>
			</div>
			<div class="input_container" id="nickname-hidden-msg">
			</div>
		</div>
		<p class="input_phone">휴대폰 번호<span class="neccesary-star">*</span>
		</p>
		<div class="input_wrapper">
			<div class="input_container" name="phone-cont">
				<input type="tel" id="phone" name="phone" placeholder="휴대폰 번호 입력(- 없이)" tabindex="6" required>
			</div>
		</div>

		<p class="input_id">배송지 주소<span class="neccesary-star">*</span>
		</p>
		<div class="row_area">
			<div class="address">
				<div class="input_container_postCode">
					<input type="text" id="postcode" name="postcode" placeholder="우편번호" readonly>
				</div>
			</div>
			<button onclick="searchAddress();" type="button" class="btn_blue">주소 찾기</button>
		</div>
		<div class="row_area">
			<div class="address">
				<div class="input_container">
					<input type="text" id="address" name="address" placeholder="기본주소" readonly>
				</div>
				<div class="input_container">
					<input type="text" id="address_detail" name="address_detail"placeholder="상세주소">
				</div>
				<div class="input_container">
					<input type="text" id="extraAddress" name="extraAddress" placeholder="참고사항" readonly>
				</div>
			</div>
		</div>
	</div>

	<div class="signupInfoOption">
		<div class="signupInfoOptionTitle">
			<p class="signupTitle">선택사항 입력</p>
			<hr>
		</div>

		<p class="input_birth">생년월일</p>
		<div class="row_area_bir">
			<div class="input_container_birth">
				<div id="birth_year">
					<input type="number" id="year" name="year" min="1900" max="" placeholder="년">
				</div>
				<span class="birth-text">년</span>
			</div>
			<div class="input_container_birth">
				<div id="birth_month">
					<select id="month" name="month">
						<option>월</option>
						<option value="01">1</option>
						<option value="02">2</option>
						<option value="03">3</option>
						<option value="04">4</option>
						<option value="05">5</option>
						<option value="06">6</option>
						<option value="07">7</option>
						<option value="08">8</option>
						<option value="09">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
					</select>
				</div>
				<span class="birth-text">월</span>
			</div>
			<div class="input_container_birth">
				<div id="birth_day">
					<input type="number" id="day" name="day" min = "1" max="31" placeholder="일">
				</div>
				<span class="birth-text">일</span>
			</div>
		</div>
		<% if("undefiend".equals(gender) == true){ %>
		<p class="input_gender">성별</p>
		<div class="input_wrapper-gender">
			<div class="input_container-gender">
				<input type="radio" name="gender" value="M" id="gender-male"> 
				<span>
					<label for="gender-male">남</label>
				</span> 
				<input type="radio" name="gender" value="F" id="gender-female"> 
				<span id="gender-f-text">
					<label for="gender-female">여</label>
				</span>
			</div>
		</div>		
	<% } %>
	</div>
	<br />
	<button type="submit" class="btn-signup" onclick="enrollMemberInfo();">회원가입</button>
</form>
<script>

//이름 온블러
user_name.onblur = () => {
	if(!user_name.value){
		document.querySelector(".input_name").style.color= "red";
		document.querySelector("[name=name-cont]").style.borderColor = "red";
		document.querySelector("[name=name-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=name-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=name-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=name-cont]").focus();
		return false;
	}	
	document.querySelector(".input_name").style.color= "black";
	document.querySelector("[name=name-cont]").style.borderColor = "#CACACA";
	return true;
};

// 닉네임 온블러
user_nickname.onblur = () => {
	if(!user_nickname.value){
		document.querySelector(".input_nickname").style.color= "red";
		document.querySelector(".notice-nick-text").style.color= "red";
		document.querySelector("[name=nickname-cont]").style.borderColor = "red";
		document.querySelector("[name=nickname-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=nickname-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=nickname-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=nickname-cont]").focus();
		return false;
	}	
	// 닉네임 중복체크 비동기화
		if(user_nickname.value){
			const nickname = document.querySelector("#user_nickname").value;
			$.ajax({
				url : "<%= request.getContextPath() %>/member/nicknameCheck",
				method : "GET",
				data : {
					memberNickname : nickname
				},
				dataType: "text",
				success(resp) {
					const nicknameVal = document.querySelector("#nickname-hidden-msg");
					nicknameVal.innerHTML = resp;
				},
				error : console.log
			});
		}
	
	document.querySelector(".input_nickname").style.color= "black";
	document.querySelector(".notice-nick-text").style.color= "black";
	document.querySelector("[name=nickname-cont]").style.borderColor = "#CACACA";
	return true;
};

// 닉네임 변경
const changeNick = () => {
	renamedNick = document.querySelector(".renamedNick").innerHTML
	user_nickname.value = renamedNick;
	user_nickname.readOnly = true;
}
// 휴대폰 온블러
phone.onblur = () => {
	if(!phone.value){
		document.querySelector(".input_phone").style.color= "red";
		document.querySelector("[name=phone-cont]").style.borderColor = "red";
		document.querySelector("[name=phone-cont]").style.animationName = "signUpBlurframes";
		document.querySelector("[name=phone-cont]").style.animationDuration = ".2s";
		document.querySelector("[name=phone-cont]").style.animationIterationCount = "3";
		document.querySelector("[name=phone-cont]").focus();
		return false;
	}	
	document.querySelector(".input_phone").style.color= "black";
	document.querySelector("[name=phone-cont]").style.borderColor = "#CACACA";
	return true;
};


/**
 * 회원가입폼 유효성 검사
 */
document.signUpFrm.onsubmit = () => {
	    const usernameVal = document.querySelector("#user_name").value;
	    const usernicknameVal = document.querySelector("#user_nickname").value;
	    const userphoneVal = document.querySelector("#phone").value;
	
	    if(!usernameVal) {
	      alert("이름을 작성해주세요.");
	      return false;
	    }
	    if(!usernicknameVal) {
	      alert("닉네임을 작성해주세요.");
	      return false;
	    }
	    if(!userphoneVal) {
	      alert("전화번호를 작성해주세요.");
	      return false;
	    }
	    
	    //1.이름검사 : 한글2글자 이상만 허용. 
	    // 한글 검사
	    if(!/^[가-힣]{2,}$/.test(usernameVal)){
	        alert('이름에는 2글자 이상의 한글만 사용할 수 있습니다.');
	        return false;
	    }
	    
	    //2. 닉네임 검사 : 한글2글자 이상만 허용. 
	    // 한글 검사
	    if(!/^[가-힣]{2,15}$/.test(usernameVal)){
	        alert('닉네임에는 2글자 이상 15글자 이하의 한글만 사용할 수 있습니다.');
	        return false;
	    }

	    //3. 휴대폰 번호 검사
	    // 01x 시작, 총 10~11자리
	    // 숫자 여부 검사
	    
	    if(!/^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/.test(userphoneVal)){
	        alert('전화번호에는 숫자만 입력해야 합니다.');
	        return false;
	    }

	}
</script>
<style>
@keyframes signUpBlurframes {
      25% { transform: translateX(4px);}
      50% { transform: translateX(-4px);}
      75% {transform: translateX(4px); }
      100% {transform: translateX(-4px);}
}
</style>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>