<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberResetPasswordCert.css" />	
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js">
</script>
<script type="text/javascript">
   	(function(){
      	emailjs.init("iiBUjT4gKhsdLuwTY");
  	})();
</script>
   <article class="reset-password-cert">
        <div class="formPopupPassword" id="formPopupPassword">
            <form method="GET" action="<%= request.getContextPath() %>/member/resetPassword" class="formContainer" id="resetPasswordFrm" name="resetPasswordFrm">
                <div class="pop-title-password">
                    <h2>비밀번호 재설정 - 본인 인증</h2>
                </div>
                <div class="pop-password-inner-wrapper">
                    <div class="pop-id-inner">
                        <label for="id" id="id-label"><strong>아이디</strong></label>
                        <input type="text" id="memberId" placeholder="아이디를 입력하세요." name="memberId">
                    </div>
                    <div class="pop-email-inner">
                        <label for="emailId" id="email-label"><strong>email</strong></label>	
                        <input type="email" id="emailId" placeholder="이메일을 입력하세요" name="emailId" >
                        <button type="button" class="btn-cert" onclick="sendCertcodeForId();">인증번호 전송</button>
                    </div>
                    <div class="pop-cert-inner">
                        <label for="certificationCodeId" id="cert-label"><strong>인증코드</strong></label>	
                        <input type="text" id="certificationCodeId" placeholder="인증코드 6자리" name="certificationCodeId" >
                        <button type="button" class="btn-cert" onclick="checkCertCode();">인증코드 확인</button>
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
                    <div class="pop-password-inner-btn" id="cancel-inner">
                        <button type="button" class="btn-cancel" onclick="location.href='<%= request.getContextPath()%>/member/SignInPage'">취소</button>
                    </div>
                </div>
            </form>
        </div>
    </article>
    <script>
    
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

    function sendCertcodeForId() {
        const inputId = document.querySelector("#memberId").value;
        const inputEmail = document.querySelector("#emailId").value;
        const certcode = certificationCode;

        //인증 타이머 시작
        sendAuthTime();  
        
        let templateParams  = {
            name : inputId,
            email :inputEmail,
            message : certcode,
        }
        emailjs.send('service_q105rgm', 'template_rzfxw6f', templateParams).then(function(response){
            alert("이메일을 보냈습니다. 확인해주세요.");
        }, function(error){

        })
    }

    const checkCertCode = () => {
        const inputCode = document.querySelector("#certificationCodeId").value;
        const inputEmail = document.querySelector("#emailId").value;
        const inputId = document.querySelector("#memberId").value;

        const certcode = certificationCode;

        if(inputCode && certcode === inputCode){
        	$.ajax({
				url : "<%= request.getContextPath() %>/member/findMemberByEmail",
				method : "GET",
				data : {
					memberEmail : inputEmail
				},
				dataType: "text",
				success(resp) {
					const memberId = resp;
					if(memberId && memberId != "" && memberId === inputId){
						alert("인증번호가 일치합니다.");
						resetPasswordFrm.submit();
					}
					else{
						alert("일치하는 정보가 없습니다.");
						location.href= "<%= request.getContextPath()%>/member/resetPasswordCert";
					}
				},
				error : console.log
			});
           }
        else{
            alert("인증코드가 일치하지 않습니다. 다시 시도해 주세요.");
        }
    }
    
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
    				location.href="<%= request.getContextPath() %>/member/SignInPage";
    			} 
    		}, 1000);
    	}
    
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>