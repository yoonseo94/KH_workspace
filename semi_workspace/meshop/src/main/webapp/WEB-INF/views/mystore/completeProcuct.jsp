<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/mystore.css">
<title>내가 올린 상품</title>
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

    <div class="board_list_wrap">
     <table class="board_list" id="tblComplete">
         <caption>내가 올린 상품 목록</caption>
         <!-- 테이블에서 열의 너비를 정해주는 태그 -->
         <colgroup>
            <col style="width: 25%">
            <col style="width: 25%">
            <col style="width: 25%">
            <col style="width: 25%">
            <col style="width: 25%">
        </colgroup>
         <thead>
             <tr>
                <th>거래 날짜</th>
                <th>사진</th>
                <th>제목</th>
                <th>거래 상태</th>
                <th>삭제</th>
             </tr>
         </thead>
         <tbody>                  
             <tr>
                 <!-- <td>2022.06.05</td>
                <td><img class="my_img" src="../img/grizzly.jpg" alt="사진"></td>
                <td><a href="#">제목 입력창 </a></td>
                <td>
                    거래 완료     
                </td>
                <td>
                    <button class="btn_delete" type="button" onclick="deleteItem(this);">삭제</button> -->
                </td>
             </tr>
         </tbody>

     </table>
    </div>

    <ul class="pagination-area">
        <li class="page-item"><a class="btn-paging-jump prev" href="#">Previous</a></li>
        <li class="page-item"><a class="btn-paging active" href="#">1</a></li>
        <li class="page-item"><a class="btn-paging" href="#">2</a></li>
        <li class="page-item"><a class="btn-paging" href="#">3</a></li>
        <li class="page-item"><a class="btn-paging" href="#">4</a></li>
        <li class="page-item"><a class="btn-paging" href="#">5</a></li>
        <li class="page-item"><a class="btn-paging-jump next" href="#">Next</a></li>
      </ul>

    <script>
        function tblComplete(prop){
            const imgSrc = prop.imgSrc;
            const subject = prop.subject;

            const eltblCom = document.querySelector('#tblComplete');
            const eltblComm = eltblCom.querySelector('tbody');
            const elAddTr = document.createElement('tr');
            let _innerHtml = '';
            _innerHtml += `<td>2022.06.05</td>`;
            _innerHtml += `<td><img class="my_img" src="${imgSrc}" alt=""></td>`;
            _innerHtml += `<td><a href="#">${subject}</a></td>`;
            _innerHtml += `<td>`;
            _innerHtml += `거래 완료`;
            _innerHtml += `</td>`;
            _innerHtml += `<td>`;
            _innerHtml += `    <button type="button" class="btn_delete" onclick="deleteItem(this);">삭제</button>`;
            _innerHtml += `</td>`;
        
            elAddTr.innerHTML = _innerHtml;
            eltblComm.append(elAddTr);
        }
        
        // document.addEventListener('DOMContentLoaded', ()=>{

        //     tblComplete({
        //         imgSrc: '../img/grizzly.jpg',
        //         subject: '곰돌잉'
        //     });
        // });

        var list_complete = [
        {imgSrc: '../img/grizzly.jpg', subject:'곰돌잉1'},
        {imgSrc: '../img/grizzly.jpg', subject:'곰돌잉2'},
        {imgSrc: '../img/grizzly.jpg', subject:'곰돌잉3'},
        {imgSrc: '../img/grizzly.jpg', subject:'곰돌잉4'},
        {imgSrc: '../img/grizzly.jpg', subject:'곰돌잉5'},
        {imgSrc: '../img/grizzly.jpg', subject:'곰돌잉6'},
        ];
        
        for(var i =0 ; i<list_complete.length; i++){
            tblComplete(list_complete[i]);
        }


        function deleteItem(elBtnDelete){
            console.log(event, elBtnDelete); // event : 예약어

            elBtnDelete.closest('tr').remove();
        }



    </script>

 
 
 
 
 
 
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>