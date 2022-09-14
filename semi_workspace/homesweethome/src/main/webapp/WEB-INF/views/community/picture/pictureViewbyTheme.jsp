<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/pictureList.css" />
<%@page import="community.model.dto.PictureExt"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
ArrayList<PictureExt> list = (ArrayList<PictureExt>) request.getAttribute("productList");
int startPage = (int) request.getAttribute("startPage");
int endPage = (int) request.getAttribute("endPage");
int pageCount = (int) request.getAttribute("pageCount");
int catenum = (int) request.getAttribute("catenum");
%>


<nav id="board_top">
	<h2>사진</h2>

	<div class="ViewByTheme">
<input type="button" value="전체" id="btn-storage" name="btn-go"
	class="btn-go"
	onclick="location.href='<%=request.getContextPath()%>/community/picture';" />
<input type="button" value="원룸&오피스텔" id="btn-oneoffi" name="btn-go"
	class="btn-go"
	onclick="location.href='<%=request.getContextPath()%>/picture/pictureViewByTheme?catenum=<%=10%>';" />
<input type="button" value="아파트"  id="btn-apart" name="btn-go"
	class="btn-go"
	onclick="location.href='<%=request.getContextPath()%>/picture/pictureViewByTheme?catenum=<%=20%>';" />

     </div>  

</nav>

	

<div class="category">
			<%
		if (list == null || list.isEmpty()) {
		%>
		<td>등록된 사진이 없습니다.</td>
		
		<%
		} else {
		for (PictureExt pic : list) {
		%>
		
		<a href="<%=request.getContextPath()%>/picture/pictureView?no=<%=pic.getImgNo()%>" class="thumbnail">
		<img src="<%=request.getContextPath()%>/upload/community/picture/<%=pic.getCoverPhoto()%>" class="thumb_nail" >
		<b><%=pic.getTitle()%></b>

		<p><%=pic.getNickName()%> ·
		조회수 <%=pic.getReadCount() %></p>
		</a>
		<%}} %>
</div>

<br>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>