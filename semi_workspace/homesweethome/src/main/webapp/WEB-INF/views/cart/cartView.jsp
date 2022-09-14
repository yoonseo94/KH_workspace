<%@page import="product.model.dto.ProductExt"%>
<%@page import="cart.model.dto.Cart"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/cartView.css" />
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<%
	List<Cart> cartList = (List<Cart>) request.getAttribute("cartList");
	List<ProductExt> productList = (List<ProductExt>) request.getAttribute("productList");
	String memberId = loginMember.getMemberId();
	
	

%>
<script>

//페이지로딩시
window.addEventListener('load', () => { // .onload로 하면jsp에 하나만 쓸수 있고(아래가덮어씀) .addEventListener는 중복사용 가능 
	// 첫 페이지 요청
	getImg(); // 처음 페이지 열렸을 경우는 무조건 1페이지를 오픈
});
const getImg = () => {
	$.ajax({
		url : "<%= request.getContextPath() %>/cart/productPhoto", 
		success(list) { // 성공하여 받은 객체 cPage정보겠지? / resp : gson으로 json된 데이터를 jquery가 자동으로 json변환처리 된 resp다
 			console.log(list);
			
			const container = document.querySelector("#photo-container"); // 넣을 위치
			list.forEach((product) => {
				const {productImages : {renamedFilename}} = product;
				console.log({productImages : {renamedFilename}});
				 // 사진이 로드되는 상황에서 시간단축을 위해 동적으로 계산하여 출력
				const img = new Image();
				img.src = `<%= request.getContextPath() %>/upload/product/\${{productImages : {renamedFilename}}}`;
				img.onload = () => { // 로딩될 떄 로 바로 바로 
					const div = `
					<picture>
						<img src="\${img.src}" alt="" height="\${height}px"/> // 위에서 가져온 값들 대입
					</picture>`;
					container.insertAdjacentHTML('beforeend', div); // 자식요소로 맨 뒤에 추가 // |(NODE) 방법1. insertBefore 방법2. insertAdjacentElement |(HTML) 방법3. insertAdjacentHTML 
				}
				
			});
		
		},
		error : console.log,
	});
};

</script>
<style>

blockquote, body, code, dd, div, dl, dt, fieldset, form, h1, h2, h3, h4, h5, h6, input, legend, li, ol, p, pre, td, textarea, th, ul {margin: 0; padding: 0;}
.my-cart-wrap{
	position: relative;
    background-color: #f5f5f5;
    margin-top: 9px;
}

body {
    font-size: 15px;
    line-height: 1;
    font-family: OhouseSans, "Noto Sans KR", "Apple SD Gothic Neo", "맑은 고딕", "Malgun Gothic", sans-serif;
    -webkit-font-smoothing: antialiased;
    letter-spacing: -0.4px;
    font-size: 13px;
}

