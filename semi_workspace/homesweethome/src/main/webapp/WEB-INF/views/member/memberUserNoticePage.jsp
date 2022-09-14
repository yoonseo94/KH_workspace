<%@page import="product.model.dto.ProductExt"%>
<%@page import="cart.model.dto.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/cartView.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<%
	List<Cart> cartList = (List<Cart>) request.getAttribute("cartList");
	List<ProductExt> productList = (List<ProductExt>) request.getAttribute("productList");
%>
<script>
</script>
<style>

</style>
<div class="css-yrqts6 eek8okh2">
	<h1 class="css-1f09m21 eek8okh1">내 소식</h1>
		<div class="eek8okh0 css-134p14f"><p class="css-uotqtd">최근 내 소식이 없습니다.</p>
		</div>
</div>

<script>
function selectAll(selectAll)  {
	  const checkboxes 
	       = document.getElementsByName('product');
	  
	  checkboxes.forEach((checkbox) => {
	    checkbox.checked = selectAll.checked;
	  })
}



</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>