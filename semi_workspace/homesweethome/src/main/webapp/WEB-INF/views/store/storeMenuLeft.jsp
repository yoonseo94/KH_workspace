<%@page import="store.controller.StoreMainServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/storeMenuLeft.css" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>

<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="<%= request.getContextPath() %>/js/storeMenuLeft.js"></script>
  <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
  <link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" rel="stylesheet">

<style>
.sidebar{
	position:sticky;
	top:0px;
	height:500px;
}


#left-tab-menu{width:30px; background:white}
</style>

</head>



<aside id="left-tab-menu">
  <div id="w" class="sidebar" align="left" style="width:20%">
    <nav>
      <ul id="main-catergory">
        <li><a href="#">오늘의 딜</a>
          <ul>
            <li><a class="sidemenu2" href="#">빠른</a></li>
          </ul>
        </li>
         <li><a href="#">가구</a>
          <ul>
				 <li><a href="#" id="bookshelf" class="sub_category">책장</a></li>
				 <li><a href="#" id="desk" class="sub_category">책상</a></li>
				 <li><a href="#" id="table" class="sub_category">식탁</a></li>
				 <li><a href="#" id="table_chair" class="sub_category">식탁 의자</a></li>
				 <li><a href="#" id="office_chair" class="sub_category">사무용 의자</a></li>
				 <li><a href="#" id="chest_of_drawers" class="sub_category">서랍장</a></li>
				 <li><a href="#" id="wardrobe" class="sub_category">옷장</a></li>
				 <li><a href="#" id="bed" class="sub_category">침대</a></li>
          </ul>
        </li>
         <li><a href="#">가전제품</a>
 			<ul>
	          <li><a href="#" id="tv" class="sub_category">TV</a></li>
			  <li><a href="#" id="air_conditioner" class="sub_category">에어컨</a></li>
			  <li><a href="#" id="refrigerator" class="sub_category">냉장고</a></li>
			  <li><a href="#" id="kimchi_refrigerator" class="sub_category">김치냉장고</a></li>
			  <li><a href="#" id="oven" class="sub_category">오븐</a></li>
			  <li><a href="#" id="microwave" class="sub_category">전자레인지</a></li>
			  <li><a href="#" id="washing_machine" class="sub_category" >세탁기</a></li>
            </ul>
        </li>
        <li><a href="#">조명</a>
          <ul>
				 <li><a href="#" id="led_lighting" class="sub_category">LED등</a></li>
				 <li><a href="#" id="fluorescent_lamp" class="sub_category">형광등</a></li>
				 <li><a href="#" id="desk_stand" class="sub_category">데스크 스탠드</a></li>
				 <li><a href="#" id="mood" class="sub_category">무드등</a></li>
				 <li><a href="#" id="wall_light" class="sub_category">벽조명</a></li>
				 <li><a href="#" id="sensor_light" class="sub_category">센서등</a></li>
          </ul>
        </li>
        <li><a href="#">수납정리</a>
          <ul>
	        	<li><a href="#" id="storage_closet" class="sub_category">서랍장</a><li>
				<li><a href="#" id="living_box" class="sub_category">리빙박스</a></li>
				<li><a href="#" id="basket" class="sub_category">바구니</a></li>
				<li><a href="#" id="clothes_rack" class="sub_category">행거</a></li>
				<li><a href="#" id="shelf" class="sub_category">선반</a></li>
				<li><a href="#" id="hanger" class="sub_category">옷걸이</a></li>
          </ul>
        </li>
        <li><a href="#">생활용품</a>
          <ul>
          		<li><a href="#" id="bathroom_products" class="sub_category">욕실용품</a></li>
				<li><a href="#" id="towel" class="sub_category">수건</a></li>
				<li><a href="#" id="cleaning_tools" class="sub_category">청소용품</a></li>
				<li><a href="#" id="laundry_products" class="sub_category">세탁용품</a></li>
				<li><a href="#" id="household_goods" class="sub_category">생활잡화</a></li>
          </ul>
        </li>
      </ul>
    </nav>
  </div>
  </aside>
  
</html>