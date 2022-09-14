<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
<script type="text/javascript">
        (function(){
            emailjs.init("wJEGCJrW2zJ9HKeEh");
        })();
</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customerservice/cscentersendemail.css" />
<%
	String selectType = request.getParameter("selectType");
%>
<article class="email-send-container">
	<div>
		<p id="email-send-title">이메일 문의하기</p>
		<p id="email-send-text">상품/배송 등 쇼핑 관련 문의는 <a id="connect-cscenter" href="<%= request.getContextPath() %>/customerservice/cscenter">고객센터</a>에서 요청해주세요.</p>
	</div>
	<div>
		<form action="<%= request.getContextPath() %>/customerservice/sendtous" method="POST" name="emailSendFrm" id="emailSendFrm" enctype="multipart/form-data" >
			<div class= "select-type-cont">
				<div>
					<div>  
						<select name="selectType" id="selectType" required>
							<option selected value disabled>문의 유형</option>
							<option value="0" <%="0".equals(selectType) ? "selected" : ""%>>회원정보 문의</option>
							<option value="1" <%="1".equals(selectType) ? "selected" : ""%>>쿠폰/포인트 문의</option>
							<option value="2" <%="2".equals(selectType) ? "selected" : ""%>>주문/결제 관련 문의</option>
							<option value="3" <%="3".equals(selectType) ? "selected" : ""%>>취소/환불 관련 문의</option>
							<option value="4" <%="4".equals(selectType) ? "selected" : ""%>>배송 관련 문의</option>
							<option value="5" <%="5".equals(selectType) ? "selected" : ""%>>주문 전 상품 정보 문의</option>
							<option value="6" <%="6".equals(selectType) ? "selected" : ""%>>서비스 개선 제안</option>
							<option value="7" <%="7".equals(selectType) ? "selected" : ""%>>시스템 오류 제보</option>
							<option value="8" <%="8".equals(selectType) ? "selected" : ""%>>불편 신고</option>
							<option value="9" <%="9".equals(selectType) ? "selected" : ""%>>기타 문의</option>
						</select>
					</div>
					<span class="select-notice">앱 개선 제안은 '서비스 개선 제안'으로 선택해 주세요.</span>
					<span class="select-notice">앱 장애 신고는 '시스템 오류 제보'로 선택해 주세요.</span>
				</div>
			</div>
			<% if(loginMember != null) { %>
			<div class="input-cont" >
					<input type="text" id="name" name="name" class="input-val" placeholder="이름" value="<%= loginMember.getMemberName() %>" required style="margin-left : 0.5rem;"/>
				<div class= input-wrapper>
				</div>
			</div>
			<div class="input-cont" >
				<div class= input-wrapper>
					<input type="text" id="email" name="email"  class="input-val" placeholder="이메일" value="<%= loginMember.getEmail() %>" required/>
				</div>
			</div>
			<% } else { %>
			<div class="input-cont" >
				<div class= input-wrapper>
					<input type="text" id="name" name="name" class="input-val" placeholder="이름" required/>
				</div>
			</div>
			<div class="input-cont" >
				<div class= input-wrapper>
					<input type="text" id="email" name="email"  class="input-val" placeholder="이메일" required/>
				</div>
			</div>
			<% } %>
			<div class= input-wrapper-title>
				<input type="text" id="title" name="title" class="input-val-title" placeholder="제목" required/>
			</div>
			<div class= input-wrapper-content>
				<textarea name="contents" id="contents" class="input-content" cols="30" rows="10" maxlength="500" placeholder="문의내용" required></textarea>
				<span id="counter">0<!--  -->자 / 최대<!--  -->500<!--  -->자</span>
			</div>
			<div>
				<label for="file">
					<span id="file-box"> <!--  -->
						<span id="box-file-text"></span> <!-- 파일명 -->
					</span>
					<span id="file-btn">첨부파일</span>
					<input type="file" name="file" id="file" onchange="changeValue();" hidden />
				</label>
			</div>
			<div id="privacy-cont">
				<div id="check-wrapper">
					<input type="checkbox" class="agree-check" id="privacy-terms-agreement" name="privacy-terms-agreement" />
					<span></span>
				</div>
				<div class="notice-info">
					<label for="privacy-terms-agreement" id="privacy-label">개인정보 수집 및 이용동의</label>
					<p>1. 수집하는 개인정보 항목 : 이름, 이메일 <br />2. 수집 목적 : 문의자 확인, 문의에 대한 회신 등의 처리<br />3. 보유 기간 : <em id="highlight-txt">목적 달성 후 파기</em>, 단, 관계법령에 따라 또는 회사 정책에 따른 정보보유사유가 발생하여 보존할 필요가 있는 경우에는 필요한 기간 동안 해당 정보를 보관합니다. 전자상거래 등에서의 소비자 보호에 관한 법률, 전자금융거래법, 통신비밀보호법 등 법령에서 일정기간 정보의 보관을 규정하는 경우, 이 기간 동안 법령의 규정에 따라 개인정보를 보관하며, 다른 목적으로는 절대 이용하지 않습니다. (개인정보처리방침 참고)<br />4. 귀하는 회사의 정보수집에 대해 동의하지 않거나 거부할 수 있습니다. 다만, 이때 원활한 문의 및 서비스 이용 등이 제한될 수 있습니다.</p>
				</div>
			</div>
			<button type="button" id="submit-btn" onclick="frmSubmit();">제출하기</button>
		</form>
	</div>
