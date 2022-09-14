<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="비밀번호찾기 결과" name="title"/>
</jsp:include>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/resources/css/member.css" />

<div id="enroll-container" class="mx-auto text-center">
	<c:if test="${result eq 'success'}">
		<div>
			<p>${resultMsg}</p>
		</div>
	</c:if>
	<c:if test="${result eq 'failure'}">
		<div>
			<p>${resultMsg}</p>
		</div>	
		<form:form name="findPasswordFrm" method="POST">
			<table class="mx-auto">
				<tr>
					<th>아이디</th>
					<td>	
						<input type="text" class="form-control" placeholder="회원아이디" name="memberId" id="memberId" value="">
					</td>
				</tr>
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
		<script>
		document.findPasswordFrm.addEventListener('submit', (e) => {
			
		});
		</script>
	</c:if>
</div>




<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
