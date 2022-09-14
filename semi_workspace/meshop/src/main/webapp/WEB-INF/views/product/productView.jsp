<%@page import="com.meshop.product.entity.Attachment"%>
<%@page import="com.meshop.product.entity.ProductStatus"%>
<%@page import="com.meshop.product.entity.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	ProductExt product = (ProductExt) request.getAttribute("product");
	List<Attachment> attachList = product.getAttachments();
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/productView.css">
<section id="headerr">
    <section id="imgSection">
    	<div class="productImg" style="background-image: url('<%= request.getContextPath() %>/images/<%= product.getAttachment().getRenamedFilename() %>'); background-size: cover;">
        	<input type="radio" name="slide" id="slide">
	        <div class="bullets">
	        	<label for="slide">&nbsp;</label>
	        </div>
        </div>
    </section>
    <section id="infoSection">
        <div class="view-brand"><%= product.getBrand() %></div>
        <div class="view-title"><%= product.getStatus() == ProductStatus.N ? "[새상품] " : ""%><%= product.getTitle() %></div>
        <div class="view-price">
            <div><%= df.format(product.getPrice()) %>&nbsp;원</div>
            <button onclick="location.href=''">신고하기</button>
        </div>
        <div class="view-btn">
            <% if(loginMember != null && wishList.contains(product.getProductId())){ %>
            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=product.getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-solid fa-heart"></i></button>
            <% }else if(loginMember != null){ %>
            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=product.getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-regular fa-heart"></i></button>
            <% }else{ %>
            	<button class="wish-btn" onclick="location.href='<%=request.getContextPath() %>/login';"><i class="fa-regular fa-heart"></i></button>
            <% } %>
            <% if(loginMember == null){ %>
            	<button id="buyBtn" onclick="location.href='<%=request.getContextPath()%>/login';">구매하기</button>
            <% }else{ %>
            	<button id="buyBtn" onclick="addBtnEvent('<%=product.getProductId() %>','<%=product.getMember().getMemberId() %>','<%=loginMember.getMemberId()%>');">구매하기</button>
            <% } %>
        </div>
        <div class="date-place">
            <div>등록일</div>
            <span id="date"><%= product.getRegDate() %></span>
        </div>
        <div class="date-place">
            <div>거래지역</div>
            <span id="view-place">서울시&nbsp;<%= product.getPlace() %></span>
        </div>
        <div class="contentBox">
            <div class="boxTitle">상품정보</div>
            <div id="view-content"><%= product.getContent() %></div>
        </div>
        <div class="storeBox">
            <div class="boxTitle">상점정보</div>
            <div>
                <img src="<%= request.getContextPath() %>/images/user.png" alt="">
                <a href="<%= request.getContextPath() %>/"><%= product.getMember().getStoreName() %></a>
                <div id="starScore"></div>
            </div>
        </div>
    </section>
</section>
<script>
const inputs = Array.from(document.querySelectorAll('input[name=slide]'));
const labels = Array.from(document.querySelectorAll('.bullets label'));

window.onload = () => {
    inputs[0].checked = 'checked';
    labels[0].style.backgroundColor = '#fff';
}

inputs.forEach((input) => {
    input.addEventListener('change', (e) => {
        labels.forEach((label) => {
            label.style.backgroundColor = 'rgba(129, 128, 128, 0.55)'
        });
        for(let i = 0; i < inputs.length; i++) {
            if(inputs[i].checked) labels[i].style.backgroundColor = '#fff';
        }
    });
});
const addBtnEvent = (productId, sellerId, buyerId)=>{
	//구매 버튼 -> 채팅연결
	console.log(productId, sellerId, buyerId);
	const form = document.createElement('form');
	
	const input1 = document.createElement('input');
    input1.setAttribute('type', 'hidden');
    input1.setAttribute('name', 'productId');
    input1.setAttribute('value', productId);

	form.appendChild(input1);
	
	const input2 = document.createElement('input');
    input2.setAttribute('type', 'hidden');
    input2.setAttribute('name', 'sellerId');
    input2.setAttribute('value', sellerId);

	form.appendChild(input2);
	
	const input3 = document.createElement('input');
    input3.setAttribute('type', 'hidden');
    input3.setAttribute('name', 'buyerId');
    input3.setAttribute('value', buyerId);

	form.appendChild(input3);
	form.setAttribute('method', 'post');
    form.setAttribute('action', '<%=request.getContextPath()%>/chat');

	document.body.appendChild(form);
    form.submit();
};
const wishBtnEvent = (e, productId, memberId) =>{
	if(e.innerHTML === '<i class="fa-regular fa-heart"></i>'){
        //빈 하트이면
        $.ajax({
            url:"<%=request.getContextPath() %>/wish/addWish",
            data:{
            	
                memberId : memberId,
                productId : productId
            },
            method : "POST",
            success(response){
            	//꽉 찬 하트로 변경
            	console.log("찜하기 성공");
                e.innerHTML = '<i class="fa-solid fa-heart"></i>';
            },
            error:console.log
        });
	}else{
        //찬 하트이면
        $.ajax({
            url:"<%=request.getContextPath() %>/wish/delWish",
            data:{
                memberId : memberId,
                productId : productId
            },
            method : "POST",
            success(response){
            	//빈 하트로 변경
            	console.log("찜하기 해제");
                e.innerHTML = '<i class="fa-regular fa-heart"></i>';
            },
            error:console.log
        });
    }
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>