<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%

String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <title>Me Shop | 회원가입</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/join.css">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
  <body>
    <div id="header"><img onclick="location.href='<%= request.getContextPath() %>/main'" src="<%=request.getContextPath() %>/resources/images/logo.png" id="logo"/></div>

    <div class="join_wrapper">
      <div class="join_content">
        <form name="memberEnrollFrm" action="<%=request.getContextPath()%>/member/join" method="post">
        <div>
          <h3 class="join_title">
            <label for="id">아이디<sup>*</sup></label>
          </h3>
            <input type="text" id="id" name="memberId" placeholder="영문, 숫자 (8~20)자 아이디" />
            <span class="validation" id="idValid"></span>
        </div>
        <div>
          <h3 class="join_title"><label for="pswd1">비밀번호<sup>*</sup></label></h3>
            <input type="password" id="pswd1" name="password" placeholder="특수문자, 영문, 숫자 최소 8자" />
            <span class="validation" id="pswd1Valid"></span>
        </div>

        <div>
          <h3 class="join_title"><label for="pswd2">비밀번호 확인</label></h3>
            <input type="password" id="pswd2"  placeholder="비밀번호 확인" />
            <span class="validation" id="pswd2Valid"></span>
        </div>
        <div>
          <h3 class="join_title"><label for="name">회원 이름<sup>*</sup></label></h3>
            <input type="text" id="name" name="memberName" placeholder="이름을 입력해주세요"/>
            <span class="validation" id="nameValid"></span>
        </div>
        <div>
          <h3 class="join_title"><label for="storeName">상점 이름<sup>*</sup></label></h3>
            <input type="text" id="storeName" name="storeName" placeholder="상점 이름을 입력해주세요"/>
            <span class="validation" id="storeValid"></span>
        </div>
        <div>
          <h3 class="join_title"><label for="region">내 동네 설정<sup>*</sup></label></h3>
          <select name="region" id="region">
            <option value="">동네 선택</option>
            <option value="종로구">종로구</option>
            <option value="동대문구">동대문구</option>
            <option value="서대문구">서대문구</option>
            <option value="용산구">용산구</option>
            <option value="중구">중구</option>
          </select>
        </div>

        <div class="join_btn_area">
          <button type="submit" class="join">
            <span>가입하기</span>
          </button>
        </div>
      </form>
      </div>
    </div>
  </body>
  <script>
    let idCheck = false;
    let pw1Check = false;
    let pw2Check = false;
    let nameCheck = false;
    let storeCheck = false;
    let placeCheck = false;
    document.querySelector("#id").addEventListener('blur', (e)=>{
      const memberId = e.target.value
      console.log(memberId);

      if(!/^.{4,20}$/.test(memberId)){
        e.target.style.border = "2px solid red";
        document.querySelector("#idValid").innerHTML = "아이디는 8~20자리 이여야 합니다.";
        idCheck = false;
        return;
      }

      e.target.style.border = "1px solid black";
      document.querySelector("#idValid").innerHTML = "";
      idCheck = true;
      
      $.ajax({
          url:"<%=request.getContextPath()%>/member/id-check",
          method:"POST",
          data:{
            memberId : memberId
          },
          success(response){
        	  console.log(response);
            if(response === "true"){
              e.target.style.border = "1px solid black";
              document.querySelector("#idValid").innerHTML = "";
               idCheck = true;
            }else{
              e.target.style.border = "2px solid red";
              document.querySelector("#idValid").innerHTML = "이미 사용중인 아이디입니다.";
              idCheck = false;
            }
          },
          error:console.log
        });
     });
     document.querySelector("#pswd1").addEventListener('blur',(e)=>{
      //password 1
      const password = e.target.value;

      //비밀번호 정규식 검사
      // 길이검사
      if(!/^.{8,20}$/.test(password)){
        e.target.style.border = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }
      // 특수문자 포함여부 !&/\*@
      if(!/[!&/\\*@]/.test(password)){
        e.target.style.border = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }
      // 숫자 포함여부
      if(!/\d/.test(password)){
        e.target.style.border = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }
      // 영문자 포함여부
      if(!/[a-z]/i.test(password)){
        e.target.style.border = "2px solid red";
        document.querySelector("#pswd1Valid").innerHTML = "비밀번호는 특수문자, 영문자, 숫자 8~20자리 이여야 합니다.";
        pw1Check = false;
        return;
      }

      e.target.style.border = "1px solid black";
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
        e.target.style.border = "2px solid red";
        document.querySelector("#pswd1").style.border = "2px solid red";
        document.querySelector("#pswd2Valid").innerHTML = "입력하신 두 비밀번호가 일치하지 않습니다.";
        pw2Check = false;
        return;
      }
      // 정상 로직
      document.querySelector("#pswd1").style.border = "1px solid black";
      document.querySelector("#pswd2").style.border = "1px solid black";
      document.querySelector("#pswd2Valid").innerHTML = "";
      pw2Check = true;

     });

     document.querySelector("#name").addEventListener('blur',(e)=>{
      // 회원 이름 validation
      const name = e.target.value;
      if(name == ""){
        document.querySelector("#name").style.border = "2px solid red";
        document.querySelector("#nameValid").innerHTML = "이름을 입력하세요.";
        nameCheck = false;
        return;
      }
      document.querySelector("#name").style.border = "1px solid black";
      document.querySelector("#nameValid").innerHTML = "";
      nameCheck = true;
     });

     document.querySelector("#storeName").addEventListener('blur',(e)=>{
      // 상점 이름 validation
      const storeName = e.target.value;
      if(storeName == ""){
        document.querySelector("#storeName").style.border = "2px solid red";
        document.querySelector("#storeValid").innerHTML = "상점 이름을 입력하세요.";
        storeCheck = false;
        return;
      }
      //상점 이름 중복불가
      $.ajax({
        url:"<%=request.getContextPath()%>/member/store-check",
        method:"POST",
        data:{
          storeName : storeName
        },
        success(response){
        	console.log(response);
          if(response === "true"){
             document.querySelector("#storeName").style.border = "1px solid black";
             document.querySelector("#storeValid").innerHTML = "";
             storeCheck = true;
          }else{
            document.querySelector("#storeName").style.border = "2px solid red";
            document.querySelector("#storeValid").innerHTML = "이미 사용중인 상점 이름입니다.";
            storeCheck = false;
          }
        },
        error:console.log
      });
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

     document.memberEnrollFrm.onsubmit = () =>{
      if(!idCheck || !pw1Check || !pw2Check || !placeCheck || !nameCheck || !storeCheck){
        alert("모든 입력란을 확인해주세요.");
        return false;
      }
     }
  </script>
</html>
