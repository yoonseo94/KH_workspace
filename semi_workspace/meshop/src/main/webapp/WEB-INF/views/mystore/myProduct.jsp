<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/mystore.css">
 <div class="frame">
 <div class="abcd">
        <h2><span class="material-symbols-outlined md-78">location_away</span> <%=loginMember.getStoreName() %>님, 안녕하세요</h2>
    </div>

    <nav class="myhomeContainer">
        <ul>
            <li class="homeItem"><a href="myProduct.jsp">내가 올린글</a></li>
            <li class="homeItem"><a href="likeProduct.jsp">찜한 상품</a> </li>
            <li class="homeItem"><a href="resvProcuct.jsp">예약중인 상품</a></li>
            <li class="homeItem"><a href="completeProcuct.jsp">거래 완료 상품</a></li>
            <li class="homeItem"><a href="reviewProduct.jsp">상품 후기</a></li>
        </ul> 
    </nav>

    <!-- 테이블 구역 -->
    <div class="board_list_wrap">
     <table class="board_list" id="tblMyProduct">
         <caption>내가 올린 상품 목록</caption>
         <!-- 테이블에서 열의 너비를 정해주는 태그 -->
         <colgroup>
            <col style="width: 20%">
            <col style="width: 40%">
            <col style="width: 20%">
            <col style="width: 10%">
            <col style="width: 10%">
        </colgroup>

         <tbody>               
         </tbody>

     </table>
    </div>
    </div>
    <script>
    window.addEventListener('load',()=>{
    	//페이지 온로드 이벤트
    	
       //헤더 높이 구하기
       const header = document.querySelector('.header');

       //메인 컨텐츠의 padding top 높이 조절하기
       const frame = document.querySelector('.abcd');
       frame.style.paddingTop = `\${header.offsetHeight}px`;
       
   	// 내가 올린 상품 목록 조회하기
       getMyProduct();
    });
    
    const getMyProduct = () =>{
    	// 내가 올린 상품 목록 조회하기
    	const memberId = "<%=loginMember.getMemberId()%>";
    	
    	//테이블 가져오기
    	const table = document.querySelector(".board_list tbody");
    	$.ajax({
    		method:"POST",
    		url:"<%=request.getContextPath()%>/mystore/myProduct",
    		data : {
    			memberId : memberId
    		},
    		success(productList){
    			console.log(productList);
    			
    			productList.forEach((product)=>{
    				//product를 리스트에서 하나씩 가져옴
    				
    				//구조분해 할당 (객체 하나씩 값을 빼온다..)
    				const {attachment, title, productId} = product;
    				
    				//첨부파일에서 img 파일 명 꺼내오기
    				const {renamedFilename} = attachment; 
    				
    				//html코드를 작성해주시면 됩니다....
    				const html = `
    					<tr>
    						<td><img class="my_img" src="<%=request.getContextPath()%>/images/\${renamedFilename}"></td>
    						<td><a href="">\${title}</a></td>
    		                <td>
    	                    <select name="reserv_complete" class="reserv_complete" id="reserv_complete">
    	                        <option value="" selected hidden>선택</option>
    	                        <option value="reserv" >예약</option>
    	                        <option value="sucess">판매 완료</option>
    	                    </select>        
    	                	</td>
	    	                <td><a href="#">수정</a></td>
	    	                <td><a href="#">삭제</a></td>
	    	            </tr>
    				`;
    				// table tbody에 하나씩 tr을 넣어준다.
    				table.insertAdjacentHTML('beforeend',html);
    			});
    		},
    		error : console.log
    	});
    }
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>