<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/header.jsp" %>

		<h1>main1</h1>
		<p><%= name %>님, 반갑습니다.</p>
		<!-- p>lorem -->
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ex placeat libero eius ipsum possimus tempora reiciendis facilis quasi quis excepturi labore error modi numquam delectus magni beatae facere eligendi autem.</p>
		
		
		<script>
		const title = document.querySelector("header h1").innerHTML;
		alert(title);
		</script>
		
<%@ include file="/jsp/footer.jsp" %>