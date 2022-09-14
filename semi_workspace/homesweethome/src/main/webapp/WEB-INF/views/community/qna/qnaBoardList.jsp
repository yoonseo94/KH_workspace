<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.QnaBoardExt"%>
<%@page import="community.model.dto.QnaNoticeExt"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<script type="text/javascript" src="jquery.tablesorter.min.js"></script>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/qnaboard.css" />
<%
List<QnaBoardExt> list = (List<QnaBoardExt>) request.getAttribute("list");
List<QnaNoticeExt> nlist = (List<QnaNoticeExt>) request.getAttribute("nlist");
boolean canEdit = ( loginMember != null && loginMember.getMemberRole() == MemberRole.A);
String searchType = request.getParameter("searchType");
String searchKeyword = request.getParameter("searchKeyword");
%>


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

<div class="sort">
<h5>
<a href="<%=request.getContextPath()%>/community/qna">최신순 |</a>
<a href="<%=request.getContextPath()%>/community/qna/ReadcntDesc">조회수순</a>
</h5>
</div>

<div class="button-wrap">
	<input type="button" value="질문하기" id="btn-add" name="btn-add"
		onclick="location.href='<%=request.getContextPath()%>/qna/qnaBoardEnroll';" />
	<input type="button" value="답변을 기다리는 질문" id="btn-qna"
		onclick="location.href='<%=request.getContextPath()%>/qna/qnaBoardNoComment';" />
				<% if(canEdit){ %>
	<input type="button" value="공지작성" id="btn-add" name="btn-add"
		onclick="location.href='<%=request.getContextPath()%>/qna/qnaNoticeEnroll';" />
		<%} %>
</div>

<div id="board-list">
<div class= "notice-list">

	<%
	if (nlist == null || nlist.isEmpty()) {
	%>
	<td colspan="6">등록된 공지가 없습니다.</td>


	<%
	} else {
	for (QnaNoticeExt notice : nlist) {
	%>
	<a
		href="<%=request.getContextPath()%>/qna/qnaNoticeView?no=<%=notice.getNo()%>">
		<b>공지</b>&nbsp;📢<%=notice.getTitle()%><br><br>
	</a>
	<%
	}
	}
	%>

</div>

<div class="qna-list">
	<%
	if (list == null || list.isEmpty()) {
	%>
	<td colspan="6">조회된 정보가 없습니다.</td>

	<%
	} else {
	for (QnaBoardExt board : list) {
	%>

	<a href="<%=request.getContextPath()%>/qna/qnaBoardView?no=<%=board.getNo()%>">
	<%=board.getTitle()%></a>

<%
if (board.getAttachCount() > 0) {
%> 
<img id="thumb_file" src="<%=request.getContextPath()%>/images/file.jpg" alt=""  width=11px/> 
<%
 }
 %> 
 
<div class="lists">
<%=board.getNickName()%>
· 댓글 <d><%=board.getCommentCount()%></d> · 조회 <%=board.getReadCount()%>
<br><br>
</div>

	<%
	}
	}
	%>
</div>


<!--탑버튼 -->
     <a style="display:scroll;position:fixed;bottom:92px; right:45px;" rel="nofollow"
     href="#" >
     <img src="<%=request.getContextPath()%>/images/top_button.png" width=20px></a>

     <a style="display:scroll;position:fixed;bottom:55px; right:45px;" rel="nofollow"
     href="#scrollbottom" ><img src="<%=request.getContextPath()%>/images/bot_button.png" width=20px></a>
     <div id="scrollbottom"></div>


</div>
<div id='pageBar'><%=request.getAttribute("pagebar")%></div>



<script>
/* var readcntdesc = function(url){

	$.ajax({
		type: 'get',
		url: "/ReadcntDesc",
		data: "",
		success: function(data) {
			$('#sort_list').html(data);
		},
		error: function(request, status, error) {
			alert(error);
		}
	});
}; */


//질문하기 버튼
function checkbox(box){
    var check = document.getElementsByName("btn-add");
    for(var i=0;i<check.length;i++){
     (check[i]!=box) && (check[i].checked=false);
    }
}

//내용검색
searchType.addEventListener('change', (e) => {
	const {value} = e.target; // 구조분해할당
	console.log(value);
	
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	});
	let id = "";
	switch(value){
		case "content": id = "content"; break; 

	document.querySelector(`#\${id}`).style.display = "inline-block";	
});


</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>
