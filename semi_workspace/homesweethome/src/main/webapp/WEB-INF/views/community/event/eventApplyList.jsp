<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="java.util.List"%>
  <%@page import="community.model.dto.EventAppExt"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/event.css" />
<%
List<EventAppExt> list = (List<EventAppExt>) request.getAttribute("list");
%>


<section id="event-applyList-container">
<div class="title-view">
<h2>현재 진행중인 이벤트 참여작</h2>
 <img src="<%=request.getContextPath()%>/upload/community/event/e3.jpg" class="img_logo">
 </div>
 <div class="main">
<%
	if (list == null || list.isEmpty()) {
	%>
	<td colspan="6">조회된 이벤트가 없습니다.</td>
	<%}
	else{ 
		for(EventAppExt eventapp : list){
	%>
	<%=eventapp.getNickName()%>님의 참여작<br>
	 	🌼<a href="<%=request.getContextPath()%>/event/eventApplyView?no=<%=eventapp.getNo()%>">
	<%=eventapp.getEventapplyCode() %></a>🌼<br><br>
	
	<%} }%>
<input type="button" value="홈으로" onclick="backBoard()" class="btn-go-main">
<input type="button" value="참가하기" onclick="applyBoard()" class="btn-go-apply">
 </div>
</section>
	<script>
	const backBoard = () =>{
		location.href = "<%= request.getContextPath()%>";
	}
	const applyBoard = () =>{
		location.href = "<%=request.getContextPath()%>/event/eventApplyEnroll";
	}
	</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>

