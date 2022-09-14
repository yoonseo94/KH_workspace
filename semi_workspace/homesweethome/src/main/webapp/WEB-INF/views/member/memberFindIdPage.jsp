<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberFindIdPage.css" />	
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/@emailjs/browser@3/dist/email.min.js">
</script>
<script type="text/javascript">
   	(function(){
      	emailjs.init("iiBUjT4gKhsdLuwTY");
  	})();
</script>
   <article class="find-id">
        <div class="formPopupId" id="popupFormId">
            <form class="formContainer" id="formId">
                <div class="pop-title-id">
                    <h2>아이디 찾기</h2>
                </div>
                <div class="pop-id-inner-wrapper">
                    <div class="pop-id-inner">
                        <label for="nameId"><strong>이름</strong></label>
                        <input type="text" id="nameId" placeholder="이름을 입력하세요." name="nameId">
                    </div>
                    <div class="pop-id-inner">
                        <label for="emailId"><strong>email</strong></label>	
                        <input type="email" id="emailId" placeholder="이메일을 입력하세요" name="emailId" >
                        <button type="button" class="btn" onclick="sendCertcodeForId();">인증번호 전송</button>
                    </div>
                    <div class="pop-id-inner">
                        <label for="certificationCodeId"><strong>인증코드</strong></label>	
                        <input type="text" id="certificationCodeId" placeholder="인증코드 6자리" name="certificationCodeId" >
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
                    <div class="pop-id-inner-btn" id="cancel-inner">
                        <button type="button" class="btn-cancel" onclick="location.href='<%= request.getContextPath()%>/member/SignInPage'">확인</button>
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
        const inputName = document.querySelector("#nameId").value;
        const inputEmail = document.querySelector("#emailId").value;
        const certcode = certificationCode;


        let templateParams  = {
            name : inputName,
            email :inputEmail,
            message : certcode,
        }
        
        //인증 타이머 시작
        sendAuthTime();  
        
        emailjs.send('service_q105rgm', 'template_rzfxw6f', templateParams).then(function(response){
            alert("이메일을 보냈습니다. 확인해주세요.");
        }, function(error){

        })
    }

    const checkCertCode = () => {
        const inputCode = document.querySelector("#certificationCodeId").value;
        const inputEmail = document.querySelector("#emailId").value;
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
						console.log(memberId);
					if(memberId && memberId != ""){
						 alert(`본인인증이 완료되었습니다.
회원님의 아이디는 [\${ memberId }]입니다.`);
						 location.href="<%= request.getContextPath() %>/member/SignInPage";
					}
					else{
						alert("일치하는 정보가 없습니다. 신규가입을 해주세요.");
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