.container {
	margin-right: auto;
    margin-left: auto;
    width: 1136px;
    max-width: 100%;
    box-sizing: border-box;
    min-height: 1px;
}
.cart{
    position: relative;
}
.row{
	display: flex;
	flex-wrap: wrap;
    box-sizing: border-box;
    margin-right: -10px;
    margin-left: -10px;
}
.cart-content-wrap {
	padding-right: 10px;
    padding-left: 10px;
    position: relative;
    width: 65%;
    min-height: 1px;
    box-sizing: border-box;
    -webkit-box-flex: 0;
}
.cart-side-wrap{
	display: block;
	padding-top: 35px;
	padding-right: 10px;
    padding-left: 10px;
}
.cart-content{
	margin: 0;
    padding: 30px 0 20px;
    position: relative;
}
.cart-header-wrap{
	z-index: 100;
}
.cart-header{
	padding: 0 21px;
    background: none;
    border: none;
    display: flex;
    align-items: center;
    justify-content: space-between;
    box-sizing: border-box;
    margin: 0 0 11px;
    z-index: 100;
}
.cart-header-left{
	flex: 0 0 auto;
	margin: -9px;
}
.cart-header-right{
	flex: 0 0 auto;
}
._3xqzr {
    display: inline-flex;
    align-items: center;
    vertical-align: middle;
    width: 100%;
}
._3xqzr:hover {
    color:black;
    font-weight: 700;
}
._4VN_z {
    flex-direction: row;
    padding-right: 6px;
}
.checkbox-css {
    position: relative;
    display: inline-block;
    font-size: 0;
    padding: 9px;
}
.all-checkbox-text {
    font-size: 15px;
}
.commerce-cart-header-caption {
    display: inline-block;
    line-height: 21px;
    margin-bottom: 2px;
}
.checkbox-shape {
    position: absolute;
    top: 10px;
    left: 10px;
    width: 50%;
    height: 50%;
    margin: 0;
    padding: 0;
    cursor: inherit;
    opacity: 1;
    box-sizing: inherit;
}
.check-inner-design {
    display: inline-block;
    width: 22px;
    height: 22px;
    box-sizing: border-box;
    padding: 2px;
    border: 1px solid #ccc;
    border-radius: 4px;
    background-color: #fff;
    color: #fff;
    font-size: 16px;
    line-height: 1;
    transition: color .1s,border-color .1s,background-color .1s;
}
.cart-header-delete {
    cursor: pointer;
    touch-action: manipulation;
	display: inline-block;
    margin: 2px -3px 0;
    padding: 3px;
    background: none;
    border: none;
    color: #424242;
    font-family: inherit;
    font-weight: 400;
    font-size: 12px;
    line-height: 1;
}
ol, ul {
    list-style: none;
}
.cart-header-delete:hover{
	color:gray;
}
ul {
    display: block;
    list-style-type: disc;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
    padding-inline-start: 10px;
}
li {
    display: contents;
    text-align: -webkit-match-parent;
}
.cart-group {
    margin-bottom: 1px;
}
.cart-grop-header {
    padding: 10px 0;
    border-bottom: 1px solid #ededed;
    font-size: 15px;
    font-weight: 500;
    line-height: 20px;
    text-align: center;
    color: #424242;
    background-color: #fff;
    border: solid #ededed;
    border-width: 1px 1px 0;
    border-radius: 6px 6px 0 0;
    padding-bottom: 12px;
}

.carted-product {
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}

.carted-product {
    border: 1px solid #ededed;
    border-radius: 6px;
    border-top-width: 2px;
}
.carted-product {
    margin-bottom: 1px;
    background-color: #fff;
}
.commerce-cart-delivery-group-footer {
    padding: 10px 0;
    font-size: 15px;
    line-height: 20px;
    text-align: center;
    color: #424242;
    background-color: white;
}
footer{
	border-bottom-left-radius: 6px;
    border-bottom-right-radius: 6px;
}

.carted-product {
    padding: 20px 15px 20px 54px;
    position: relative;
}
.carted-product-select {
    position: absolute;
    margin: -9px;
    top: 20px;
    left: 20px;
}
._3zqA8 {
    position: relative;
    display: inline-block;
    font-size: 0;
    padding: 9px;
}
._3UImz {
    position: absolute;
    top: 10px;
    left: 10px;
    width: 100%;
    height: 100%;
    margin: 0;
    padding: 0;
    cursor: inherit;
    opacity: 1;
    box-sizing: border-box;
}
.sticky-container {
    height: 30px;
}
.product-small-item__image{
	flex: 0 0 auto;
    position: relative;
    display: block;
    width: 70px;
    height: 70px;
    border-radius: 6px;
    background-color: #ededed;
    overflow: hidden;
}
.product-small-item__image img {
    display: block;
    position: absolute;
    top: 50%;
    left: 50%;
    width: 100%;
    transition: transform .2s;
    transform: translate(-50%,-50%) scale(1.0001);
}

