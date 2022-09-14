<%@page import="java.sql.Date"%>
<%@page import="product.model.dto.ProductIOExt"%>
<%@page import="product.model.dto.ProductIO"%>
<%@page import="product.model.dto.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/product/productIOPage.css" />
<%
	List<ProductIOExt> productsIOExtList = (List<ProductIOExt>) request.getAttribute("productsIOExtList");

	String pagebar = (String) request.getAttribute("pagebar");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");

	System.out.println(searchKeyword);
%>
<aside class="tab-menu-category">
	<div class="main-catergory">
					<div>
						<a href="#" id="furniture" class="main_category">가구</a>
						<div class="content-wrapper">
							<a href="#" id="bookshelf" class="sub_category">책장</a>
							<a href="#" id="desk" class="sub_category">책상</a>
							<a href="#" id="table" class="sub_category">식탁</a>
							<a href="#" id="table_chair" class="sub_category">식탁 의자</a>
							<a href="#" id="office_chair" class="sub_category">사무용 의자</a>
							<a href="#" id="chest_of_drawers" class="sub_category">서랍장</a>
							<a href="#" id="wardrobe" class="sub_category">옷장</a>
						</div>
					</div>
					<div>
						<a href="#" id="electronics"  class="main_category">가전</a>
						<div class="content-wrapper">
							<a href="#" id="tv" class="sub_category">TV</a>
							<a href="#" id="air_conditioner" class="sub_category">에어컨</a>
							<a href="#" id="refrigerator" class="sub_category">냉장고</a>
							<a href="#" id="kimchi_refrigerator" class="sub_category">김치냉장고</a>
							<a href="#" id="oven" class="sub_category">오븐</a>
							<a href="#" id="microwave" class="sub_category">전자레인지</a>
							<a href="#" id="washing_machine" class="sub_category" >세탁기</a>
						</div>
					</div>
					<div>
						<a href="#" id="lighting"  class="main_category">조명</a>
						<div class="content-wrapper">
							<a href="#" id="led_lighting" class="sub_category">LED등</a>
							<a href="#" id="fluorescent_lamp" class="sub_category">형광등</a>
							<a href="#" id="desk_stand" class="sub_category">데스크 스탠드</a>
							<a href="#" id="mood" class="sub_category">무드등</a>
							<a href="#" id="wall_light" class="sub_category">벽조명</a>
							<a href="#" id="sensor_light" class="sub_category">센서등</a>
						</div>
					</div>
					<div>
						<a href="#" id="organizing"  class="main_category">수납/정리</a>
						<div class="content-wrapper">
							<a href="#" id="storage_closet" class="sub_category">서랍장</a>
							<a href="#" id="living_box" class="sub_category">리빙박스</a>
							<a href="#" id="basket" class="sub_category">바구니</a>
							<a href="#" id="clothes_rack" class="sub_category">행거</a>
							<a href="#" id="shelf" class="sub_category">선반</a>
							<a href="#" id="hanger" class="sub_category">옷걸이</a>
						</div>
					</div>
					<div>
						<a href="#" id="living" class="main_category">생활용품</a>
						<div class="content-wrapper">
							<a href="#" id="bathroom_products" class="sub_category">욕실용품</a>
							<a href="#" id="towel" class="sub_category">수건</a>
							<a href="#" id="cleaning_tools" class="sub_category">청소용품</a>
							<a href="#" id="laundry_products" class="sub_category">세탁용품</a>
							<a href="#" id="household_goods" class="sub_category">생활잡화</a>
						</div>
					</div>
		</div>
	<div class="brand"></div>
	<div class="status"></div>
