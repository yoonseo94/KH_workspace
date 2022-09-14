<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String nickname = request.getParameter("memberNickname");
	boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>닉네임중복검사</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberIdCheck.css" />
</head>
<body>
	<div id="check-nickname-container">
		<h2 id="nickname-check-title">닉네임 중복검사</h2>
		<% if(available) { %>
			<p class="passed">[<span id="usable-nickname"><%= nickname %></span>]는 사용가능합니다.</p>
			<p class="passed"><button type="button" id="btn-close" onclick="closePopup();">닫기</button></p>
			<input type="hidden" class="resetVal" value="0" />
		<% } else { %>
			<p class="failed">[<span id="duplicated-nickname"><%= nickname %></span>]</p>
			<p class="failed"> 이미 사용중인 닉네임입니다.</p>
			<input type="hidden" class="resetVal" value="1" />
			<form name="checkNickDuplicateFrm" id="checkNickDuplicateFrm" action="<%= request.getContextPath() %>/member/nickCheck">
				<input type="text" name="memberNickname" id="memberNickname" palceholder="새로운 닉네임을 입력하세요" />
				<input type="submit" id="btn-nick-check" value="중복검사" onclick= "resetValid();"/>
			</form>
		<% } %>
	</div>
	<script>
	 // 현재창 닫기
		const closePopup = () => {
			opener.document.querySelector("#memberNickname").value = '<%= nickname %>';
			opener.document.querySelector("#memberEmail").focus();
			self.opener = self;
			window.close();
		};
		document.checkNickDuplicateFrm.onsubmit = () => {
			const userNickVal = document.querySelector("#memberNickname").value;
			if(!userNickVal) {
				alert("닉네임이 입력되지 않았습니다.");
			      return false;
			}
		}
		
		// 중복닉네임 통과 후, 중복되는 닉네임으로 다시 조회 시 데이터 덮어쓰기 방지용
		window.onload = () => {
			const resetVal = document.querySelector(".resetVal").value;
			if(resetVal == 1){
				opener.document.querySelector("#memberNickname").value = '';
				opener.document.querySelector("#nickValid").value = 0;
			}		  
		}
	</script>
</body>
</html>
