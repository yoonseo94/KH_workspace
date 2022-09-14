<%@page import="com.meshop.product.entity.ProductStatus"%>
<%@page import="com.meshop.product.entity.ProductExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<% 
	List<ProductExt> list = (List<ProductExt>) request.getAttribute("productList");
	String pagebar = (String) request.getAttribute("pagebar");
	String category = (String) request.getAttribute("category");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/productList.css">
<main>
    <section id="categorySection">
        <div id="categoryToggle">
            <span>카테고리</span>
            <span id="add">+</span>
        </div>
        <span class="categoryList" id="top" onclick="location.href='<%= request.getContextPath() %>/product/productListByCategory?category=top'">상의</span>
        <span class="categoryList" id="bottom" onclick="location.href='<%= request.getContextPath() %>/product/productListByCategory?category=bottom'">하의</span>
        <span class="categoryList" id="shoes" onclick="location.href='<%= request.getContextPath() %>/product/productListByCategory?category=shoes'">신발</span>
        <span class="categoryList" id="stuff" onclick="location.href='<%= request.getContextPath() %>/product/productListByCategory?category=stuff'">잡화</span>
        <span class="categoryList" id="luxury" onclick="location.href='<%= request.getContextPath() %>/product/productListByCategory?category=luxury'">럭셔리</span>
    </section>
    <section id="listSection">
        <section id="listSection-toggle">
            <!-- 새상품, 내동네 toggle -->
            <div>
                <input type="checkbox" name="statusToggle" id="statusToggle">
                <label for="statusToggle">
                    <div class="toggleBtn">
                        <span class="onoff"></span>
                    </div>
                    <span class="content">새 상품</span>
                </label>
                <input type="checkbox" name="placeToggle" id="placeToggle">
                <label for="placeToggle">
                    <div class="toggleBtn">
                        <span class="onoff"></span>
                    </div>
                    <span class="content">내 동네</span>
                </label>
            </div>
            <!-- 조회조건 리스트 -->
            <div>
                <select name="viewCondition" id="viewCondition">
                    <option value="orderByDay" selected>최신순&nbsp;&nbsp;</option>
                    <option value="orderByPrice">가격순</option>
                </select>
            </div>
        </section>
        <section id="listSection-list">
       	<% 
       		if(list != null && !list.isEmpty()) {
       			int index = 0;
        		for(int i = 0; i < (int) Math.ceil((double) list.size() / 4); i++) {
       	%>
			<div class="productSection">
				<% 
					for(int j = 0; j < 4; j++) {
				%>
    			<div class="productBox">
					<% if(loginMember != null && wishList.contains(list.get(index).getProductId())){ %>
		            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=list.get(index).getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-solid fa-heart"></i></button>
		            <% }else if(loginMember != null){ %>
		            	<button class="wish-btn" onclick="wishBtnEvent(this,'<%=list.get(index).getProductId()%>','<%= loginMember.getMemberId() %>')"><i class="fa-regular fa-heart"></i></button>
		            <% }else{ %>
		            	<button class="wish-btn" onclick="location.href='<%=request.getContextPath() %>/login';"><i class="fa-regular fa-heart"></i></button>
		            <% } %>
        			<a class="productLink" href="<%= request.getContextPath() %>/product/productView?productId=<%= list.get(index).getProductId() %>">
	           			<div class="productImage" style="background-image: url('<%= request.getContextPath() %>/images/<%= list.get(index).getAttachment().getRenamedFilename() %>'); background-size: cover;">           

						</div>
						<div class="productInfo">
	    					<div class="list-brand"><%= list.get(index).getBrand() %></div>
							<div class="list-title"><%= list.get(index).getStatus() == ProductStatus.N ? "[새상품] " : "" %> <%= list.get(index).getTitle() %></div>
							<div class="list-price"><%= df.format(list.get(index).getPrice()) %>원</div>
							<div class="list-other">
	    						<span class="list-place">서울시 <%= list.get(index).getPlace() %></span>
								<span class="list-date"><%= list.get(index).getRegDate() %></span>
	             			</div>
	         			</div>
     				</a>
				</div>
				<%
						index++;
						if(index == list.size()) break;
					}
				%>
			</div>
		<% 
				}
			} 
		%>
        </section>
            <div id="pagebar">
            	<%= pagebar %>
            </div>
        </section>
    </section>
