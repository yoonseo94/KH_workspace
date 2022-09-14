<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/productEnroll.css" />
<form
	name="productEnrollFrm" 
	action="<%= request.getContextPath() %>/product/productEnroll"
	method="POST"
	enctype="multipart/form-data">
	<div class="boardType">
	    <input type="radio" name="boardType" id="boardType-sale" value="saleBoard" checked>
	    <label for="boardType-sale">판매하기</label>
	    <input type="radio" name="boardType" id="boardType-buy" value="buyBoard">
	    <label for="boardType-buy">구매하기</label>
	</div>
    <table id="product-enroll">
        <tbody>
            <tr id="first">
                <th rowspan="2">상품 이미지</th>
                <td class="attachments">
                    <input type="file" name="productImage" id="productImage" accept="image/*">
                    <label for="productImage">
                    </label>
                </td>
            </tr>
            <tr id="second">
                <td class="imageCondition">
                    <div>
                        - 상품 이미지는 640x640에 최적화되어 있습니다.<br>
                        - 상품 이미지는 PC에서는 1:1, 모바일에서는 1:1.23 비율로 보여집니다.<br>
                        - 이미지는 상품 등록 시 정사각형으로 잘려서 등록됩니다.<br>
                        - 이미지를 클릭할 경우 원본 이미지를 확인할 수 있습니다.
                    </div>
                </td>
            </tr>
            <tr>
                <th>제목</th>
                <td class="title">
                    <input type="text" name="productTitle" id="productTitle" placeholder="제목을 입력해주세요.">
                </td>
            </tr>
            <tr>
                <th>브랜드</th>
                <td class="brand">
                    <input type="text" name="productBrand" id="productBrand" placeholder="상품 브랜드를 입력해주세요.">
                </td>
            </tr>
            <tr>
                <th>카테고리</th>
                <td class="category">
                    <select name="productCategory" id="productCategory">
                    	<option value="" disabled selected>카테고리를 선택해주세요.</option>
                        <option value="top">상의</option>
                        <option value="bottom">하의</option>
                        <option value="shoes">신발</option>
                        <option value="stuff">잡화</option>
                        <option value="luxury">럭셔리</option>
                    </select>
                </td>
            </tr>
            <tr>
                <th>상태</th>
                <td class="status">
                    <input type="radio" name="productStatus" id="productStatus-old" value="U">
                    <label for="productStatus-old">
                        <i class="circle"></i>
                        <span>중고상품</span>
                    </label>
                    <input type="radio" name="productStatus" id="productStatus-new" value="N">
                    <label for="productStatus-new">
                        <i class="circle"></i>
                        <span>새상품</span>
                    </label>
                </td>
            </tr>
            <tr>
                <th>가격</th>
                <td class="price">
                    <input type="text" name="productPrice" id="productPrice"><span id="won">&nbsp;원</span>
                </td>
            </tr>
            <tr id="last">
                <th>내용</th>
                <td class="content">
                    <textarea name="productContent" id="productContent" cols="30" rows="10"></textarea>
                </td>
            </tr>
        </tbody>
    </table>
    <div id="product-enroll-submit">
        <hr>
        <div>
            <input type="submit" value="등록하기">
        </div>
    </div>
</form>
<script>
document.querySelector('.attachments input').addEventListener('change', (e1) => {
	if(e1.target.files && e1.target.files[0]) {
		const reader = new FileReader();
		reader.readAsDataURL(e1.target.files[0]);
		reader.onload = function(e) {
			console.log(e.target);
			document.querySelector('.attachments label').style.backgroundImage = `url('\${e.target.result}')`;
			document.querySelector('.attachments label').style.backgroundSize = "cover";
		};
	} else {
		document.querySelector('.attachments label').style.backgroundImage = "";
	}
});
// 판매글, 구매글 첨부파일 영역 구분
document.querySelectorAll('.boardType input').forEach((input) => {
    input.addEventListener('click', (e) => {
        const boardTypeVal = e.target.value;
        if(boardTypeVal === 'buyBoard') {
            first.style.display = 'none';
            second.style.display = 'none';
        } else {
            first.style.display = '';
            second.style.display = '';
        }
    });
});

// 유효성 검사
document.productEnrollFrm.onsubmit = (e) => {
	const frm = e.target;
	// 제목
	if(!/^.+$/.test(frm.productTitle.value.trim())) {
		alert("제목을 작성해주세요.");
		frm.productTitle.select();
		return false;
	}
	
	// 브랜드
	if(!/^.+$/.test(frm.productBrand.value.trim())) {
		alert("브랜드를 작성해주세요.");
		frm.productTitle.select();
		return false;
	}
	
	// 카테고리
	if(!(frm.productCategory.value)) {
		alert("카테고리를 선택해주세요.");
		return false;
	}
	
	// 상태
	if(!(frm.productStatus.value)) {
		alert("상품 상태를 선택해주세요.");
		return false;
	}
	
	// 가격
	if(!/^[0-9]+$/.test(frm.productPrice.value)) {
		alert("올바른 가격을 작성해주세요.");
		frm.productPrice.select();
		return false;
	}
	
	// 내용
	if(!/^(.|\n)+$/.test(frm.productContent.value.trim())) {
		alert("내용을 작성해주세요.");
		frm.productContent.select();
		return false;
	}
	
}
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>