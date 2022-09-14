<%@page import="product.model.dto.Product"%>
<%@page import="javax.swing.plaf.synth.SynthOptionPaneUI"%>
<%@page import="java.util.ArrayList"%>
<%@page import="product.model.dto.ProductMainCode"%>
<%@page import="product.model.dto.ProductSubCode"%>
<%@page import="product.model.dto.ProductBrand"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>  
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>  
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product/productInputStock.css" />
<%
	List<Product> productsList = (List<Product>) request.getAttribute("productsList");
	String searchTypeMain = request.getParameter("searchTypeMain");
	String searchTypeSub = request.getParameter("searchTypeSub");
%>
<script>
/**
* productEnrollFrm 유효성 검사
*/
window.onload = () => {	
	document.productIOEnrollFrm.onsubmit = (e) => {
		const frm = e.target;

		const productNameVal = frm.productName.value.trim(); // 좌우공백
		if(!/^.+$/.test(productNameVal)){
			alert("상품명을 작성해주세요.");
			frm.productName.select();
			return false;
		}		
		
		
		const productHeightVal = frm.productHeight.value.trim(); // 좌우공백
		if(!/^.+$/.test(productHeightVal)){
			alert("상품 높이를 작성해주세요.");
			frm.productHeight.select();
			return false;
		}		
		
		const productWidthVal = frm.productWidth.value.trim(); // 좌우공백
		if(!/^.+$/.test(productWidthVal)){
			alert("상품 너비를 작성해주세요.");
			frm.productWidth.select();
			return false;
		}		
		
		const productDepthVal = frm.productDepth.value.trim(); // 좌우공백
		if(!/^.+$/.test(productDepthVal)){
			alert("상품 깊이를 작성해주세요.");
			frm.productDepth.select();
			return false;
		}		
		
	}
}

</script>
<section id="product-input-container">
	<div id="title-wrapper">
		<h2 id="cont-title">상품 등록</h2>	
	</div>
<hr class="hr1" />	
<form
	id="tbl-product-enroll"
	name="productIOEnrollFrm"
	action="<%=request.getContextPath() %>/product/productInputStock" 
	method="POST">
	<table id="tbl-input-view">
	<tr>
		<th>상품 아이디</th>
		<td><input class="tbl-input"  name="productId" id="productId" required readonly></td>
		<td>
			<button type="button" class="findId-btn" onclick="findProductId()">상품 찾기</button>
		</td>
	</tr>
	<tr>
		<th>상품명</th>
		<td><input class="tbl-input" type="text" name="productName" id="productName" required readonly></td>
	</tr>
	<tr>
		<th>수량</th>
		<td><input type="number" min = "1" max = "10000" class="tbl-input"  name="count" id="count" required></td>
	</tr>
	<tr>
		<th>상태</th>
		<td>	
            <label for="status-input" class="label-status">입고</label>
   	 		<input type="radio" name="status" id="status-input" value="I">
            <label for="status-output" class="label-status">출고</label>
            <input type="radio" name="status" id="status-output" value="O">
		</td>
	</tr>
	<tr>
		<th colspan="3">
			<input type="submit" id="enroll-submit-btn" value="등록하기">
		</th>
	</tr>
</table>
</form>
</section>
	        <hr class="hr1" />	
<script>
const findProductId = () =>{
	const title = "findProductIdPopup";
	const spec = "width=510px, height=390px";
	const popup = open("<%= request.getContextPath() %>/product/findProductIdByName", title, spec);
}


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
