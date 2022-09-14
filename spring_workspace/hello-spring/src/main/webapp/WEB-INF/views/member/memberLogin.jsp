<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- bootstrap js: jquery load 이후에 작성할것.-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<!-- bootstrap css -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">

<!-- 사용자작성 css -->
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" />

</head>
<body>

	<!-- Modal시작 -->
	<!-- https://getbootstrap.com/docs/4.1/components/modal/#live-demo -->
	<div class="modal fade" id="loginModal" tabindex="-1" role="dialog"
		aria-labelledby="loginModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginModalLabel">로그인</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<!--로그인폼 -->
				<!-- https://getbootstrap.com/docs/4.1/components/forms/#overview -->
				<form:form method="post">
					<div class="modal-body pb-2">
						<c:if test="${param.error != null}">
							<div class="alert alert-danger alert-dismissible fade show" role="alert">
							  <span class="text-danger">아이디 또는 비밀번호가 일치하지 않습니다.</span>
							  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
							    <span aria-hidden="true">&times;</span>
							  </button>
							</div>
						</c:if>
						
						<input 
							type="text" class="form-control" name="memberId"
							placeholder="아이디" value="${param.memberId}" required> 
						<br /> 
						<input
							type="password" class="form-control" name="password"
							placeholder="비밀번호" required>
							
						<div class="text-right pb-0">
							<a href="${pageContext.request.contextPath}/member/findMemberId.do">아이디 찾기</a> |
							<a href="${pageContext.request.contextPath}/member/findPassword.do">비번 찾기</a>
						</div>
					</div>
					<div class="modal-footer justify-content-between">
						<div>
							<input type="checkbox" class="form-check-input" name="remember-me" id="remember-me" />
							<label for="remember-me" class="form-check-label">Remeber me</label>
						</div>				
						<div>
							<button type="button" class="btn btn-outline-success" data-dismiss="modal">취소</button>
							<button type="submit" class="btn btn-outline-success">로그인</button>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<!-- Modal 끝-->
	
	<script>
	<%-- 
	<c:if test="${not empty msg}">
		alert('${msg}');
	</c:if> 
	--%>

	$("#loginModal")
		.modal()
		.on('hide.bs.modal', (e) => {
			<c:if test="${empty header.referer || fn:contains(header.referer, '/member/memberLogin.do')}">
				location.href = '${pageContext.request.contextPath}';
			</c:if>
			<c:if test="${not fn:contains(header.referer, '/member/memberLogin.do')}">
				location.href = '${header.referer}';
			</c:if>
		});
	</script>
</body>
</html>