</main>
<script>
// 새 상품, 내 동네 toggle
document.querySelectorAll('#listSection-toggle input').forEach((input) => {
	input.addEventListener('change', (e) => {
	<%
		if(loginMember != null) {
	%>
		$.ajax({
			url:"<%=request.getContextPath() %>/product/productSortToggle",
            data:{
            	place : "<%= loginMember.getPlace() %>",
            	category : "<%= category %>",
                statusToggle : statusToggle.checked,
                placeToggle : placeToggle.checked
            },
            method : "GET",
            success(response){
            	const listSection = document.querySelector('#listSection-list');
            	listSection.innerHTML = "";
            	let index = 0;
            	if(response.list !== null && response.list.length !== 0) {
	            	for(let i = 0; i < Math.ceil(response.list.length / 4); i++) {
	            
		            	const productSection = document.createElement('div');
		            	productSection.classList.add('productSection');
            		
		            	for(let j = 0; j < 4; j++) {
		            		const productBox = document.createElement('div');
		            		productBox.classList.add('productBox');
		            		
		            		const productLink = document.createElement('a');
		            		productLink.classList.add('productLink');
		            		productLink.href = `<%= request.getContextPath() %>/product/productView?productId=\${response.list[index].productId}`;
		            		
		            		const productImage = document.createElement('div');
		            		productImage.classList.add('productImage');
		            		productImage.style = `background-image: url('<%= request.getContextPath() %>/images/\${response.list[index].attachment.renamedFilename}'); background-size: cover;`;
		            		
			            	const productInfo = document.createElement('div');
			            	productInfo.classList.add('list-productInfo');
			            	
			            	const brand = document.createElement('div');
			            	brand.classList.add('list-brand');
			            	brand.textContent = response.list[index].brand;
			            	
			            	const title = document.createElement('div');
			            	title.classList.add('list-title');
			            	
			            	let status = '';
			            	if(response.list[index].status === 'N') status = '[새상품] ';
			            	title.textContent = status + response.list[index].title;
			            	
			            	const price = document.createElement('div');
			            	price.classList.add('list-price');
			            	price.textContent = fr(response.list[index].price) + "원";
			            	
			            	const other = document.createElement('div');
			            	other.classList.add('list-other');
			            	
			            	const place = document.createElement('span');
			            	place.classList.add('list-place');
			            	place.textContent = "서울시 " + response.list[index].place;
			            	
			            	const date = document.createElement('span');
			            	date.classList.add('list-date');
			            	date.textContent = response.list[index].regDate;
			            	
			            	other.append(place, date);
			            	productInfo.append(brand, title, price, other);
			            	productLink.append(productImage, productInfo);
			            	productBox.append(productLink);
			            	productSection.append(productBox);
			            	index++;
			            	if(index === response.list.length) break;
		            	}
	            		listSection.append(productSection);
            		}
            	}
            	const pagebar = document.querySelector('#pagebar');
            	pagebar.innerHTML = response.pagebar;
            	console.log(response.pagebar);
            },
            error:console.log
			});
	<% } else { %>
		alert('로그인이 필요합니다.');
		e.target.checked = '';
	<%	} %>
	});

});
const fr = number => number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');

document.querySelectorAll('#categoryToggle span').forEach((span) => {
    span.addEventListener('click', () => {
        if(categorySection.classList.contains('on')) {
            categorySection.classList.remove('on');
            add.innerHTML = "+";
        } else {
            categorySection.classList.add('on');
            add.innerHTML = "-";
        }
    })
});

<% if(category != null) { %>
window.onload = () => {
	categorySection.classList.add('on');
	add.innerHTML = '-';
}
document.querySelectorAll('.categoryList').forEach((span) => {
    if(span.id === "<%= category %>") span.style.fontWeight = "bold";
});
<% } %>


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
};

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>