.product-small-item--clickable:active .product-small-item__title, .product-small-item--clickable:focus .product-small-item__title, .product-small-item--clickable:hover .product-small-item__title {
    opacity: .5;
}
.product-small-item__title {
    min-width: 0;
    font-size: 15px;
    font-weight: 500;
    color: #000;
    line-height: 21px;
    overflow-wrap: break-word;
    transition: opacity .1s;
}
.product-small-item__content {
    -webkit-box-flex: 1;
    -webkit-flex: 1 0 0px;
    -moz-box-flex: 1;
    -moz-flex: 1 0 0px;
    -ms-flex: 1 0 0px;
    flex: 1 0 0px;
    padding-left: 12px;
    padding-top: 15px;
    padding-right: inherit;
}

.cart-side-wrap {
    padding-top: 35px;
    display: block;
    padding-right: 10px;
    padding-left: 10px;
    width: 30%;
}
.commerce-cart__side-bar{
	padding: 30px 0;
}
.commerce-cart__side-bar__summary {
    margin: 0 0 20px;
    border: 1px solid #ededed;
    border-radius: 6px;
    background-color: #fff;
}
.commerce-cart__summary {
    padding: 10px 20px;
}
.commerce-cart__side-bar__summary {
    margin: 0 0 20px;
    border: 1px solid #ededed;
    border-radius: 6px;
    background-color: #fff;
}

.commerce-cart__summary {
    padding: 10px 20px;
}
.commerce-cart__summary__row {
	display: flex;
	-webkit-box-align: center;
	margin: 20px 0;
	justify-content: space-between;
}
.commerce-cart__summary__row>dd {
    font-weight: 700;
    text-align: right;
}
.commerce-cart__summary__row--total {
    margin: 30px 0 20px;
    color: #000;
    display: flex;
    align-items: center;
}
.commerce-cart__summary__row--total>dt {
    font-weight: 700;
}
.commerce-cart__summary__row--total>dd {
    font-size: 24px;
}
.commerce-cart__side-bar__order__btn {
    display: block;
    width: 100%;
}
._1eWD8 {
    user-select: none;
    position: relative;
    justify-content: center;
    align-items: center;
    margin: 0;
	box-sizing: border-box;
    border: 1px solid transparent;
    background: none;
    font-family: Noto Sans KR,Noto Sans CJK KR,맑은 고딕,Malgun Gothic,sans-serif;
    font-weight: 700;
    text-decoration: none;
    text-align: center;
    border-radius: 4px;
    cursor: pointer;
}
._27do9 {
    padding: 15px 10px;
    line-height: 20px;
    font-size: 17px;
    min-height: 50px;
}
._3SroY {
    background-color: #35c5f0;
    border-color: #35c5f0;
    color: #fff;
}
.commerce-cart__side-bar__order:hover{
	font-weight: bold;
}
a {
    color: inherit;
    text-decoration: none;
}
.carted-product .product-small-item {
    margin: 0 20px 12px 0;
}
.carted-product__delete {
    position: absolute;
    display: inline-block;
    top: 15px;
    right: 15px;
    padding: 5px;
    background: none;
    border: none;
    font-size: 0;
    transition: opacity .1s;
    color: #424242;
}
.carted-product__delete:hover{
	cursor: pointer;
}
.carted-product__footer {
    display: flex;
    justify-content: space-between;
    margin-top: 16px;
}
.carted-product__footer__left {
    flex: 0 0 auto;
}
.carted-product__subtotal {
    flex: 0 0 auto;
    color: #000;
    font-size: 17px;
    font-weight: 700;
}
.carted-product__order-btn {
    position: relative;
    display: inline-block;
    margin: 2px 0 0 -3px;
    padding: 3px;
    background: none;
    border: none;
    color: #424242;
    font-family: inherit;
    font-weight: 400;
    font-size: 12px;
    line-height: 1;
    transition: opacity .1s;
}
.carted-product__order-btn:hover {
	font-style: italic;
	cursor: pointer;
}
.commerce-cart-empty {
    background-color: #f5f5f5;
    display: flex;
    -webkit-box-direction: normal;
    -webkit-box-orient: vertical;
    flex-direction: column;
    -webkit-box-pack: center;
    justify-content: center;
}
.commerce-cart-empty__content {
    -webkit-box-flex: 0;
    flex: 0 0 auto;
    width: 100%;
    max-width: 220px;
    margin: 80px auto;
    box-sizing: border-box;
    text-align: center;
}
.commerce-cart-empty__content {
    -webkit-box-flex: 0;
    flex: 0 0 auto;
    width: 100%;
    max-width: 220px;
    margin: 80px auto;
    box-sizing: border-box;
    text-align: center;
}
.commerce-cart-empty__content__image {
    display: block;
    max-width: 160px;
    margin: 0 auto;
}
.commerce-cart-empty__content__button.button {
    display: block;
    margin-top: 30px;
}
.button--size-50 {
    padding: 11px 10px;
    font-size: 17px;
    line-height: 26px;
}

