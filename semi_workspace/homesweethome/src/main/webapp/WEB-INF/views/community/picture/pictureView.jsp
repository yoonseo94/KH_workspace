<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.min.js"></script>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="member.model.dto.Member"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dao.PictureDao"%>
<%@page import="community.model.dto.LikeDTO"%>
<%@page import="community.model.dto.PictureAttachment"%>
<%@page import="community.model.dto.Picture"%>
<%@page import="community.model.dto.PictureExt"%>
  <meta
      name="viewport"
      content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1"
    />
    <!-- Link Swiper's CSS -->
    <link
      rel="stylesheet"
      href="https://unpkg.com/swiper/swiper-bundle.min.css"
    />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/pictureList.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
	<style>
	#likeBtn:hover{cursor:pointer;}
	</style>
<%

int no = Integer.parseInt(request.getParameter("no"));
boolean like = (boolean)request.getAttribute("like");

PictureExt picture = (PictureExt) request.getAttribute("picture");
boolean canEdit = loginMember != null
		&& (loginMember.getMemberId().equals(picture.getMemberId()) || loginMember.getMemberRole() == MemberRole.A);


%>

<section id="board-view-container">
	<table id="tbl-board-view">

		<div class="title-view">
			<h1><%=picture.getTitle()%></h1>
			<h3><%=picture.getNickName()%></h3>
			<h3><%=picture.getRegDate()%></h3>
		</div>

		<div class="content-view">
			<%=picture.getContent()%><br>
			<%
			List<PictureAttachment> attachments = picture.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				for (PictureAttachment attach : attachments) {
			%>
<img
				src="<%=request.getContextPath()%>/upload/community/picture/<%=attach.getRenamedFilename()%>"
				width=450px><br>

    </div>
			
			<%
			}

			}
			%>
			<div class="view-end">
				<br> No.<%=picture.getImgNo()%>&nbsp;&nbsp; 조회
				<c><%=picture.getReadCount()%></c>
				&nbsp;&nbsp;
			</div>
	<div class="like-btn">

<form name="likeFrm" action="<%= request.getContextPath()%>/picture/pictureView" method="POST">
		<input type="hidden" id="likeMemId" name="memberId" value="<%= loginMember.getMemberId() %>" />
		<input type="hidden" id=likeBoardNum name="no" value="<%= picture.getImgNo()%>" />
		
	</form>

	<div id="LikeAlarm">
		<img src=<%= like ? "../images/like.png" : "../images/dislike.png" %> id="likeBtn" width="25px" />좋아요 

		</div>
			
	</div>
			<%
			if (canEdit) {
			%>
			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
			<input type="button" class="btn-upd" value="수정하기"
				onclick="updateBoard()"> <input type="button"
				class="btn-del" value="삭제하기" onclick="deleteBoard()">
			<%
			}
			%>
		
	</table>
</section>

<%
if (canEdit) {
%>
<form action="<%=request.getContextPath()%>/picture/pictureDelete"
	name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%=picture.getImgNo()%>" />
</form>
<script>
const deleteBoard = () => {
	if(confirm("정말 이 게시글을 삭제하시겠습니까?"))
		document.boardDeleteFrm.submit();
};	

const updateBoard = () => {
	location.href = "<%=request.getContextPath()%>/picture/pictureUpdate?no=<%=picture.getImgNo()%>";
}
</script>
<%
}
%>

<script>

//좋아요 자체
$(document).ready(function(){
    $("#likeBtn").click(function(){
		$.ajax({
			url: "<%= request.getContextPath() %>/picture/pictureView",
			method: "POST", 
			dataType: "text", //html, text, json, xml 리턴된 데이터에 따라 자동설정됨
			data:  {"memberId": $("#likeMemId").val(),
					"no" : $("#likeBoardNum").val(),
				}, //사용자 입력값전달
			success: function(data){
				history.go(0);
			},
			error: function(xhr, textStatus, errorThrown){
				alert("인증번호가 일치하지 않습니다.")
				console.log("ajax 요청 실패!");
				console.log(xhr, textStatus, errorThrown);
			}
		});
    });
});


</script>

         <a style="display:scroll;position:fixed;bottom:92px; right:45px;" rel="nofollow"
     href="#" >
     <img src="<%=request.getContextPath()%>/images/top_button.png" width=20px></a>

     <a style="display:scroll;position:fixed;bottom:55px; right:45px;" rel="nofollow"
     href="#scrollbottom" ><img src="<%=request.getContextPath()%>/images/bot_button.png" width=20px></a>
     <div id="scrollbottom"></div>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>