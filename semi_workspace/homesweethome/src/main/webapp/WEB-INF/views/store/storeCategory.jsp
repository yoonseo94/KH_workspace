<%@page import="product.model.dto.ProductExt"%>
<%@page import="product.model.dto.ProductImage"%>
<%@page import="store.model.dao.TodayDeal"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
 <link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" rel="stylesheet">
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/store/storeCategory.css" />
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/storesubmenu.jsp" %>

<% 
	List<ProductExt> productDealExtLists = (List<ProductExt>) request.getAttribute("productDealExtLists");
	List<ProductExt> productListByDefaults = (List<ProductExt>) request.getAttribute("productListByDefaults");
	List<ProductExt> productListByCategory = (List<ProductExt>) request.getAttribute("productListByCategory");
	List<TodayDeal> todayDeals = (List<TodayDeal>) request.getAttribute("todayDeals");
	String mainNameVal = (String) request.getAttribute("mainNameVal");
	System.out.println(productListByDefaults);
%>
<body>
<aside id="left-tab-menu">
  <div id="w" class="sidebar" align="left" style="width:20%">
    <nav>
      <ul id="main-catergory">
         <li><a href="#" id="furniture" class="main_category">가구</a>
          <ul class="content-wrapper">
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
         <li><a href="#" id="electronics" class="main_category">가전제품</a>
 			<ul class="content-wrapper">
	          <li><a href="#" id="tv" class="sub_category">TV</a></li>
			  <li><a href="#" id="air_conditioner" class="sub_category">에어컨</a></li>
			  <li><a href="#" id="refrigerator" class="sub_category">냉장고</a></li>
			  <li><a href="#" id="kimchi_refrigerator" class="sub_category">김치냉장고</a></li>
			  <li><a href="#" id="oven" class="sub_category">오븐</a></li>
			  <li><a href="#" id="microwave" class="sub_category">전자레인지</a></li>
			  <li><a href="#" id="washing_machine" class="sub_category" >세탁기</a></li>
            </ul>
        </li>
        <li><a href="#" id="lighting" class="main_category">조명</a>
          <ul class="content-wrapper">
				 <li><a href="#" id="led_lighting" class="sub_category">LED등</a></li>
				 <li><a href="#" id="fluorescent_lamp" class="sub_category">형광등</a></li>
				 <li><a href="#" id="desk_stand" class="sub_category">데스크 스탠드</a></li>
				 <li><a href="#" id="mood" class="sub_category">무드등</a></li>
				 <li><a href="#" id="wall_light" class="sub_category">벽조명</a></li>
				 <li><a href="#" id="sensor_light" class="sub_category">센서등</a></li>
          </ul>
        </li>
        <li><a href="#" id="organizing" class="main_category">수납정리</a>
          <ul class="content-wrapper">
	        	<li><a href="#" id="storage_closet" class="sub_category">서랍장</a><li>
				<li><a href="#" id="living_box" class="sub_category">리빙박스</a></li>
				<li><a href="#" id="basket" class="sub_category">바구니</a></li>
				<li><a href="#" id="clothes_rack" class="sub_category">행거</a></li>
				<li><a href="#" id="shelf" class="sub_category">선반</a></li>
				<li><a href="#" id="hanger" class="sub_category">옷걸이</a></li>
          </ul>
        </li>
        <li><a href="#" id="living" class="main_category">생활용품</a>
          <ul class="content-wrapper">
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
<div style ="margin-left: 15% ">
<article>
	<div class="main-visual-wrap" id="main-visual">
		<div class="main-visual">
			<div class="bx-wrapper">
				<div class="bx-viewport">
					<ul class="slider-box">
						<li class="visual" id="visual-image1" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel5.png);"></li>
						<li class="visual" id="visual-image2" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel1.png);"></li>
						<li class="visual" id="visual-image3" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel2.png);"></li>
						<li class="visual" id="visual-image4" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel3.png);"></li>
						<li class="visual" id="visual-image5" style="background-image: url(<%= request.getContextPath() %>/images/store/carousel4.png);"></li>
					</ul>	
				 </div>
			</div>
		</div>
		<div class="bx-controls">
			<button type="button" class="bx-prev" style="background-image: url(<%= request.getContextPath() %>/images/store/next.png);"></button>
			<div class="paginations"></div>
			<button type="button" class="bx-next" style="background-image: url(<%= request.getContextPath() %>/images/store/next.png);"></button>
		</div> 
	</div>
