<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="아이디찾기 결과" name="title"/>
</jsp:include>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/member.css" />

<div id="enroll-container" class="mx-auto text-center">
	<c:if test="${not empty memberId}">
		<div>
			<p>아이디는 <a href="${pageContext.request.contextPath}/member/memberLogin.do?memberId=${memberId}">${memberId}</a> 입니다.</p>
		</div>	
	</c:if>
	<c:if test="${empty memberId}">
		<p>해당하는 회원이 존재하지 않습니다.</p>
		<form:form name="lostMemberIdFrm" action="" method="POST">
			<table class="mx-auto">
				<tr>
					<th>이메일</th>
					<td>	
						<input type="email" class="form-control" placeholder="abc@xyz.com" name="email" id="email" value="">
					</td>
				</tr>
			</table>
			<input type="submit" value="찾기" >
			<input type="reset" value="취소">
		</form:form>
	</c:if>
</div>

<script>
document.lostMemberIdFrm.addEventListener('submit', (e) => {
	
	
});

</script>



<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