.button--color-blue {
    background-color: #35c5f0;
    border-color: #35c5f0;
    color: #fff;
}
</style>

<% 
	if(cartList.size() > 0) {
%>
<div class="my-cart-wrap" id ="product">
	<div class="container">
		<div class="cart row">
			<div class="cart-content-wrap">
				<div class="cart-content">
					<div class="sticky-container cart-header-wrap">
						<div class="sticky-child cart-header">
							<span class="cart-header-left">
								<label class="_3xqzr _4VN_z">
									<div class="checkbox-css">
										<input type="checkbox" class="checkbox-shape" id="all-check" value="" checked>
											<span class="check-inner-design">
												<svg width="1em" height="1em" viewBox="0 0 16 16" class="_2UftR">
													<path fill="currentColor" d="M6.185 10.247l7.079-7.297 1.435 1.393-8.443 8.703L1.3 8.432l1.363-1.464z">
													</path>
												</svg>
											</span>
									</div>
									<span class="all-checkbox-text">
										<span class="commerce-cart-header-caption">모두선택</span>
									</span>
								</label>
							</span>
							<span class="cart-header-right">
								<button class="cart-header-delete" type="button">선택삭제</button>
							</span>
						</div>
					</div>
<%
						for(int i = 0; i < cartList.size(); i++){
							int totalPrice = 0;
							int productCnt = 0;
							int price = 0;
							String productId = cartList.get(i).getProductId();
							String brandName = "";
							productCnt = cartList.get(i).getProductCount();
							for(int j = 0; j < productList.size() ; j++){
								if(cartList.get(i).getProductId().equals(productList.get(j).getProductId())){
									price = productList.get(j).getProductPrice();
									brandName = productList.get(j).getBrandId();
								}
							}
							totalPrice = price * productCnt;
%>
					<ul class="cart-content-group-list">
						<li class="cart-content-group-item">
							<article class="cart-group">
								<h1 class="cart-grop-header"><%= brandName %></h1>
									<article class="carted-product">
										<div class="carted-product-select">
											<div class="_3zqA8">
												<input type="checkbox" class="_3UImz" name="innerCheck" id="<%= i %>" checked="" value="<%= cartList.get(i).getProductId() %>" >
													<span class="_2mDYR">
														<svg width="1em" height="1em" viewBox="0 0 16 16" class="_2UftR">
															<path fill="currentColor" d="M6.185 10.247l7.079-7.297 1.435 1.393-8.443 8.703L1.3 8.432l1.363-1.464z">
															</path>
														</svg>
													</span>
											</div>
										</div>
										<a class="product-small-item product-small-item--clickable" href="/productions/1240953/selling">
											<div class="product-small-item__image" id="photo-container">
												<picture>
													<img alt="" src="" srcset="">
												</picture>
											</div>
												<div class="product-small-item__content">
													<h1 class="product-small-item__title" ><%= cartList.get(i).getProductId() %></h1>
													<p class="css-w0e4y9 e1xep4wb0">무료배송<!-- -->&nbsp;|&nbsp;<!-- -->화물택배<!-- -->&nbsp;&nbsp;<!-- --><p>수량 : <%= cartList.get(i).getProductCount() %></p><p>단가 : <%= price %></p><input type="hidden" id="count<%= i %>" value="<%= productList.get(i).getProductPrice() %>" />
												</div> 
											</a>
											<button class="carted-product__delete" type="button" aria-label="삭제">
												<svg width="12" height="12" viewBox="0 0 12 12" fill="currentColor" preserveAspectRatio="xMidYMid meet">
													<path fill-rule="nonzero" d="M6 4.6L10.3.3l1.4 1.4L7.4 6l4.3 4.3-1.4 1.4L6 7.4l-4.3 4.3-1.4-1.4L4.6 6 .3 1.7 1.7.3 6 4.6z"></path>
												</svg>
											</button>
											<input type="hidden" value="<%= productId %>"/>
										<div class="carted-product__footer">
											<span class="carted-product__footer__left">
												<button class="carted-product__order-btn" type="button" onclick="purchaseOne();">바로구매</button>
												<input type="hidden" value="<%= totalPrice %>"/>
											</span>
											<span class="carted-product__subtotal">
												<span class="carted-product__subtotal__number" name="gulDel" id="price<%= i %>"><%= totalPrice %></span>원
											</span>
										</div>
									</article>
							</article>
						</li>
						<footer class="commerce-cart-delivery-group-footer">
							<p class="commerce-cart__delivery-group-total">배송비<!-- --> 무료</p>
						</footer>
					</ul>
					<%
						}
					%>
				</div>
			</div>
			<div class="cart-side-wrap">
				<div class="sticky-container commerce-cart__side-bar-container" style="position: sticky; top: 81px; transition: top 0.1s ease 0s;">
					<div class="sticky-child commerce-cart__side-bar" style="position: relative;">
						<dl class="commerce-cart__summary commerce-cart__side-bar__summary">
							<div class="commerce-cart__summary__row">
								<dt>총 상품금액</dt>
									<dd>
										<span class="number" id="priceAll"></span>원
									</dd>
							</div>
							<div class="commerce-cart__summary__row">
								<dt>총 배송비</dt>
									<dd>+ 
										<span class="number">0</span>원
									</dd>
							</div>
							<div class="commerce-cart__summary__row commerce-cart__summary__row--total">
								<dt>결제금액</dt>
									<dd>
										<span class="number" id="totalPrice"></span>원
									</dd>
							</div>
						</dl>
						<div class="commerce-cart__side-bar__order">
							<button id="choicePurchase" class="_1eWD8 _3SroY _27do9 commerce-cart__side-bar__order__btn" type="button" onclick="purchaseTotal();">구매하기</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%
	}  else {
%>
		<div class="commerce-cart-empty">
			<div class="commerce-cart-empty__content">
				<img class="commerce-cart-empty__content__image" src="https://image.ohou.se/i/bucketplace-v2-development/uploads/assets/163703569663018673.png" alt="장바구니가 비었습니다.">
					<a class="button button--color-blue button--size-50 button--shape-4 commerce-cart-empty__content__button" href="<%= request.getContextPath() %>/community/home">상품 담으러 가기</a>
			</div>
		</div>
<%
			}