</article>
	<section class="store-today-deal-list-section" >
		<header class="store-index-today-deal-list-header">
			<h1 class="store-index-today-deal-list-title" >#지금은 할인중</h1>
			<a class="store-index-today-deal-list-detail-link" href="<%= request.getContextPath() %>/store/todayDeal">더보기</a>
		</header>
		<div class="store-today-deal-list-content">
		<% 
			if(productDealExtLists != null && !productDealExtLists.isEmpty()) {
				for(ProductExt products : productDealExtLists) {						
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
										<span class="production-item-price-price"><%= (int) Math.floor(products.getProductPrice() * products.getDiscountRate() / 1000)*10 %></span>
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
	
	<section class="store-category-list-section" >

			<header class="store-index-category-list-header" style="margin-left:1rem;">
				<h1 class="store-index-category-list-title" >#<%= mainNameVal %></h1>
			</header>
			<div class="store-category-list-content">
			<% 
				if(productListByDefaults != null && !productListByDefaults.isEmpty()) {
					for(ProductExt productsDefault : productListByDefaults) {						
			%>
				<div class="category-item-cont">
					<div class="category-item-wrapper">
						<article class="category-item">
							<a class="category-item-overlay" href="<%= request.getContextPath() %>/store/productView?productId=<%= productsDefault.getProductId() %>">
								<div class="category-item-image">
									<div class="category-item-image-item">
										<div class="production-item-image">
										 <%   
											 	List<ProductImage> productImagess = productsDefault.getProductImages();
												 	ProductImage pImg = productImagess.get(0);
												 	System.out.println(pImg.getOriginalFilename());
											 %>
											<img class="product-image" alt="" src="<%= request.getContextPath() %>/upload/product/<%= pImg.getRenamedFilename() %> ">
						
					
										</div>
									</div>
								</div>
								<div class="category-item-content">
									<div class="category-item-content-wrap">
										<h1 class="category-item-header">
											<span class="category-item-header-brand"><%= productsDefault.getBrandName() %> </span>
											<span class="category-item-header-name"><%= productsDefault.getProductName() %></span>
										</h1>
										<% if( productsDefault.getDiscountRate() == 0 ) { %>
										<span class="production-item-price">
											<span class="production-item-price-price"><%= productsDefault.getProductPrice() %></span>
										</span>
										<% } else { %>
										<span class="production-item-price">
											<span class="production-item-price-rate"><%= productsDefault.getDiscountRate() %> <span class="percentage">% </span></span>
											<span class="production-item-price-price"><%= (int) Math.floor(productsDefault.getProductPrice() * (100 - productsDefault.getDiscountRate()) / 1000)*10 %></span>
										</span>
										<% } %>
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
	
	
</body>



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
<script>
	$(document).ready(function(){
		  $("#main-catergory > li > a").on("click", function(e){
		    if($(this).parent().has("ul")) {
		      e.preventDefault();
		    }
		    
		    if(!$(this).hasClass("open")) {
		      // hide any open menus and remove all other classes
		      $("#main-catergory li ul").slideUp(350);
		      $("#main-catergory li a").removeClass("open");
		      
		      // open our new menu and add the open class
		      $(this).next("ul").slideDown(350);
		      $(this).addClass("open");
		    }
		    
		    else if($(this).hasClass("open")) {
		      $(this).removeClass("open");
		      $(this).next("ul").slideUp(350);
		    }
		  });
		});	
</script>
<script>
 const categoryContent = document.querySelector(".store-category-list-section"); 
	$(".main_category").click((e) => {
		console.log(e);
		console.log(e.target);
		
		const maincodeVal = e.target.id;
		const searchTypeVal = "main_code";
		console.log("maincodeVal  = " + maincodeVal);
		console.log("searchTypeVal  = " + searchTypeVal);
		 
		 $.ajax({
	 		  url: "<%=request.getContextPath()%>/store/StoreSearchByMainCategoryServlet",
	 		  method: "GET",
	 		  data : {
	 			 maincode : maincodeVal,
	 			 searchType : searchTypeVal
	 		  },
			  dataType : "html",
	 		  success(resp){
				  console.log(resp);
				  categoryContent.innerHTML = "";
				  categoryContent.innerHTML = resp;
	 		  },
	 		  error : console.log
	 	}); 
	}); 	 
	
	$(".content-wrapper").click((e) => {
		console.log(e);
		console.log(e.target);
		
		const maincodeVal = e.target.id;
		const searchTypeVal = "sub_code";
		console.log("maincodeVal  = " + maincodeVal);
		console.log("searchTypeVal  = " + searchTypeVal);
		 
		 $.ajax({
	 		  url: "<%=request.getContextPath()%>/store/StoreSearchByMainCategoryServlet",
	 		  method: "GET",
	 		  data : {
	 			 maincode : maincodeVal,
	 			 searchType : searchTypeVal
	 		  },
			  dataType : "html",
	 		  success(resp){
				  console.log(resp);
				  categoryContent.innerHTML = "";
				  categoryContent.innerHTML = resp;
	 		  },
	 		  error : console.log
	 	}); 
	}); 	 
</script>
			

<%@ include file="/WEB-INF/views/common/footer.jsp" %>