<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.QnaBoardExt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/qnaboard.css" />
<%
List<QnaBoardExt> list = (List<QnaBoardExt>) request.getAttribute("list");
String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
%>
<style>
body{font-family: 'Noto Sans KR', sans-serif;}
 .no-search-result{margin-left: 45rem; margin-top:5rem;}
h1{color: #7f7f7f; font-size:18px;}
.search-result-list{margin-top:2rem; margin-bottom: 3rem;}
.search-result-list>a{font-size:20px; font-weight:bold; color:black;}
</style>



<nav id="board_top">
	<h2>질문과 답변</h2>
	<h3>homesweethome 인테리어 고수들과 전문가들에게 조언을 받아보세요.</h3>
	<div id="search-content" class="search-type">
		<form action="<%=request.getContextPath()%>/qna/qnaBoardFinder">
			<input type="hidden" name="searchType" value="content" />
			<!--돋보기-->
			<input type="text" name="searchKeyword" class="search-box"
				placeholder="궁금한 것을 검색해보세요."
				value="<%="content".equals(searchType) ? searchKeyword : ""%>" />
		</form>
	</div>
</nav>

<div class="no-search-result" >
			<%
			if (list == null || list.isEmpty()) {
			%>
			<img src="<%=request.getContextPath()%>/images/community/qna/Noresults.JPG" class="noresult">
			<h1>앗! 찾으시는 결과가 없네요.</h1>
</div>	
			<%
			} else {
			for (QnaBoardExt board : list) {
			%>
<div class="search-result-list">
		<!-- 		<h1> 검색결과 </h1><br><br> -->
				<a
					href="<%=request.getContextPath()%>/qna/qnaBoardView?no=<%=board.getNo()%>"><%=board.getTitle()%></a>
					<%
					if (board.getAttachCount() > 0) {
					%> <img id="thumb_file"
					src="<%=request.getContextPath()%>/images/file.png" alt="" /> <%
 }
 %>			
				<br><%=board.getNickName()%>&nbsp;&nbsp; 
				<%=board.getRegDate() %>
<%-- 				댓글 
				<% if ( board.getCommentCount()  > 0 )  {   
				System.out.println(board.getCommentCount());
				%>
				
				<%=board.getCommentCount() %> 
				<%} %> --%>
				
				· 조회 <%=board.getReadCount()%>	

			<%
			}
			}
			%>
</div>
</section>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>