<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>비밀번호 찾기</title>
 <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/login.css">
</head>
<body>
<!-- 로그인폼 시작 -->
 <div id="header">
       <img src="<%=request.getContextPath() %>/resources/images/me_shop_logo.png" id="logo"/>
    </div>


 <div class="login-wrapper">
      <div class="login_content">
       <form id="loginFrm" name="loginFrm" method="POST" action="<%= request.getContextPath() %>/member/find/pw">
        <div>
          <h3 class="login_title">
            <label for="id">아이디</label>
          </h3>
            <input type="text"  name="id" id="id" placeholder="아이디"/>
        </div>
     
        <div class="login_btn_area">
          <input type="submit" value="비밀번호 찾기">
        </div>
      </form>
        <div class="service">
          <a href="<%= request.getContextPath() %>/meshop/member/join"><span>회원가입</span></a>
        </div>
      </div>
    </div>
  </body>
</html>




