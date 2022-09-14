<%@page import="product.model.dto.ProductDescriptionImage"%>
<%@page import="store.model.dao.TodayDeal"%>
<%@page import="product.model.dto.ProductImage"%>
<%@page import="product.model.dto.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/storesubmenu.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/store/storeproductdetailview.css" />
<% 
	ProductExt product = (ProductExt) request.getAttribute("products");
    List<TodayDeal> todayDeals = (List<TodayDeal>) request.getAttribute("todayDeals");
	System.out.println("product@jsp = " + product);
%>

<div id="product-view-cont">
	<article id="product-view-1">
		<div class="production-selling-overview container">
			<nav class="commerce-category-breadcrumb-wrap production-selling-overview-category">
				<ol class="commerce-category-breadcrumb">
					<li class="commerce-category-breadcrumb-entry">
						<a class="link" href="#"><%= product.getMainCategoryName() %></a>
						<span class="arrow-link">></span>
					</li>
					<li class="commerce-category-breadcrumb-entry">
						<a class="link" href="#"> <%= product.getSubCategoryName() %></a>
					</li>
				</ol>
			</nav>
			<div class="production-selling-overview-container-row">
				<div class="production-selling-overview-cover-image-wrap">
					<div class="production-selling-cover-image-container">
						<div class="carousel-production-selling-cover-image production-selling-overview-cover-image" role="region" aria-roledescription="carousel">
						
							<div class="carousel-list-wrap production-selling-cover-image-carousel-wrap">
								<div class="carousel-list" aria-live="polite" style="transform: translateX(0%); transition: transform 0s ease 0s;">
									<div class="carousel-list-entry" imgno ="" style="width: 100%;">
									</div>		
								</div>
							</div>
						
							<ul class="production-selling-cover-image-list">
							<%
								if(product != null) {
									List<ProductImage> pImgs = product.getProductImages();
									for(int i = 0; i < pImgs.size(); i++) {
							%>
								<li class="production-selling-cover-image-list-item">
									<button class="production-selling-cover-image-list-btn" type="button" >
										<img class="btn-image" src="<%= request.getContextPath() %>/upload/product/<%= pImgs.get(i).getRenamedFilename() %>" imgno="<%= i %>">
									</button>
								</li>
							<% 
									}
								}
							%>
							</ul>	 
						</div>
						 
					</div>
				</div>
				<div class="production-selling-overview-content ">
					<div class="production-selling-header">
						<h1 class="production-selling-header-title">
							<p class="production-selling-header-title-brand-wrap">
								<a class="production-selling-header-title-brand" href="/brands/home?query=id391014"><%= product.getBrandName() %></a>
							</p>
							<div class="production-selling-header-title-name-wrap">
								<span class="production-selling-header-title-name"><%= product.getProductName() %></span>
							</div>
						</h1>
						<div class="production-selling-header-content"> 
							<div class="production-selling-header-price">
								<span class="production-selling-header-price-price-wrap">
								<%   
									if(product.getDiscountRate() == 0) {
								%>
									<span class="production-selling-header-price-price">
											<span class="number"><%= product.getProductPrice() %></span>
											<span class="won">원</span>
									</span>	
										
								<% } else {
										for(TodayDeal todayDeal : todayDeals){
											if(product.getProductId().equals(todayDeal.getProductId())){	
								%>
									<div class="discount-wrapper">
										<span class="production-selling-header-price-discount">
											<span class="number"><%= todayDeal.getDiscountRate() %></span>
											<span class="percent">%</span>
										</span>
										<del class="production-selling-header-price-original">
												<span class="number"><%= product.getProductPrice() %></span>
												<span class="won">원</span>
										</del>
									</div>
										<span class="production-selling-header-price-separator"></span>
										<span class="production-selling-header-price-price">
											<span class="number"><%= (int) Math.floor(product.getProductPrice() * (100 - todayDeal.getDiscountRate()) / 1000)*10 %></span>
											<span class="won">원</span>
											<span class="production-selling-header-price-badge"> </span>
										</span>	
									<%		}
										}
									}
									%> 

								</span>
							</div>
						</div>
						<div class="production-selling-header-info-wrap">
							<div class="production-selling-header-delivery">
								<div class="production-selling-header-delivery-title-wrap">
									<span>배송</span>
								</div>
								<div class="production-selling-header-delivery-content-wrap">
									<span class="production-selling-header-delivery-fee">
										<b>무료배송</b>
									</span>
									<span class="production-selling-header-delivery-type">
										<span>일반택배</span>
									</span>
									<div class="_2SUYq production-selling-header-delivery-expectation-date-dropdown">
										<div class="production-selling-header-delivery-expectation-section">
											<div class="production-selling-header-delivery-expectation-wrap">
												<div
													class="production-selling-header-delivery-expectation-date-header">
													<span class="text"><span class="date">내일 </span>도착 예정</span>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<% if (loginMember != null){ %>
					<div class="production-selling-option-form production-selling-overview-option-form">
						<div class="production-selling-option-form-footer">
							<button class="cart-btn" type="button" onclick="moveToCart">장바구니</button>
							<button class="buy-btn" id="buy-button" type="button" onclick="immediatelyBuy">바로구매</button>
						</div>
					</div>
					<% } %>
				</div>
			</div>
		</div>
	</article>
	<article id="product-view-2">
		<div class="product-detail-description-view-wrapper">
			<% 
								if(product != null) {
									List<ProductDescriptionImage> pDesImgs = product.getProductDescriptionImages();
									for(int i = 0; i < pDesImgs.size(); i++) {
							%>
									<div class="carousel-des-list">
										<img class="production-des-image" src="<%= request.getContextPath() %>/upload/product/<%= pDesImgs.get(i).getRenamedFilename() %>">
									</div>		
							<% 
									}
								}
							%>	
		</div>
	</article>
</div>

<script>
	const body = document.querySelector(".carousel-list-entry");
window.onload = (e) =>{
	<%
	if(product != null) {
		List<ProductImage> ppImgs = product.getProductImages();
	%>
	body.innerHTML = `<img class="production-selling-cover-image-entry-image" src="<%= request.getContextPath() %>/upload/product/<%= ppImgs.get(0).getRenamedFilename() %>">`
	<% } %>
}

	let noVal;
	$(".btn-image").click(function() {
		noVal = $(this).attr('imgno');
		body.innerHTML = "";

		$.ajax({
			url : "<%= request.getContextPath() %>/store/findImgNo",
			method : "GET",
			dataType : "text",
			data : {
				noVal : noVal,
				productId : "<%= product.getProductId() %>"
			},
			success(resp){
				body.innerHTML = resp; 
			},
			error: console.log
		});
	
	}); 
	
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>