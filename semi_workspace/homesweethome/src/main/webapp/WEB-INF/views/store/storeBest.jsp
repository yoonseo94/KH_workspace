<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/storesubmenu.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/storeItem.css" />

<body>

    
	<h2 align="center" style="margin-top:100px;"> 실시간 베스트 </h2>	
		


<div class="list con">
    <ul class="row">
        <li class="cell">
            <div class="img-box"><img src="https://image.ohou.se/i/bucketplace-v2-development/uploads/productions/164455726167952635.jpg?gif=1&w=640&h=640&c=c" alt="first"></div>
            <div class="product-name">샘플용</div>
            <div class="product-price">1000 </div>
            <div class="product-rate">별점 </div>
            <div class="product-rate">리뷰 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/1.png" alt="first"></div>
            <div class="product-name">1번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/1.png" alt="first"></div>
            <div class="product-name">1번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/1.png" alt="first"></div>
            <div class="product-name">1번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/2.png" alt="second"></div>
            <div class="product-name">2번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/2.png" alt="second"></div>
            <div class="product-name">2번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/2.png" alt="second"></div>
            <div class="product-name">2번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/2.png" alt="second"></div>
            <div class="product-name">2번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/3.png" alt="second"></div>
            <div class="product-name">3번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/3.png" alt="second"></div>
            <div class="product-name">3번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/3.png" alt="second"></div>
            <div class="product-name">3번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
        <li class="cell">
            <div class="img-box"><img src="<%= request.getContextPath()%>/images/3.png" alt="second"></div>
            <div class="product-name">3번째줄 테스트</div>
            <div class="product-price">가격 </div>
        </li>
    </ul>
</div>
    
    
    
</body>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>