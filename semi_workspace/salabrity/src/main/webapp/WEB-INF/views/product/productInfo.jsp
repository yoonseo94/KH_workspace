<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="product.model.dto.ProductExt"%>
<%@ page import="product.model.dto.Thumbnail"%>
<%@ page import="java.util.List"%>
<%@ page import="common.utill.Methods"%>
<%@ page import= "java.text.DecimalFormat" %>
<%@ page import="product.model.dto.ProductAttach"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	String defaultDate = new Methods().getDefaultFilstShippingDate();
	ProductExt product = (ProductExt) request.getAttribute("product");
	List<ProductAttach> attachments= product.getAttachs();
	ProductAttach thumbnailImg = null;
	ProductAttach detailImg1 = null;
	ProductAttach detailImg2 = null;
	for(ProductAttach attachment : attachments){
		if(attachment.getThumbnail() == Thumbnail.Y)
			thumbnailImg = attachment;
		else if (attachment.getThumbnail() == Thumbnail.N1)
			detailImg1 = attachment;
		else if (attachment.getThumbnail() == Thumbnail.N2)
			detailImg2 = attachment;
	}
	DecimalFormat df = new DecimalFormat("#,###");
%>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/product/product_info.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>

<div class="gds_wrp">
	<img src="<%= request.getContextPath() %>/upload/product/<%= thumbnailImg.getRenamedFileName()%>" alt="<%= thumbnailImg.getOriginalFileName() %>" class="prd_img" />
	<div class="prd_info">
	<form action="<%=request.getContextPath()%>/order/order" method="post" name ="singleProductOrderFrm">
		<input type="hidden" name="productNo" value="<%=product.getProductNo() %>" />
		<input type="hidden" name="productPrice" value="<%=product.getProductPrice() %>" />
		<input type="hidden" name="originalFileName" value="<%=thumbnailImg.getOriginalFileName() %>" />
		<input type="hidden" name="renamedFileName" value="<%=thumbnailImg.getRenamedFileName() %>" />
		<input type="hidden" name="subscriptionPeriod" value="<%= product.getSubscriptionPeriod() %>" />
		<a href="<%= request.getContextPath() %>/calendar?productNo=<%=product.getProductNo() %>" class="go_cal">?????????????????? ??????</a>
		<div class="prd_tit_wrap">
			<h2 class="prd_tit"><%=product.getProductName() %>
				<input type="hidden" name="productName" value="<%= product.getProductName()%>"/> </h2>
			<h3 class="prd_sub_tit"><%=product.getProductdescription() %></h3>
		</div>
		<div class="prd_opt_wrp">
			<div class="prd_opt">
				<div class="opt_title">??????</div>
				<div class="opt">
					<ul class="qty_wrp">
						<li class="qty_minus"><span class="quantity_minus"><i class="fa-solid fa-minus"></i></span>
						<li class="qty"><input type="number" value="1" id="quantity" name="quantity"></li>
						<li class="qty_plus"><span class="quantity_plus"><i class="fa-solid fa-plus"></i></span></li>
					</ul>
				</div>
			</div>
			<div class="prd_opt">
				<div class="opt_title">??? ?????????</div>
				<div class="opt">
					<div class="input_wrp">
						<input class="datepicker" id="firstShippingDate" name="firstShippingDate" value="<%= defaultDate %>"/>
					</div>
				</div>
			</div>
		</div>
		<div class="total_wrap">
			<div class="total_tit">?????? ??????</div>
			<div class="total_price"><%=df.format(product.getProductPrice()) %>???</div>
		</div>
		<div class="order_btn_wrp">
			<a href='#' class="btn_order">????????????</a> 
			<a href='javascript:void(0);' class="btn_add_cart">???????????? ??????</a>
		</div>
		</form>
	</div>
</div>
<div class="tab_wrap">
	<ul class="detail_tab">
		<li class="detail_tab1"><span>????????????</span></li>
		<li class="detail_tab2"><span class="ship_info">????????????</span></li>
	</ul>
