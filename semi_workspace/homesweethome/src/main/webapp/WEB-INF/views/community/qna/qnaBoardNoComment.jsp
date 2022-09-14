<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap" rel="stylesheet">
<%@ page import="java.util.*"%>
<%@page import="community.model.dto.QnaBoard"%>
<%@page import="community.model.dto.QnaBoardExt"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
	List<QnaBoard> comlist =(List<QnaBoard>)request.getAttribute("comlist");
	int[] replycount = (int [])request.getAttribute("replycount");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
%>

<style>
body{font-family: 'Noto Sans KR', sans-serif;}
#result{margin-left:42em; margin-top:3.5rem;}
#result>h3{text-align:center;}
#result>p{font-size:15px;}
a:link{	color:#2F3438;text-decoration: none;}
a:visited{color:#2F3438;text-decoration: none;}
a:hover{color:rgb(130,140,148);text-decoration: none;	}
#board_top{margin-top:1.5rem;background-color:#F7F9FA;}
#board_top>h2{font-size:24px;font-weight: 700;line-height:32px;text-decoration: none solid rgb(47,52,56);text-align : center;}
#board_top>h3{font-size:16px;line-height:22px;text-decoration: none solid rgb(47,52,56);text-align : center;}
.search-box{height:3.2rem;width: 35rem;border : 1px solid #EAEDEF;padding : 0.7rem 1.1rem 0.9rem 2.2rem;margin-left:37rem;font-weight: bold;}
input::placeholder{font-weight: bold;font-size: small;font-color : #EAEDEF;}
.search-box:focus{outline : 1px solid #EAEDEF;}
input#btn-add{
	float:right; 
	margin: 17px 0 0 15px;
/* 	padding : 0 16px 0 16px;  */
	background-color:#35C5F0;
	height:40px;
	width: 94.65px;
	border:0;
	outline: none; 
	text-align :center;
	font-size: 17px;
	color:white;
	font-weight: bold;
	border-radius: 8px;
}

input#btn-list{
	float:right; 
	margin: 17px 0 0 15px;
/* 	padding : 0 16px 0 16px;  */
	background-color:#35C5F0;
	height:40px;
	width: 94.65px;
	border:0;
	outline: none; 
	text-align :center;
	font-size: 17px;
	color:white;
	font-weight: bold;
	border-radius: 8px;
}
#btn-list:hover {cursor: pointer}
#btn-add:hover {cursor: pointer}
</style>

<div class="nocomment-list">

<nav id="board_top">
		<h2>질문과 답변</h2>
		<h3>homesweethome 인테리어 고수들과 전문가들에게 조언을 받아보세요.</h3>
				<div id="search-content" class="search-type">
			<form action="<%=request.getContextPath()%>/qna/qnaBoardFinder">
				<input type="hidden" name="searchType" value="content" /> 
							<!--돋보기-->
				<input
					type="text" name="searchKeyword" class="search-box"
					placeholder="궁금한 것을 검색해보세요."
					value="<%="member_id".equals(searchType) ? searchKeyword : ""%>" />
			</form>
		</div>
		</nav>
		
<div class="nocomment-wrap">
	<input type="button" value="돌아가기" id="btn-list"
			onclick="location.href='<%=request.getContextPath()%>/community/qna';" />
	
		<input type="button" value="질문하기" id="btn-add" name="btn-add"
			onclick="location.href='<%=request.getContextPath()%>/qna/qnaBoardEnroll';" />
</div>

<div id="result">
 <%int i=0; %>
 <% for(QnaBoard board : comlist) {%>
			<a href="<%=request.getContextPath()%>/qna/qnaBoardView?no=<%=board.getNo()%>" >
			<h3><%=board.getTitle() %></h3>
			<h4><%=board.getContent() %></h4></a>
			 <p><%=board.getNickName()%>&nbsp;&nbsp;<%=board.getRegDate() %> &nbsp; 조회 <%=board.getReadCount() %></p>
			 <br>

<% i++; } %>
</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>