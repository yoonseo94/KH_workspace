<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
 <title> 리뷰 확인</title>
</head>
<body>
    <div class="header">
        <h2><span class="material-symbols-outlined md-78">location_away</span> 님, 안녕하세요</h2>
    </div>

    <nav class="myhomeContainer">
        <ul>
            <li class="homeItem"><a href="myProduct.html">내가 올린글</a></li>
            <li class="homeItem"><a href="likeProduct.html">찜한 상품</a> </li>
            <li class="homeItem"><a href="resvProcuct.html">예약중인 상품</a></li>
            <li class="homeItem"><a href="completeProcuct.html">거래 완료 상품</a></li>
            <li class="homeItem"><a href="reviewProduct.html">상품 후기</a></li>
        </ul> 
    </nav>
    <div class="board_review">
        <table class="board_review" id="tblReview">
            <caption>내가 올린 상품 목록</caption>
            <!-- 테이블에서 열의 너비를 정해주는 태그 -->
            <colgroup>
               <col style="width: 15%">
               <col style="width: 20%">
               <col style="width: 45%">
               <col style="width: 20%">
           </colgroup>
            <thead>
                <tr>
                   <th>날짜</th>
                   <th>User</th>
                   <th>Text</th>
                   <th>평점</th>
                </tr>
            </thead>
            <tbody>                  
                <tr>
                   <td><a href="#">22.06.02</a></td>
                   <td>홍길동</td>
                   <td>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo dicta, quas possimus debitis veritatis magnam minima consectetur consequatur natus impedit. Autem asperiores necessitatibus suscipit odio porro quibusdam minus tempora illum!</p> 
                   </td>     
                   <td>
                        <div class="star-area">
                            <div class="star-scroe" style="width:50%"></div>
                        </div>
                    </td>
                </tr>
            </tbody> 
   
        </table>
       </div>
