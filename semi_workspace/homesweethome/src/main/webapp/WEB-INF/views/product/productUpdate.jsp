<%@page import="product.model.dto.ProductDescriptionImage"%>
<%@page import="product.model.dto.ProductBrand"%>
<%@page import="product.model.dto.ProductSubCode"%>
<%@page import="product.model.dto.ProductMainCode"%>
<%@page import="product.model.dto.ProductImage"%>
<%@page import="java.util.List"%>
<%@page import="product.model.dto.ProductExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>   
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %> 
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/product/productUpdate.css" />
<% 
	ProductExt product = (ProductExt) request.getAttribute("product");
	List<ProductMainCode> mainCodeList = (List<ProductMainCode>)request.getAttribute("productMainCodes");
	List<ProductSubCode> subCodeList = (List<ProductSubCode>) request.getAttribute("productSubCodes");
	List<ProductBrand> brandList = (List<ProductBrand>) request.getAttribute("productBrandIds");
	
	String searchTypeMain = request.getParameter("searchTypeMain");
	String searchTypeSub = request.getParameter("searchTypeSub");
%>
<script>
/**
* productEnrollFrm 유효성 검사
*/
window.onload = () => {	
	document.productUpdateFrm.onsubmit = (e) => {
		const frm = e.target;
		
		const productIdVal = frm.productId.value.trim(); // 좌우공백
		if(!/^.+$/.test(productIdVal)){
			alert("상품 아이디를 작성해주세요.");
			frm.productId.select();
			return false;
		}		
		
		const productNameVal = frm.productName.value.trim(); // 좌우공백
		if(!/^.+$/.test(productNameVal)){
			alert("상품명을 작성해주세요.");
			frm.productName.select();
			return false;
		}		
		
		const mainCodeVal = frm.mainCode.selected; 
		if(!"selected".equalse(mainCodeVal)){
			alert("대분류를 선택해주세요.");
			frm.mainCode.select();
			return false;
		}		
		
		const subCodeVal = frm.subCode.selected; 
		if(!"selected".equalse(subCodeVal)){
			alert("소분류를 선택해주세요.");
			frm.subCode.select();
			return false;
		}		
		
		const brandVal = frm.brandId.selected; // 좌우공백
		if(!"selected".equalse(brandVal)){
			alert("브랜드를 선택해주세요.");
			frm.brandId.select();
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
		
		const productColorVal = frm.productColor.value.trim(); // 좌우공백
		if(!/^.+$/.test(productColorVal)){
			alert("상품 색상을 작성해주세요.");
			frm.productColor.select();
			return false;
		}		
		
		const productPriceVal = frm.productPrice.value.trim(); // 좌우공백
		if(!/^.+$/.test(productPriceVal)){
			alert("상품 가격을 작성해주세요.");
			frm.productPrice.select();
			return false;
		}		
		

		const contentVal = frm.content.value.trim();
		if(!/^(.|\n)+$/.test(contentVal)){
			alert("내용을 작성해주세요.");
			frm.content.select();
			return false;
		}
	}
}

</script>
<section id="product-update-container">
	<div id="title-wrapper">
		<h2 id="cont-title">상품 수정</h2>	
	</div>
<hr class="hr1" />	
<form
	id="tbl-product-update"
	name="productUpdateFrm"
	action="<%=request.getContextPath() %>/product/productUpdate" 
	method="POST"
	enctype="multipart/form-data">
	<table id="tbl-board-view">
	<tr>
		<th>상품 아이디</th> 
		<td><input  class="tbl-input" type="text" name="productId" value="<%=product.getProductId() %>" required></td>
	</tr> 
	<tr>
	<th>상품명</th>
		<td><input class="tbl-input"  type="text" name="productName" value="<%=product.getProductName() %>" required></td>
	</tr>
		<th>대분류</th>
		<td>
			<select id="searchTypeMain">
	            	<option value="main-category" selected>대분류</option>
				<% if(mainCodeList != null && !mainCodeList.isEmpty()){
					for(ProductMainCode maincode : mainCodeList){ 
%>		
	            	<option value="<%= maincode.getProductMainCode() %>" class="<%= maincode.getProductMainCode() %>" <%=maincode.getProductMainCode().equals(searchTypeMain) ? "selected" : ""%>><%= maincode.getProductMainName() %></option>
<%
					}
				}
%>
      		</select>
		</td>
	</tr>
	<tr>
		<th>소분류</th>
		<td>
			<select id="searchTypeSub">
				<option value="subCategory" id="subCateOpt" selected>소분류</option>
      		</select>
		</td>
	</tr>
	<tr>
		<th>브랜드명</th>
		<td><input class="tbl-input" id="input-brand"  name="brandId" required autofocus></td>
	</tr>
	<tr>
		<th>높이</th>
		<td><input class="tbl-input"  type="text" name="productHeight" value="<%=product.getProductHeight() %>" required></td>
	</tr>
	<tr> 
		<th>너비</th>
		<td><input class="tbl-input"  type="text" name="productWidth" value="<%=product.getProductWidth() %>" required></td>
	</tr>
	<tr>
		<th>깊이</th>
		<td><input class="tbl-input"  type="text" name="productDepth" value="<%=product.getProductDepth() %>" required></td>
	</tr>
	<tr>
		<th>색상</th>
		<td><input class="tbl-input"  type="text" name="productColor" value="<%=product.getProductColor() %>" required></td>
	</tr>
	<tr>
		<th>가격</th>
		<td><input class="tbl-input"  type="text" name="productPrice" value="<%=product.getProductPrice() %>" required/></td>
	</tr>
	<tr>
		<th>상품 대표 이미지 파일</th>
		<td>
		<%
		List<ProductImage> productImages = product.getProductImages();
		if(productImages != null && !productImages.isEmpty()){
			for(int i = 0; i < productImages.size(); i++ ){
				ProductImage img = productImages.get(i);
				System.out.println(img.getOriginalFilename());
	%>
			<img src="<%= request.getContextPath() %>/upload/product/<%=img.getRenamedFilename() %>" width="12px" alt="첨부파일 이미지" />
			<%= img.getOriginalFilename() %> 
			<input type="checkbox" name="delFile" id="delFile<%= i + 1 %>" value="<%= img.getNo() %>" />
			<label for="delFile<%= i + 1 %>">삭제</label>
			<br />
	<%
		}
		}
	%>					
			<input type="file" name="upFile1" >
			<br>
			<input type="file" name="upFile2" >
			<br>
			<input type="file" name="upFile3" >
		</td>
	</tr>
	<tr>
		<th>상품 설명 이미지 파일</th>
		<td>
		<%
		List<ProductDescriptionImage> productDescriptionImages = product.getProductDescriptionImages();
		if(productDescriptionImages != null && !productDescriptionImages.isEmpty()){
			for(int i = 0; i < productDescriptionImages.size(); i++ ){
				ProductDescriptionImage desImg = productDescriptionImages.get(i);
				System.out.println(desImg.getOriginalFilename());
	%>
			<img src="<%= request.getContextPath() %>/upload/product/<%=desImg.getRenamedFilename() %>" width="12px" alt="첨부파일 이미지" />
			<%= desImg.getOriginalFilename() %> 
			<input type="checkbox" name="delDesFile" id="delDesFile i + 1 %>" value="<%= desImg.getNo() %>" />
			<label for="delDesFile<%= i + 1 %>">삭제</label>
			<br />
	<%
		}
		}
	%>					
			<input type="file" name="desFile1" >
			<br>
			<input type="file" name="desFile2" >
			<br>
			<input type="file" name="desFile3" >
			<br>
			<input type="file" name="desFile4" >
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea id="text" rows="5" cols="40" name="content" value="<%=product.getPContent() %> "></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" id="enroll-update-btn" value="수정하기">
		</th>
	</tr>
</table>
<input type="hidden" name="mainCode" value="<%=product.getMainCode() %>"/>
<input type="hidden" name="subCode" value="<%=product.getSubCode() %>"/>
</form>
</section>
<script>

searchTypeMain.addEventListener('change', (e) => {
	document.querySelector("#searchTypeSub").innerHTML = "";
	document.querySelector("#searchTypeSub").innerHTML = "<option value='subcategory'>소분류</option>";
	const {value} = e.target;  
	document.querySelector("[name=mainCode]").value = value;

	let id = "";
	
	switch(value){
		case "furniture": id = "furniture"; break; 
		case "electroics": id = "electroics"; break; 
		case "lighting": id = "lighting"; break; 
	}
	
	let ValMainCode = "";
	let html = "";
<% if(subCodeList != null && !subCodeList.isEmpty()){ 
	for(int i = 0; i < subCodeList.size(); i++) { 	
			String subMainCode = subCodeList.get(i).getProductMainCode();
			String subSubCode = subCodeList.get(i).getProductSubCode();
			String subName = subCodeList.get(i).getProductSubName(); 	
%>
		ValMainCode = "<%= subMainCode %>";
			if(value === ValMainCode){
					html = `<option name="subOpt" value="<%= subSubCode %>" id="<%= subSubCode %>" class="<%= subMainCode %>" <%= subSubCode.equals(searchTypeSub) ? "selected" : ""%>><%= subName %></option>`;	
					document.querySelector("#searchTypeSub").innerHTML += html;						
			}
	<%	} %>
		
	<% } %>
 
});

searchTypeSub.addEventListener('change', (e) => {
	const {value} = e.target;  
	document.querySelector("[name=subCode]").value = value;
}); 

$("#input-brand").autocomplete({
    source(request, response){
  	  $.ajax({
  		  url: "<%=request.getContextPath()%>/product/findAutoComplete",
  		  method: "GET",
  		  data : {
  			  search : request.term
  		  },
  		  dataType : "text",
  		  success(resp){
  		  	const brandName = resp.split(",");
  		  	response(
  		  		brandName.map((brand) => ({
  		  			label : brand,
  		  			value : brand
  		  		}))
  		  	);
  		  },
  		  error : console.log
  	  })
    },
    minLength: 1,
    delay: 500,
    focus(e, item){
  	  return false; // 기본 작동 해제	
    },
    select(e, {item}){
  	  // 선택했을 때, 처리코드
  	  let indexEnd = item.value.lastIndexOf('_');
  	  let indextStart;
  	  item.value = item.value.slice(indextStart, indexEnd);
  	  alert(item.value);
  	  document.querySelector("#input-brand").value = item.value;
    }
    
  });
  
const len = document.querySelectorAll("[name=delFile]").length;
for(let i = 0; i < len; i++){
	 document.querySelectorAll("input[type=file]")[i].style.display = "none";

}
document.querySelectorAll("[name=delFile]").forEach((delFile) => {
	delFile.onchange = (e) => {
		const {id, checked} = e.target;
		console.log(id, checked);
		const n = id.replaceAll(/[^0-9]/g, "");
		const file = document.querySelector(`[name=upFile\${n}]`);
		file.style.display = checked ? "inline" : "none";
		checked || (file.value = ""); //지정한 파일을 제거
	};
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>