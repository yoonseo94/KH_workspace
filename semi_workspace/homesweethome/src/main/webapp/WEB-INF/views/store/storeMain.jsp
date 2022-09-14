<%@page import="product.model.dto.ProductImage"%>
<%@page import="store.model.dao.TodayDeal"%>
<%@page import="product.model.dto.ProductExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
List<ProductExt> productExtLists = (List<ProductExt>) request.getAttribute("productExtLists");
List<TodayDeal> todayDeals = (List<TodayDeal>) request.getAttribute("todayDeals");
System.out.println("todayDeals = " + todayDeals);
System.out.println("productExtLists = " + productExtLists);
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/store/storeMain.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/storesubmenu.jsp" %>
<article>
	<div class="main-visual-wrap" id="main-visual">
		<div class="main-visual">
			<div class="bx-wrapper">
				<div class="bx-viewport">
					<ul class="slider-box">
						<li class="visual" id="visual-image1" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel1.png);"></li>
						<li class="visual" id="visual-image2" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel2.png);"></li>
						<li class="visual" id="visual-image3" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel3.png);"></li>
						<li class="visual" id="visual-image4" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel4.png);"></li>
						<li class="visual" id="visual-image5" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel5.png);"></li>
						<li class="visual" id="visual-image6" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel6.png);"></li>
						<li class="visual" id="visual-image7" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel7.png);"></li>
					</ul>	
				 </div>
			</div>
		</div>
		<div class="bx-controls">
			<button type="button" class="bx-prev" style="background-image: url(<%= request.getContextPath() %>/images/store/next.png);"></button>
			<div class="paginations"></div>
			<button type="button" class="bx-next" style="background-image: url(<%= request.getContextPath() %>/images/store/next.png);"></button>
		</div> 
</article>
	</div>
	<section class="store-category-list-cont" style="margin-bottom: 2rem;">
		<h1 class="store-category-list-title">카테고리</h1>
		<div class="category-list-wrap">
			<div class="category-list-fold" style="transition-duration: 0ms; transform: translateX(0%);">
				<div class="category-item-wrap">
					<a href="<%= request.getContextPath() %>/store/storeCategory?mainCategory=furniture">
						<div class="category-item">
								<img class="category-item-image" src="<%= request.getContextPath() %>/images/store/furniture-mini.png">
								<div class="category-item-title">가구</div>
						</div>
					</a>
				</div>
				<div class="category-item-wrap">
						<a href="<%= request.getContextPath() %>/store/storeCategory?mainCategory=electronics">					
						<div class="category-item">
								<img class="category-item-image" src="<%= request.getContextPath() %>/images/store/electronics-mini.png">
								<div class="category-item-title">가전</div>
						</div>
					</a>
				</div>
				<div class="category-item-wrap">
						<a href="<%= request.getContextPath() %>/store/storeCategory?mainCategory=lighting">
						<div class="category-item">
								<img class="category-item-image" src="<%= request.getContextPath() %>/images/store/light-minii.png">
								<div class="category-item-title">조명</div>
						</div>
					</a>
				</div>
				<div class="category-item-wrap">
					<a href="<%= request.getContextPath() %>/store/storeCategory?mainCategory=organizing">
						<div class="category-item">
								<img class="category-item-image" src="<%= request.getContextPath() %>/images/store/organizing.png">
								<div class="category-item-title">수납정리</div>
						</div>
					</a>
				</div>
				<div class="category-item-wrap">
					<a href="<%= request.getContextPath() %>/store/storeCategory?mainCategory=living">
						<div class="category-item">
								<img class="category-item-image" src="<%= request.getContextPath() %>/images/store/household-mini.png">
								<div class="category-item-title">생활용품</div>
						</div>
					</a>
				</div>
			</div>
		</div>
	</section>
	<section class="store-today-deal-list-section">
		<header class="store-index-today-deal-list-header">
			<h1 class="store-index-today-deal-list-title">오늘의딜</h1>
			<a class="store-index-today-deal-list-detail-link" href="<%= request.getContextPath() %>/store/todayDeal">더보기</a>
		</header>
		
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
	</section>
