<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- <link rel="stylesheet" href="<%=request.getContextPath() %>/css/signin.css" />  보류 -->
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<script>
<% 
String saveId = null; 
Cookie[] cookies = request.getCookies();
if(cookies != null){
	for(Cookie cookie : cookies){
		if("saveId".equals(cookie.getName())){
			saveId = cookie.getValue();
		}
	}
}
else {
}
 %>
</script>
<style>
.notice-page {
	width: 100%;
    max-width: 750px;
    margin: 20px auto;
}
.notice-title{
    font-size: 24px;
    line-height: 32px;
    margin-bottom: 40px;
}
.notice-content{
	width: 343px;
    margin: 0 auto;
    text-align: center;
    margin-top: 200px;
    margin-bottom: 200px;
}
.notice-innertext{
	font-size: 16px;
    line-height: 24px;
    margin-top: 10px;
    color: #828C94;
    white-space: pre-wrap;
}
</style>

</style>
<div class = "all-content">
	<div class="notice-page">
		<h1 class="notice-title">내 소식</h1>
			<div class="notice-content">
				<p class="notice-innertext">최근 내 소식이 없습니다.</p>
			</div>
	</div>
</div>
</script>
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>	
<%@ include file="/WEB-INF/views/common/footer.jsp" %>