</aside>
<section id="product-io-container">
	<div id="title-wrapper">
		<h2 id="cont-title"> 상품 입출고 관리</h2>	
	</div>
	<div id="search-container">
    	<label for="searchType">검색타입 :</label> 
        <select id="searchType">
            <option value="product_name" <%="product_name".equals(searchType) ? "selected" : ""%>>상품명</option>		
			<option value="status" <%="status".equals(searchType)?"selected":""%>>상태</option>
        <%--    <option value="io_datetime" <%="io_datetime".equals(searchType)?"selected":""%>>입출고일</option> --%>		
			
        </select>
        <div id="search-productName" class="search-type">
            <form class="search-frm" action="<%=request.getContextPath()%>/product/productIOFinder">
                <input type="hidden" name="searchType" value="p.product_name"/>
                <input type="text" class="input-val" name="searchKeyword"  size="50" placeholder="검색할 상품명을 입력하세요." value="<%= "product_name".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit" class="btn-finder">검색</button>			
            </form>	
        </div>
        <div id="search-productStatus" class="search-type">
            <form class="search-frm" action="<%=request.getContextPath()%>/product/productIOFinder">
                <input type="hidden" name="searchType" value="i.status"/>
                <input type="text" class="input-val" name="searchKeyword" size="25" placeholder="대문자 I 또는 O 를 입력하세요." value="<%= "status".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit" class="btn-finder">검색</button>			
            </form>	
        </div>
<%--         <div id="search-productEnrollDate" class="search-type">
            <form class="search-frm" action="<%=request.getContextPath()%>/product/productIOFinder">
                <input type="hidden" name="searchType" value="i.io_datetime"/>
                <input type="date" class="input-val" name="searchKeyword" size="25" value="<%= "io_datetime".equals(searchType) ? searchKeyword : "" %>"/>
                <button type="submit" class="btn-finder">검색</button>			
            </form>	
        </div> --%>
    </div>
	        <hr class="hr1" />	
	<table id="tbl-product-io">
		<thead>
			<tr>
				<th>번호</th>
				<th>상품명</th>
				<th>상품 아이디</th>
				<th>상태</th>
				<th>입출고 수량</th>
				<th>입출고 시간</th>
				<th>현재 수량</th>
			</tr>
		</thead>
		<tbody id="tbody-tb">
<%  
		if(productsIOExtList != null && !productsIOExtList.isEmpty()){
			for(ProductIOExt productIOExt : productsIOExtList){
%>			
			<tr>
				<td><%= productIOExt.getNo() %></td>
				<td><%= productIOExt.getProductName() %></td>
				<td><%= productIOExt.getProductId() %></td>
				<td><%= productIOExt.getStatus().toString() == "I" ? "입고" : "출고" %></td>
				<td><%= productIOExt.getCount() %></td>
				<td><%= productIOExt.getIoDateTime() %></td>
				<td><%= productIOExt.getStock() %></td>
			</tr>			
<%
			}
		}
		else {
%>			
			<tr>
				<td colspan="7">조회된 상품이 없습니다.</td>
			</tr>
<%
		}
%>		
		</tbody>
	</table>        
	<hr class="hr1" />
	<div class="btn-enroll-wrapper">
		<button id="enrollProductStock" type="button" onclick="productInputStock();">상품 입출고</button>
	</div>	
	<div id="pagebar">
		<%= pagebar %>
	</div>
<form 
	action="<%= request.getContextPath() %>/product/productUpdate" 
	name="productUpdateFrm"
	method="GET">
	<input type="hidden" name="productId" />
</form>
<form 
	action="<%= request.getContextPath() %>/product/productDelete" 
	name="productDeleteFrm"
	method="POST">
	<input type="hidden" name="productId" />
</form>
</section>
<script>
document.querySelectorAll(".content-wrapper").forEach((en) => {
	en.style.display="none";
}); 