</div>
<div class="prd_detail">
	<div class="detail_tab1_active">
		<img src="<%= request.getContextPath() %>/upload/product/<%= detailImg1.getRenamedFileName() %>" alt="<%= detailImg1.getOriginalFileName() %>" class="detail_img" />
<%
    	if(detailImg2 != null ){
%>
		<img src="<%= request.getContextPath() %>/upload/product/<%= detailImg2.getRenamedFileName() %>" alt="<%= detailImg2.getOriginalFileName() %>" class="detail_img" />
<%
    }
%>
		<h3>???????????? ????????????</h3>
		<p>???????????????, ???????????????-???????????? ??????</p>
	</div>
	<div class="detail_tab2_active">
		<table>
			<tr>
				<th>?????????</th>
				<td>??????</td>
			</tr>
			<tr>
				<th>??? ?????????</th>
				<td>?????????????????? 3??? ??? ??? ????????? ????????????, ??????, ???????????? ??????????????????.</td>
			</tr>
		</table>
	</div>
</div>
<script>

    const datepicker = document.querySelector('.datepicker');
  
    flatpickr(" .datepicker", {
        minDate: new Date().fp_incr(3),
        maxDate: new Date().fp_incr(30),
        "disable": [
            function (date) {
                // return true to disable
                return (date.getDay() === 0 || date.getDay() === 6);
            }
        ]
    });

    const detail_tab2 = document.querySelector(".detail_tab2");
    const detail_tab1 = document.querySelector(".detail_tab1");
    detail_tab2.addEventListener('click', function () {
        const detail_tab1_active = document.querySelector(".detail_tab1_active");
        const detail_tab2_active = document.querySelector(".detail_tab2_active");
        detail_tab2.style.borderBottom = "#116919 solid";
        detail_tab1.style.borderBottom = "none";
        detail_tab1_active.style.display = "none";
        detail_tab2_active.style.display = "block";
    });

    detail_tab1.addEventListener('click', function () {
        const detail_tab1_active = document.querySelector(".detail_tab1_active");
        const detail_tab2_active = document.querySelector(".detail_tab2_active");
        detail_tab1.style.borderBottom = "#116919 solid";
        detail_tab2.style.borderBottom = "none";
        detail_tab2_active.style.display = "none";
        detail_tab1_active.style.display = "block";
    });
    
    // -?????????? ????????? ?????? ??????
    $(".quantity_minus").click((e) => {
        let quantity = $(event.target).parents("li").next().children().val();
        if (quantity <= 1) {
            alert('???????????? 1???');
            return;
        };
        quantity = quantity - 1;
        $(event.target).parents("li").next().children("input[type=number]").val(quantity);
    });
    
    // +?????????? ????????? ?????? ??????
    $(".quantity_plus").click((e) => {
        let quantity = (Number)($(event.target).parents("li").prev().children().val());
        quantity = quantity + 1;
        $(event.target).parents("li").prev().children().val(quantity);
    });
    
    <% String memberId = loginMember != null ? loginMember.getMemberId() : ""; %>
  //???????????? ?????? ??????
	$(".btn_add_cart").click((e) => {
		if("<%=memberId%>" !== ""){
			$.ajax({
				type : "POST",
				async : true,
				dataType: "text",
				data : {productNo: <%=product.getProductNo()%>, memberId : "<%=memberId%>",
					quantity : $('#quantity').val(), firstShippingDate : $('#firstShippingDate').val()},
					url : "<%=request.getContextPath()%>/order/cart/insertCart",
					success : function(resp){
						alert(resp);
					},
				error : function(data){
					alert('????????????');
				}
	
			});
		}
		else{
			alert("????????? ??? ???????????? ??? ????????????.");
		}
	});
  
  //???????????? ?????? ??????
	$(".btn_order").click((e) => {
		$("form").submit();
	});
  
</script>


<%@ include file="/WEB-INF/views/common/footer.jsp"%>