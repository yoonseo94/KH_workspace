<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<meta charset="UTF-8">
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%


%>
<title>찜상품 목록</title>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/mystore.css">
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
 
  <div class="list_wrap">
      <ul class="listPin" >
          <!-- <li class="item item1" >
              <div class="image" ><img class="my_pin" src="../img/grizzly.jpg" alt="사진"></a> </div>
              <button class="btn-heart active" ></button>
              <div class="cont" >
              <strong>제목이 들어갑니다.</strong>
              <p>내용이 들어갑니다.</p>
              <a href="#">바로가기</a>
            </div>
          </li>    -->
      </ul>

      <ul class="pagination-area">
        <li class="page-item"><a class="btn-paging-jump prev" href="#">Previous</a></li>
        <li class="page-item"><a class="btn-paging active" href="#">1</a></li>
        <li class="page-item"><a class="btn-paging" href="#">2</a></li>
        <li class="page-item"><a class="btn-paging" href="#">3</a></li>
        <li class="page-item"><a class="btn-paging" href="#">4</a></li>
        <li class="page-item"><a class="btn-paging" href="#">5</a></li>
        <li class="page-item"><a class="btn-paging-jump next" href="#">Next</a></li>
      </ul>
  </div>

  
  <script>
  // $('.btn-heart').on('click', function(){
  //   console.log('!')
  //   $(this).toggleClass('active');
  // })


  // document.querySelectorAll('cli')

  $(document).on('click', '.btn-heart', function(){
    $(this).toggleClass('active');
  })
  // document.getElementById("item1").style.url

</script>




<script>
        function list_wrap(prop){
            const imgSrc = prop.imgSrc;
            const title = prop.title;
            const content = prop.content;

            const elTblMyPin = document.querySelector('.listPin');
            const elPintype = document.createElement('li');
            elPintype.classList.add('item','item1');
            
            let _innerHtml = '';
            _innerHtml += `<div class="image" ><img class="my_pin" src="${imgSrc}" alt=""></a> </div>`;
            _innerHtml += `<button class="btn-heart active" ></button>`;
            _innerHtml += `<div class="cont" >`;
            _innerHtml += `<strong>${title}</strong>`;
            _innerHtml += `<p>${content}</p>`;
            _innerHtml += `<a href="#">바로가기</a>`;
         
        
            elPintype.innerHTML = _innerHtml;
            elTblMyPin.append(elPintype);
        }
        
        // document.addEventListener('DOMContentLoaded', ()=>{

        //   list_wrap({
        //         imgSrc: '../img/grizzly.jpg',
        //         title: '그리즐리배어팔아요',
        //         content: '저를 사시면 물어드립니다.'
        //     });




        // });

        var list_arr = [
          {imgSrc: '../img/grizzly.jpg',title: '그리즐리배어팔아요',content: '저를 사시면 물어드립니다.'},
          {imgSrc: '../img/grizzly.jpg',title: '그리즐리배어팔아요',content: '저를 사시면 물어드립니다.'},
          {imgSrc: '../img/grizzly.jpg',title: '그리즐리배어팔아요',content: '저를 사시면 물어드립니다.'},
          {imgSrc: '../img/grizzly.jpg',title: '그리즐리배어팔아요',content: '저를 사시면 물어드립니다.'},
          {imgSrc: '../img/grizzly.jpg',title: '그리즐리배어팔아요',content: '저를 사시면 물어드립니다.'},
          {imgSrc: '../img/grizzly.jpg',title: '그리즐리배어팔아요',content: '저를 사시면 물어드립니다.'}
          
        ];

        for(var i =0; i<list_arr.length;i++){
          list_wrap(list_arr[i]);

        }


    


    

    </script>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>