<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>마이페이지</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="mypage">
        <div class="menu-section">
            <p>마이 페이지</p>
            <ul class="mypage-menu-list">
                <li><a id="memberBtn">회원 정보</a></li>
                <li><a id="reportBtn">신고 내역</a></li>
                <li><a id="storeBtn">내 상점</a></li>
            </ul>
        </div>
        <div class="content-section">
            <div class="title">
                <span>회원정보</span>
            </div>
            <div class="profile-section">
                <img src="<%=request.getContextPath() %>/resources/images/noone.PNG">
                <p><%=loginMember.getMemberName() %></p>
            </div>
            <form name="memberUpdateFrm" method="post" action="<%=request.getContextPath()%>/member/memberUpdate">
            	<input type=hidden id="memberId" name="memberId" value="<%=loginMember.getMemberId()%>"/>
                <div class="updateFrm">
                    <p>상점 이름</p>
                    <input type="text" id="storeName" name="storeName" value="<%=loginMember.getStoreName()%>"/>
                    <span class="validation" id="storeValid"></span>
                </div>
                <div class="updateFrm">
                    <p>비밀번호</p>
                    <input type="password" id="pswd1" name="password" placeholder="새 비밀번호"/>
                    <span class="validation" id="pswd1Valid"></span>
                </div>
                <div class="updateFrm">
                    <p>비밀번호 확인</p>
                    <input type="password" id="pswd2" placeholder="새 비밀번호 확인"/>
                    <span class="validation" id="pswd2Valid"></span>
                </div>
                <div class="updateFrm region">
                    <p>우리 동네</p>
                    <select name="region" id="region" >
                        <option value="<%=loginMember.getPlace() %>"><%=loginMember.getPlace() %></option>
                        <option value="종로구">종로구</option>
                        <option value="동대문구">동대문구</option>
                        <option value="서대문구">서대문구</option>
                        <option value="용산구">용산구</option>
                        <option value="중구">중구</option>
                    </select>
                </div>
                <div class="updateFrm">
                    <button type="submit">회원정보 수정</button>
                </div>
            </form>
        </div>
    </div>

<script>
    let pw1Check = false;
    let pw2Check = false;
    let storeCheck = false;
    let placeCheck = false;

    window.addEventListener('load',()=>{
    //헤더 높이 구하기
    const header = document.querySelector('.header');

    //메인 컨텐츠의 padding top 높이 조절하기
    const frame = document.querySelector('.mypage');
    frame.style.paddingTop = `\${header.offsetHeight+20}px`;
    });
    
    document.querySelector("#storeName").addEventListener('blur',(e)=>{
      // 상점 이름 validation
      const storeName = e.target.value;
      if(storeName == ""){
        document.querySelector("#storeName").style.borderBottom = "2px solid red";
        document.querySelector("#storeValid").innerHTML = "상점 이름을 입력하세요.";
        storeCheck = false;
        return;
      }
      if(storeName === "<%= loginMember.getStoreName() %>"){
    	  document.querySelector("#storeName").style.borderBottom = "1px solid black";
          document.querySelector("#storeValid").innerHTML = "";
          storeCheck = true;
      }else{
          // 상점 이름 중복불가
          $.ajax({
              url:"<%=request.getContextPath()%>/member/store-check",
              method:"POST",
              data:{
                storeName : storeName
              },
              success(response){
              	console.log(response);
                if(response === "true"){
                   document.querySelector("#storeName").style.borderBottom = "1px solid black";
                   document.querySelector("#storeValid").innerHTML = "";
                   storeCheck = true;
                }else{
                  document.querySelector("#storeName").style.borderBottom = "2px solid red";
                  document.querySelector("#storeValid").innerHTML = "이미 사용중인 상점 이름입니다.";
                  storeCheck = false;
                }
              },
              error:console.log
            });
      }
     });
     document.querySelector("#pswd1").addEventListener('blur',(e)=>{
      //password 1
      const password = e.target.value;

      //비밀번호 정규식 검사
      // 길이검사
      if(!/^.{8,20}$/.test(password)){
        e.target.style.borderBottom = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }
      // 특수문자 포함여부 !&/\*@
      if(!/[!&/\\*@]/.test(password)){
        e.target.style.borderBottom = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }
      // 숫자 포함여부
      if(!/\d/.test(password)){
        e.target.style.borderBottom = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }
      // 영문자 포함여부
      if(!/[a-z]/i.test(password)){
        e.target.style.borderBottom = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }

      e.target.style.borderBottom = "1px solid black";
      document.querySelector("#pswd1Valid").innerHTML = "";
      pw1Check = true;
     });
     document.querySelector("#pswd2").addEventListener('blur',(e)=>{
      //password 2
      const password2 = e.target.value;
      if(pw1Check != true){
        pw2Check = false;
        return;
      }
      const password1 = document.querySelector("#pswd1").value;
      console.log(password1);
      console.log(password2);

      // 두 비밀번호가 같지 않으면
      if(password1 != password2){
        e.target.style.borderBottom = "2px solid red";
        document.querySelector("#pswd1").style.borderBottom = "2px solid red";
        document.querySelector("#pswd2Valid").innerHTML = "입력하신 두 비밀번호가 일치하지 않습니다.";
        pw2Check = false;
        return;
      }
      // 정상 로직
      document.querySelector("#pswd1").style.borderBottom = "1px solid black";
      document.querySelector("#pswd2").style.borderBottom = "1px solid black";
      document.querySelector("#pswd2Valid").innerHTML = "";
      pw2Check = true;

     });
     document.querySelector("#region").addEventListener('change',(e)=>{
      // 동네 validation
      const place = e.target.value;
      console.log(place);

      if(place == ""){
        placeCheck = false;
        return;
      }
      placeCheck = true;
     });
     document.memberUpdateFrm.onsubmit = () =>{
      if(!pw1Check || !pw2Check || !placeCheck || !storeCheck){
        alert("뭔가 오류임");
        return false;
      }
     }
     /*회원 정보 클릭 이벤트*/
     document.querySelector('#memberBtn').addEventListener('click',(e)=>{
        //회원 정보 진하게
        e.target.style.fontWeight = "700";
        e.target.style.fontSize="22px";
        
        //신고 관리 연하게
        const reportBtn = document.querySelector('#reportBtn');
        reportBtn.style.fontWeight = "500";
     });
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>