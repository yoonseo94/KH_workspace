<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String email = request.getParameter("memberEmail");
	boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일중복검사</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberIdCheck.css" />
</head>
<body>
	<div id="checkEmail-container">
		<h2 id="email-check-title">이메일 중복검사</h2>
		<% if(available) { %>
			<p class="passed">[<span id="usable-email"><%= email %></span>]는 사용가능합니다.</p>
			<p class="passed"><button type="button" id="btn-close" onclick="closePopup();">닫기</button></p>
			<input type="hidden" class="resetVal" value="0" />
		<% } else { %>
			<p class="failed">[<span id="duplicated-email"><%= email %></span>]</p>
			<p class="failed"> 이미 사용중인 이메일입니다.</p>
			<input type="hidden" class="resetVal" value="1" />
			<form name="checkEmailDuplicateFrm" id="checkEmailDuplicateFrm" action="<%= request.getContextPath() %>/member/EmailCheck">
				<input type="text" name="memberEmail" id="memberEmail" palceholder="아이디 입력(6~16자 영문)" />
				<input type="submit" id="btn-id-check" value="중복검사" onclick= "resetIdValid();"/>
			</form>
		<% } %>
	</div>
	<script>
	 // 현재창 닫기
		const closePopup = () => {
			opener.document.querySelector("#memberEmail").value = '<%= email %>';
			opener.document.querySelector("#emailValid").value = 1;
			opener.document.querySelector("#memberPhone").focus();
			self.opener = self;
			window.close();
		};
		
		document.checkEmailDuplicateFrm.onsubmit = () => {
			const useridVal = document.querySelector("#memberId").value;
			if(!useridVal) {
				alert("아이디가 입력되지 않았습니다.");
			      return false;
			}
		    if(!/^[a-zA-Z0-9]{6,16}$/.test(useridVal)){
		        alert('규칙에 맞게 아이디를 6-16자 사이의 숫자를 포함하는 영문자로 만들어 주세요.');
		        return false;
		    }
		}
		// 중복아이디 통과 후, 중복되는 아이디로 다시 조회 시 데이터 덮어쓰기 방지용
		window.onload = () => {
			const resetVal = document.querySelector(".resetVal").value;
			if(resetVal == 1){
				opener.document.querySelector("#memberEmail").value = '';
				opener.document.querySelector("#emailValid").value = 0;
			}		  
		}
	</script>
</body>
</html>