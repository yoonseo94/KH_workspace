<%@page import="product.model.dto.ProductImage"%>
<%@page import="store.model.dao.TodayDeal"%>
<%@page import="product.model.dto.ProductExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/storesubmenu.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/storeItem.css" />
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/store/todaydeal.css" />
<% 
	List<ProductExt> productExtLists = (List<ProductExt>) request.getAttribute("productExtLists");
	List<TodayDeal> todayDeals = (List<TodayDeal>) request.getAttribute("todayDeals");
	System.out.println("todayDeals = " + todayDeals);
%>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">


	
</head>	
<body>
 
	<h2 id="main_title_todaydeal" align="left" style="margin-top:60px; margin-left:20%;"> 오늘의 딜! </h2>	
	<h5 id="sub_title_todaydeal" align="left" style="margin-left:20%;">매일 새로운 특가상품!</h5>
	
		<div class="store-today-deal-list-content">
		<% 
			if(productExtLists != null && !productExtLists.isEmpty()) {
				for(ProductExt products : productExtLists) {						
		%>
			<div class="today-deal-item-cont">
				<div class="today-deal-item-wrapper">
					<article class="today-deal-item">
						<a class="today-deal-item-overlay" href="<%= request.getContextPath() %>/store/productView?productId=<%= products.getProductId() %>">
							<div class="today-deal-item-image">
								<div class="today-deal-item-image-item">
									<div class="production-item-image">
									 <%   
										 	List<ProductImage> productImages = products.getProductImages();
											 	ProductImage pImg = productImages.get(0);
										 %>
										<img class="product-image" alt="" src="<%= request.getContextPath() %>/upload/product/<%= pImg.getRenamedFilename() %> ">
											
				
									</div>
								</div>
							</div>
							<div class="today-deal-item-content">
								<div class="today-deal-item-content-wrap">
									<h1 class="today-deal-item-header">
										<span class="today-deal-item-header-brand"><%= products.getBrandName() %> </span>
										<span class="today-deal-item-header-name"><%= products.getProductName() %></span>
									</h1>
									<span class="production-item-price">
										<span class="production-item-price-rate"><%= products.getDiscountRate() %> <span class="percentage">% </span></span>
										<span class="production-item-price-price"><%= (int) Math.floor(products.getProductPrice() * (100 - products.getDiscountRate()) / 1000)*10 %></span>
									</span>
								</div>
							</div>
						</a>
					</article>
				</div>
			</div>
			<%
						}
					}
				%>

		</div>
    
    
    
</body>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>