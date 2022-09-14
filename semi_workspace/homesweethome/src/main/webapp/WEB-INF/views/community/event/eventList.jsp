<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="community.model.dto.EventExt"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/event.css" />
<%
List<EventExt> list = (List<EventExt>) request.getAttribute("list");
boolean canEdit = (loginMember != null && 
loginMember.getMemberRole() == MemberRole.A);

%>

<style>
#listimg{
width:57%;
height:23%;
transform:scale(1.0);        
}
#listimg:hover{
 transform: scale(1.07);
  -webkit-transform: scale(1.07);
  -moz-transform: scale(1.07);
  -ms-transform: scale(1.07);
  -o-transform: scale(1.07);
  }
</style>
<%if(canEdit) {%>
<div id="event">
	<input type="button" value="이벤트작성"  class="btn-event-add" name="btn-event-add"
		onclick="location.href='<%=request.getContextPath()%>/event/eventEnroll';" />
<%} %> 

<%
	if (list == null || list.isEmpty()) {
	%>
	<td colspan="6">조회된 이벤트가 없습니다.</td>
</div>
	<%
	} else {
		
		for (EventExt event : list) {
	%>

<div class="event-list">
<a href="<%=request.getContextPath()%>/community/eventView?no=<%=event.getNo()%>">
<img src="<%=request.getContextPath()%>/upload/community/event/<%=event.getTitlefileName()%>" id="listimg"/>
</a>
<br>
<c:set var="today" value="<%=new java.util.Date() %>"/>
<c:set var="edate" value="<%=event.geteDate() %>"/>
<c:if test="${edate < today}">
<div class="end">
<p>종료</p>
</div>
</c:if>

<c:if test="${edate > today}">
<div class="going">
<p>진행중</p>
</div>
</c:if>
<%=event.getsDate() %> ~ <%=event.geteDate() %>

</div>

	<%
	}
	}
	%>
     <a style="display:scroll;position:fixed;bottom:92px; right:45px;" rel="nofollow"
     href="#" >
     <img src="<%=request.getContextPath()%>/images/top_button.png" width=20px></a>

     <a style="display:scroll;position:fixed;bottom:55px; right:45px;" rel="nofollow"
     href="#scrollbottom" ><img src="<%=request.getContextPath()%>/images/bot_button.png" width=20px></a>
     <div id="scrollbottom"></div>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>    