%>
<form name="productOnePurchaseFrm" id="productOnePurchaseFrm" action="<%= request.getContextPath() %>/purchase/cartPurchaseOne">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="productPrice" />
</form>
<form name="purchaseChoiceFrm" id="purchaseChoiceFrm" action="<%= request.getContextPath() %>/purchase/paywayInfo">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="totalPrice" />
</form>

<script>

// 화면 로드시 전체버튼 클릭된 상태하 전체가격으로 시작
window.onload = function(){
	let totalPrice = 0;
	let total = $("input[type=checkbox]").length - 1;
	for(let i = 0; i < total; i++){
		const id = "price" + (i).toString();
		totalPrice += parseInt(document.getElementById(id).innerText);
	}
	$("#totalPrice").html(totalPrice);
	$("#priceAll").html(totalPrice);
};

// 전체선택 버튼구현
$("#all-check").click(function(){
	console.log($("#all-check").is(":checked"));
	// 전체체크
	if($("#all-check").is(":checked")){
		$("input[type=checkbox]").prop("checked", true);
	}
	else	
		$("input[type=checkbox]").prop("checked", false);
	
	// 전체가격
	let totalPrice = 0;
	let total = $("input[type=checkbox]").length - 1;
	if($("#all-check").is(":checked")){
		for(let i = 0; i < total; i++){
			const id = "price" + (i).toString();
			console.log(id);
			totalPrice += parseInt(document.getElementById(id).innerText);
		}
		$("#totalPrice").html(totalPrice);
		$("#priceAll").html(totalPrice);
	} else {
		totalPrice = 0;
		$("#totalPrice").html(totalPrice);
		$("#priceAll").html(totalPrice);
	}
}); 

