<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<style>
body, html {
    line-height: 1;
    font-family: OhouseSans, "Noto Sans KR", "Apple SD Gothic Neo", "맑은 고딕", "Malgun Gothic", sans-serif;
    -webkit-font-smoothing: antialiased;
    letter-spacing: -0.4px;
    font-size: 13px;
}
.enroll-container{
    display: block;
}
.password-wrap{
    border: #292929;
    border-radius: 8px;
    box-shadow: 0px 0px 1.2px;
	margin-top: 50px;
    width: 50%;
    margin-inline: auto;
}
table{
	margin-left: auto;
    margin-right: auto;
    border-collapse: separate;
    border-spacing: 0 20px;
}
table td{
	width : 70%;
}
table tr{
	width : 30%;
}
.passwrod-title{
	text-align: center;
	font-size: 30px;
}
tr{
	font-size: 20px;
}
input {
	display: block;
    width: 85%;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
    text-align: center;
}
.submit-btn{
    color: #fff;
    background-color: #5bc0de;
    border-color: #46b8da;
    box-shadow: inset 0 1px 0 rgb(255 255 255 / 15%), 0 1px 1px rgb(0 0 0 / 8%);
	display: inline-block;
    padding: 6px 12px;
    margin-bottom: 0;
    font-size: 14px;
    font-weight: 400;
    line-height: 1.42857143;
    text-align: center;
    white-space: nowrap;
    vertical-align: middle;
	touch-action: manipulation;
    cursor: pointer;
	user-select: none;
    background-image: none;
    border: 1px solid transparent;
    border-radius: 4px;
}
</style>

<section id=enroll-container>
	<fieldset class = "password-wrap">
		<h1 class = "passwrod-title">비밀번호 변경</h1>
			<form 
				name="passwordUpdateFrm" 
				action="<%=request.getContextPath()%>/member/passwordUpdate" 
				method="post" >
				<table>
					<div class="table-grop">
						<tr>
							<th>현재 비밀번호</th>
							<td><input type="password" name="oldPassword" id="oldPassword" required placehorder="기존 비밀번호를 입력하세요."></td>
						</tr>
						<tr>
							<th>변경 비밀번호</th>
							<td><input type="password" name="newPassword" id="newPassword" required placehorder="새로운 비밀번호를 입력하세요."></td>
						</tr>
						<tr>
							<th>비밀번호 확인</th>
							<td><input type="password" name="newPasswordCheck" id="newPasswordCheck" required placehorder="비밀번호 확인."><br></td>
						</tr>
						<tr>
							<td colspan="2" style="text-align: center;"><input type="submit" class="submit-btn" value="비밀번호수정" /></td>
						</tr>
					</div>
				</table>
				<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
			</form>
	</fieldset>
</section>
<br />
<br />
<br />
<script>
newPasswordCheck.onblur = () => {
	if(newPassword.value !== newPasswordCheck.value){
		alert("두 비밀번호가 일치하지 않습니다.");
		return false;
	}
	return true;
};
document.passwordUpdateFrm.onsubmit = () => {
	// oldPassword 숫자/문자/특수문자 포함 형태의 8~16자리 이내의 암호 정규식 
	if(!/(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-z0-9!@#$%&*]{8,16}/i.test(oldPassword.value)){
		alert("규칙에 맞게 비밀번호를 8-16자 사이의 영문, 숫자, 특수문자를 포함시켜 만들어 주세요.");
		return false;
	}
	// newPassword 숫자/문자/특수문자 포함 형태의 8~16자리 이내의 암호 정규식 
	if(!/(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-z0-9!@#$%&*]{8,16}/i.test(newPassword.value)){
		alert("규칙에 맞게 비밀번호를 8-16자 사이의 영문, 숫자, 특수문자를 포함시켜 만들어 주세요.");
		return false;
	}
    //비밀번호일치여부 검사
    if(!(newPassword.value === newPasswordCheck.value)){
        alert('비밀번호가 비밀번호 재입력에 입력된 값과 일치하지 않습니다. 다시 입력해주세요.');
        return false;
    }
	if(!newPasswordCheck.onblur()){
		return false; // 폼 제출을 방지
	}
};
</script>
	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>