// 탭메뉴와 탭메뉴 클릭 시 해당되는 상품 불러오기
// select * from product where # = ? 	
$(".main_category").click((e) => {
	console.log(e);
	console.log(e.target);
	
	const maincodeVal = e.target.id;
	const searchTypeVal = "main_code";
	console.log("maincodeVal  = " + maincodeVal);
	console.log("searchTypeVal  = " + searchTypeVal);
	 $.ajax({
 		  url: "<%=request.getContextPath()%>/product/findProductIOBySomething",
 		  method: "GET",
 		  data : {
 			 maincode : maincodeVal,
 			 searchType : searchTypeVal
 		  },
		  dataType : "json",
 		  success(resp){
 			 const tbody = document.querySelector("#tbl-product-io tbody");
 			 tbody.innerHTML = "";
 			 resp.forEach((res) => {
				const {productName, no, productId, count, status, ioDateTime, stock} = res;
				const tr = document.createElement("tr");
				const tdNo = document.createElement("td");
				tdNo.append(no);
				const tdProductName = document.createElement("td");
				tdProductName.append(productName);
				const tdProductId = document.createElement("td");
				tdProductId.append(productId);
				const tdStatus = document.createElement("td");
				tdStatus.append(status);
				const tdCount = document.createElement("td");
				tdCount.append(count);
				const tdStock = document.createElement("td");
				tdStock.append(stock);
				const tdIoDatetime = document.createElement("td");
				tdIoDatetime.append(ioDateTime);
				tr.append(tdNo, tdProductName, tdProductId, tdStatus, tdCount, tdIoDatetime, tdStock);
				tbody.append(tr);
 			});
 		  },
 		  error : console.log
 	}); 
	 
   document.querySelectorAll(".content-wrapper").forEach((en) => {
    	$(en).slideUp(300);
    }); 
	$(e.target)
        .next()
        .slideToggle()
        .siblings(".content-wrapper")
        .slideUp();
  });
  
$(".content-wrapper").click((e) => {
	const maincodeVal = e.target.id;
	const searchTypeVal = "sub_code";
	console.log(e);
	console.log(maincodeVal);
	 $.ajax({
 		  url: "<%=request.getContextPath()%>/product/findProductIOBySomething",
 		  method: "GET",
 		  data : {
 			 maincode : maincodeVal,
 			 searchType : searchTypeVal
 		  },
		  dataType : "json",
 		  success(resp){
 			 const tbody = document.querySelector("#tbl-product-io tbody");
 			 tbody.innerHTML = "";
 			 resp.forEach((res) => {
 				const {productName, no, productId, count, status, ioDateTime, stock} = res;
				const tr = document.createElement("tr");
				const tdNo = document.createElement("td");
				tdNo.append(no);
				const tdProductName = document.createElement("td");
				tdProductName.append(productName);
				const tdProductId = document.createElement("td");
				tdProductId.append(productId);
				const tdStatus = document.createElement("td");
				tdStatus.append(status);
				const tdCount = document.createElement("td");
				tdCount.append(count);
				const tdStock = document.createElement("td");
				tdStock.append(stock);
				const tdIoDatetime = document.createElement("td");
				tdIoDatetime.append(ioDateTime);
				tr.append(tdNo, tdProductName, tdProductId, tdStatus, tdCount, tdIoDatetime, tdStock);
				tbody.append(tr);
 			});
 		  },
 		  error : console.log
 	}); 
	 
   document.querySelectorAll(".content-wrapper").forEach((en) => {
    	$(en).slideUp(300);
    }); 
	$(e.target)
        .next()
        .slideToggle()
        .siblings(".content-wrapper")
        .slideUp(); 
      
      
  });


window.onload = () => {
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	});
	document.querySelector("#search-productName").style.display = "inline-block";
}; 


const productInputStock = () => {
	location.href="<%= request.getContextPath() %>/product/productInputStock"
}

searchType.addEventListener('change', (e) => {
	const {value} = e.target;  
	
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	});
	
	let id = "";
	switch(value){
		case "product_name": id = "search-productName"; break; 
		case "status": id = "search-productStatus"; break; 
		case "io_datetime": id = "search-productEnrollDate"; break; 
 
	}
	document.querySelector(`#\${id}`).style.display = "inline-block";
});
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>