//부분체크시 전체체크 해제
$("input[name=innerCheck]").click(function() {
	var selectedIndex = $('input:checkbox').index($(this)); // 인덱스번호 찾기
	let total = $("input[name=innerCheck]").length;
	let checked = $("input[name=innerCheck]:checked").length;
	if(total != checked)
		$("#all-check").prop("checked", false);
	else 
		$("#all-check").prop("checked", true);
	const id = "price" + (selectedIndex-1).toString();
	// 선택된 체크박스의 가격만 합계구하기
	let totalPrice = parseInt(document.getElementById("totalPrice").innerText); // 로드된 전체선택의 전체가격
	
	if(!$(this).is(":checked")){
		totalPrice -= parseInt(document.getElementById(id).innerText); // 클릭해제시 지우기
		console.log(totalPrice);
	}
	else{
		totalPrice += parseInt(document.getElementById(id).innerText); // 클릭시 더하기
		console.log(totalPrice);
	}	
	$("#totalPrice").html(totalPrice);
	$("#priceAll").html(totalPrice);
});	


//선택삭제
$(".cart-header-delete").click(function(){
	if($("input[type=checkbox]").is(":checked")){
		if(confirm("선택한 상품을 삭제하시겠습니까?") == true){
			const productIdArr = new Array();
			if($("input[type=checkbox]").is(":checked"))
				$("input[type=checkbox]:checked").each(function(){
					productIdArr.push($(this).val());
					console.log($(this).val());
				});
				
				jQuery.ajaxSettings.traditional = true; // 배열을 전달할때 세팅 꼭 해줘야한다!!
				
				// 비동기 처리
			    $.ajax({
				    url : "<%= request.getContextPath() %>/cart/cartDelete",
				    type : "POST",
				    data : { 
				    	memberId : '<%= memberId %>',
				    	productId : productIdArr
				    	},
				    success : function(response){
				    	location.reload();
				    }
			 	  });
		} 
	}else {
		alert("상품을 선택해주세요.");
		return false;
	}
});

// x버튼으로 상품삭제
$(".carted-product__delete").click(function(event){
	const productId = $(this).next().val();
	if(confirm("이 상품을 삭제하시겠습니까?") == true){
		alert("삭제되었습니다.");
		jQuery.ajaxSettings.traditional = true; // 배열을 전달할때 세팅 꼭 해줘야한다!!
		
		console.log(productId);
		// 비동기 처리
	    $.ajax({
		    url : "<%= request.getContextPath() %>/cart/cartDelete",
		    type : "POST",
		    data : { 
		    	memberId : '<%= memberId %>',
		    	productId : productId
		    	},
		    success : function(response){
		    	location.reload();
		    }
 	  });
	} else {
		return false;
	}

});

//바로 구매하기
const purchaseOne = () => {
	let price = $(".carted-product__order-btn").next().val();
	const memberId = "<%= memberId %>";
	console.log(price);
	const frm = document.productOnePurchaseFrm;
	frm.memberId.value = memberId;
	frm.productPrice.value = price;
	frm.submit();
};

//선택 구매하기
const purchaseTotal = () => {
	let totalPrice = parseInt(document.getElementById("totalPrice").innerText);
	const memberId = "<%= memberId %>";
	const frm = document.purchaseChoiceFrm;
	frm.totalPrice.value = totalPrice;
	console.log(totalPrice);
	frm.memberId.value = memberId;
	console.log(memberId);
	frm.submit();
};
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>