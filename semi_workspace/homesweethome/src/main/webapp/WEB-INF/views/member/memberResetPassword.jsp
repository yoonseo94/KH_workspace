<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberResetPassword.css" />	
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<% String memberId = request.getParameter("memberId"); %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
<script type="text/javascript">
   	(function(){
      	emailjs.init("iiBUjT4gKhsdLuwTY");
  	})();
</script>
   <article class="reset-password">
        <div class="reset-password">
        <div class="formPopupPassword" id="popupFormPassword">
            <form method="POST" action="<%= request.getContextPath() %>/member/resetPassword" class="formContainer" id="resetPasswordFrm" name="resetPasswordFrm">
                <div class="pop-title-password">
                    <h2>비밀번호 재설정</h2>
                </div>
                <div class="pop-password-inner-wrapper">
                	<div class="pop-id-inner">
                        <label for="memberId" id="id-label"><strong>아이디</strong></label>
                        <input type="text" id="memberId" name="memberId" value="<%= memberId %>" readonly>
                    </div>
                    <div class="pop-password-inner">
                        <label for="password" id="password-label"><strong>비밀번호</strong></label>
                        <input type="password" id="input-password" placeholder="비밀번호를 입력하세요." name="password">
                    </div>
                    <div class="pop-password-check-inner">
                        <label for="passwordCheck" id="password-check-label"><strong>비밀번호 확인</strong></label>	
                        <input type="password" id="input-passwordCheck" placeholder="비밀번호 확인" name="passwordCheck">
                    </div>
                    <div class="pop-password-inner-btn" id="btn-inner">
                        <button class="btn-accept">확인</button>
                        <button type="button" class="btn-cancel" onclick="location.href='<%= request.getContextPath() %>/'">취소</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    </article>
    <script>
    document.resetPasswordFrm.onsubmit = () => {
    const passwordVal = document.querySelector("#password").value;
    const passwordChVal = document.querySelector("#passwordCheck").value;

      if(!passwordVal) {
        alert("비밀번호를 작성해주세요.");
        return false;
      }
      if(!passwordChVal) {
        alert("비밀번호 확인을 작성해주세요.");
        return false;
      }

      // a(?=b)    a이후 b가 나오는 것 매칭. b가 뒤따르는 a를 조회(b는 조회만 하고 최종매칭되지 않는다.)
      //2.비밀번호 확인 검사 
      //숫자/문자/특수문자 포함 형태의 8~16자리 이내의 암호 정규식 
      if(!/(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%&*])[a-z0-9!@#$%&*]{8,16}/i.test(passwordVal)){
          alert('규칙에 맞게 비밀번호를 8-16자 사이의 영문, 숫자, 특수문자를 포함시켜 만들어 주세요.');
          return false;
      }
  
      //비밀번호일치여부 검사
      if(!(passwordVal === passwordChVal)){
          alert('비밀번호가 비밀번호 확인에 입력된 값과 일치하지 않습니다. 다시 입력해주세요.');
          return false;
      }
    } 
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>