</article>
<script>
$(contents).keyup((e) => {
    const length = $(e.target).val().length;
    const lengthText = $(e.target).val().length +"<!--  -->자 / 최대<!--  -->500<!--  -->자";
  $(counter)
  .html(0)
  .html(lengthText);
});

const frmSubmit = () =>{
	sendEmail();
	validateValue();
}

// emailJS API 이용한 이메일 전송
function sendEmail() {
	  const inputName =  document.querySelector("#name").value;
	  const inputEmail = document.querySelector("#email").value;
	  const inputContent = document.querySelector("#contents").value;
	  const inputTitle = document.querySelector("#title").value;
	  const selectType = document.querySelector("#selectType").value;
	  const fileName = document.querySelector("#file").value.split('/').pop().split('\\').pop();
	  
	  let templateParams  = {
	      email : inputEmail,
	      title : inputTitle,
	      selectType : selectType,
	      name : inputName,
	      content : inputContent,
	      file : fileName 
	  };

	  //이메일 전송
	   emailjs.send('service_99n7p22', 'template_roh0s3p', templateParams)
	        .then(function(response){
	         }, function(error){
	    });   
	};
	

const validateValue = () => {
		
		// 유효성 검사
	    const usernameVal = document.querySelector("#name").value;
	    const userTitleVal = document.querySelector("#title").value;
	    const useremailVal = document.querySelector("#email").value;
	    const contentVal = document.querySelector("#contents").value.trim();
		const selectTypeVal = document.querySelector("#selectType").value;
		
	    if(!selectTypeVal) {
	      alert("문의유형을 선택해주세요.");
	      return false;
	    }
	    
	    if(!usernameVal) {
	      alert("이름을 작성해주세요.");
	      return false;
	    }

	    if(!useremailVal) {
	      alert("이메일을 작성해주세요.");
	      return false;
	    }
	    
	    if(!userTitleVal) {
	      alert("제목을 작성해주세요.");
	      return false;
	    }
	    
	    //1. 문의 유형 검사
	    if($("#selectType").val() == null){
			alert("문의 유형을 선택해주세요.");
			return false;
		}
	    
	    //2.이름검사 : 한글2글자 이상만 허용. 
	    // 한글 검사
	    if(!/^[가-힣]{2,}$/.test(usernameVal)){
	        alert('이름에는 2글자 이상의 한글만 사용할 수 있습니다.');
	        return false;
	    }
	    
	    //3.이메일 검사
	    if(!/^[a-z0-9]{4,12}[@].+[.][a-zA-Z]{2,3}$/i.test(useremailVal)){
	        alert('이메일은 @가 포함되어야 하며, 아이디의 길이는 4~12자리이어야 합니다.');
	        return false;
	    }
	    
	    //4. 제목 검사
	    if(!/^[가-힣a-zA-Z0-9!@#$%&*]{1,}$/.test(userTitleVal)){
	        alert('제목에는 2글자 이상 입력해야 합니다..');
	        return false;
	    }
	    
	   
	    // 5. 문의사항 검사
		if(!/^(.|\n)+$/.test(contentVal)){
			alert("문의사항 내용을 작성해주세요.");
			return false;
		}
	    
	    
	    document.emailSendFrm.submit();
	}
	
$("#file-box").click((e) =>{
	e.preventDefault();
	$("#file").click();

})	
$("#file-btn").click((e) =>{
	e.preventDefault();
	$("#file").click();	

})	
	
const changeValue = (e) => {
	let filename = document.querySelector("#file").value.split('/').pop().split('\\').pop();

	document.querySelector("#box-file-text").innerHTML = filename;
}



</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>