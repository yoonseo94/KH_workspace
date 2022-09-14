<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
	String result =(String) request.getAttribute("result");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/login.css">
</head>
<body>
<!-- 로그인폼 시작 -->
    <div id="header"><img onclick="location.href='<%= request.getContextPath() %>/main'" src="<%=request.getContextPath() %>/resources/images/logo.png" id="logo"/></div>
  <div class="login-wrapper">
     <div class="login_content">
        <form class="loginFrm" name="loginFrm" action="<%=request.getContextPath() %>/login" method="post">
        <div>
          <h3 class="login_title"><label for="memberId">아이디</label></h3>
          <input type="text" id="memberId" name="memberId" placeholder="아이디" />
            
        </div>
        
        <div>
          <h3 class="login_title"><label for="password">비밀번호</label></h3>
          <input type="password" id="password" name="password" placeholder="비밀번호"/>
        </div>

        <div class="login_btn_area">
          <button type="submit" class="login">
            로그인
          </button>
        </div>
      </form>
        <div class="service">
          <ul>
            <li><a href="<%=request.getContextPath() %>/member/join"> 회원가입 </a></li>
            <li><a href="<%=request.getContextPath() %>/member/join">아이디 찾기</a></li>
            <li><a href="<%=request.getContextPath() %>/member/join">비밀번호 찾기</a></li>
          </ul>
        </div>
      
     </div>
  </div>
  </body>
    <script>
    document.loginFrm.onsubmit = ()=>{
      const idValue = document.querySelector("#memberId").value;
      const pwValue = document.querySelector("#password").value;
	  console.log(idValue);
	  console.log(pwValue);
      if(idValue == "" || pwValue == ""){
        alert("아이디와 비밀번호를 입력해주세요");
        return false;
      }
    };
  </script>
</html>