<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 아이디 찾기</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/product/productIdFinder.css" />
</head>
<body>
    <div class="find-productId">
        <div class="popupFinderFrm-wrapper" id="popupFinderFrm-wrapper" >
            <form class="formContainer" id="formCert" name="emailCertFrm">
                <div class="pop-title">
                    <h2>상품 아이디 찾기</h2>
                </div>
                <div class="pop-finder-inner-wrapper">
                    <div class="pop-finder-name-inner">
                        <label for="nameId" id="nameId-label"><strong>상품명</strong></label>	
                        <input type="text" id="nameId" name="nameId" required placeholder="상품명을 먼저 입력하세요"> 
                    </div>
                    <div class="pop-finder-id-inner">
                        <label for="productId" id="productId-label"><strong>상품 아이디</strong></label>
                        <input type="text" id="productId" name="productId" required readonly>
                    </div>
                    <div class="pop-finder-inner-btn" id="cancel-inner">
                        <button type="button" class="btn-cancel" onclick="closePopup();">확인</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    
<script>
// 현재창 닫기
const closePopup = () => {
	const productIdVal = document.querySelector("#productId").value;
	const productNameVal = document.querySelector("#nameId").value;
	opener.document.querySelector("#productId").value = productIdVal;
	opener.document.querySelector("#productName").value = productNameVal;
	opener.document.querySelector("#count").focus();
	self.close();
};


$("#nameId").autocomplete({
    source(request, response){
  	  $.ajax({
  		  url: "<%=request.getContextPath()%>/product/findNameAuto",
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
  	  let indextStart = 0;
  	  const productName =  item.value.slice(indexEnd + 1);
  	  const productId = item.value.slice(indextStart, indexEnd) + productName;
  	  alert(item.value);
  	  item.value= productName;
  	  document.querySelector("#productId").value = productId;
    }
    
  });
</script>
</body>
</html>