<script>

// 캐러셀
	const list = document.querySelector('.slider-box');
	const items = document.querySelectorAll('.visual');
	const btnPrev = document.querySelector('.bx-prev');
	const btnNext = document.querySelector('.bx-next');
	const paginations = document.querySelector('.paginations');
	const lastIndex = items.length - 1;
	let selected = 0;
	let interval;
	
	
	const setTransition = (value) => {
	  list.style.transition = value;
	};
	
	const setTranslate = ({ index, reset }) => {
	  if (reset) {
	    list.style.transform = `translate(-\${list.clientWidth}px, 0)`;
	  }
	  else {
	    list.style.transform = `translate(-\${(index + 1) * list.clientWidth}px, 0)`;
	  }  
	};
	
	const activePagination = (index) => {
	  [...paginations.children].forEach((pagination) => {
	    pagination.classList.remove('active');
	  });
	  paginations.children[index].classList.add('active');
	};
	
	const handlePagination = (e) => {
	  if (e.target.dataset.num) {
	    selected = parseInt(e.target.dataset.num);
	    setTransition('all 0.3s linear');
	    setTranslate({ index: selected });
	    activePagination(selected);
	  }
	};
	
	const makePagination = () => {
	  if (items.length > 1) {
	    for (let i = 0; i < items.length; i++) {
	      const button = document.createElement('li');
	      button.dataset.num = i;
	      button.style.backgroundImage = "url(<%= request.getContextPath() %>/images/store/circle.png)";
	      button.classList.add('pagination');
	      if (i === 0) {
	        button.classList.add('active');
	      }
	      paginations.appendChild(button);
	      paginations.addEventListener('click', handlePagination);
	    }
	  }
	};
	
	
	const handlePrev = () => {
	  selected -= 1;
	  setTransition('transform 0.3s linear');
	  setTranslate({ index: selected });
	  if (selected < 0) {
	    selected = lastIndex;
	    setTimeout(() => {
	      setTransition('');
	      setTranslate({ index: selected });
	    }, 300);
	  }
	  if (selected >= 0) activePagination(selected);
	};
	
	const handleNext = () => {
	  console.log(selected);
	  selected += 1;
	  setTransition('transform 0.3s linear');
	  setTranslate({ index: selected });
	  if (selected > lastIndex) {
	    selected = 0;
	    setTimeout(() => {
	      setTransition('');
	      setTranslate({ index: selected });
	    }, 300);
	  }
	  if (selected <= lastIndex) activePagination(selected);
	};
	
	const clickButton = () => {
	  if (items.length > 1) {
	    btnPrev.addEventListener('click', handlePrev);
	    btnNext.addEventListener('click', handleNext);
	  }
	};
	
	
	const cloneElement = () => {
	  list.prepend(items[lastIndex].cloneNode(true));
	  list.append(items[0].cloneNode(true));
	  setTranslate({ reset: true });
	};
	
	
	const autoplayIterator = () => {
	  selected += 1;
	  setTransition('all 0.3s linear');
	  setTranslate({ index: selected });
	  if (selected > lastIndex) {
	    activePagination(0);
	    clearInterval(interval);
	    setTimeout(() => {
	      selected = 0;
	      setTransition('');
	      setTranslate({ reset: true });
	      autoplay({ duration: 4000 });
	    }, 300);
	  }
	  if (selected <= lastIndex) activePagination(selected);
	};
	
	const autoplay = ({ duration }) => {
	  interval = setInterval(autoplayIterator, duration);
	};
	    
	const render = () => {
	  clickButton();
	  makePagination();
	  cloneElement();
	  autoplay({ duration: 4000 });
	};
	render();
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>