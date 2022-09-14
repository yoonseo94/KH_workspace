<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String memberEmail = request.getParameter("memberEmail");
String memberName = request.getParameter("memberName");
boolean available = (boolean) request.getAttribute("available");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 본인인증</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberEmailCert.css" />
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js"></script>
<script type="text/javascript">
        (function(){
            emailjs.init("iiBUjT4gKhsdLuwTY");
        })();
</script>
<%-- <script src="<%=request.getContextPath() %>/js/certificationemail.js"></script> --%>
</head>
<body>
    <div class="cert-email">
        <div class="formPopupCert" id="popupFormCert" >
            <form class="formContainer" id="formCert" name="emailCertFrm">
                <div class="pop-title-cert">
                    <h2>이메일 본인 인증</h2>
                </div>
                <% if(available) { %>
                <div class="pop-cert-inner-wrapper">
                    <div class="pop-cert-inner">
                        <label for="nameId" id="name-label"><strong>이름</strong></label>
                        <input type="text" id="nameId" name="nameId" value="<%= memberName %>" required readonly>
                    </div>
                    <div class="pop-cert-inner">
                        <label for="emailId" id="email-label"><strong>email</strong></label>	
                        <input type="email" id="emailId" name="emailId" value="<%= memberEmail %>" required readonly>
                        <button type="button" class="btn" id="emailSend" onclick="sendCertcode();">인증코드 전송</button>
                    </div>
                    <div class="pop-cert-inner">
                        <label for="certificationcode" id="certificationcode-label"><strong>인증코드</strong></label>	
                        <input type="text" id="certificationcode" placeholder="인증코드 6자리" name="certificationcode"  >
                        <button type="button" class="btn" onclick="checkCertCode();">인증코드 확인</button>
                    </div>
                    <div class="pop-cert-timer">
	                    <p class="timer-text">* 3분 이내로 인증번호를 입력해주세요.</p>
	                    <div class="btn-inner">
		                    <span>남은 시간 </span>
		                    <span class="timer"></span>
		                    <button type="button" onclick="refreshAuthTime();">시간 초기화(최대 1회)</button>
	                    </div>
	                    <input type="hidden" id="timer-valid" value="0" />
                    </div>
                    <div class="pop-cert-inner-btn" id="cancel-inner">
                        <button type="button" class="btn-cancel" onclick="self.close();">닫기</button>
                    </div>
                </div>
                <% } else { %>
                	<p class="failed">[<span id="duplicated-id"><%= memberEmail %></span>]</p>
					<p class="failed"> 이미 사용중인 이메일입니다.</p>
					<div class="pop-cert-inner-btn" id="cancel-inner-failed">
                        <button type="button" class="btn-cancel" onclick="self.close();">닫기</button>
                    </div>
                <% } %>
            </form>
        </div>
    </div>
    
<script>
// 현재창 닫기
const closePopup = () => {
	opener.document.querySelector("#email").value = '<%= memberEmail %>';
	opener.document.querySelector("#emailValid").value = 1;
	opener.document.querySelector(".input_id").focus();
	opener.document.querySelector("#email").readOnly = true;
	self.close();
};

// 인증코드 생성
const generateRandomCode = () => {
    const characters ='ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz01234567890123456789012345678901234567890123456789';
    let resultCode= '';
    const charLen = characters.length;
    for(let i = 0; i < 6; i++ ){
        resultCode += characters.charAt(Math.floor(Math.random() * charLen));
    }
    return resultCode;
}


const certificationCode = generateRandomCode();

function sendCertcode() {
  const inputName =  document.querySelector("#nameId").value;
  const inputEmail = document.querySelector("#emailId").value;
  const certcode = certificationCode;

  let templateParams  = {
      name : inputName,
      email : inputEmail,
      message : certcode
  };
  
  //인증 타이머 시작
  sendAuthTime();  
  
  //이메일 전송
  emailjs.send('service_q105rgm', 'template_xtqu1tv', templateParams)
        .then(function(response){
        	alert("이메일을 보냈습니다. 확인해주세요.")
         }, function(error){
    }); 
};

function checkCertCode(){
    const inputCode = document.querySelector("#certificationcode").value;
    const certcode = certificationCode;

    if(inputCode && certcode === inputCode){
            // 본인 인증에 통과된 이메일의 값 readonly로 변경
            opener.document.querySelector("#email").readOnly = true;
            // 커서를 주소 필드로 이동한다.
            opener.document.querySelector("#address").focus();
            // 현재 창 닫기
            self.close();
    }
    else{
        alert("인증코드가 일치하지 않습니다. 다시 시도해 주세요.");
    }
    
};

let timer;
let isRunning = false; 
let leftSec;

function sendAuthTime(){    	
	// 남은 시간	
	const display = document.querySelector('.timer');
	leftSec = 180;	
	// 이미 타이머가 작동중이면 중지	
	if (isRunning){	   
		clearInterval(timer);
	}	
	// 타이머를 화면에 출력
	startTimer(leftSec, display);
} 

function refreshAuthTime(){   
	let timerVal = document.querySelector("#timer-valid").value;
	if(timerVal == 0){
	document.querySelector("#timer-valid").value = 1;	
	clearInterval(timer);
	sendAuthTime();		
	}	
} 

function startTimer(count, display) { 
	document.querySelector(".pop-cert-timer").style.visibility= "visible";	
		let minutes;
		let seconds;        
		timer = setInterval(function () {       
			minutes = parseInt(count / 60, 10);       
			seconds = parseInt(count % 60, 10);       
			minutes = minutes < 10 ? "0" + minutes : minutes;       
			seconds = seconds < 10 ? "0" + seconds : seconds;         
			display.textContent = minutes + ":" + seconds;         
			// 타이머 끝       
			if (--count < 0) {	     
				clearInterval(timer);	     
				display.textContent = "";	    
				isRunning = false;
				alert("제한 시간을 초과하였습니다. 다시 시도해주세요.")
				self.close();
			} 
		}, 1000);
	}

</script>
</body>
</html>