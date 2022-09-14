<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.LikeDTO"%>
<%@page import="community.model.dto.Attachment"%>
<%@ page import="member.model.dto.Member"%>
<%@page import="community.model.dao.KnowhowDao"%>
<%@page import="community.model.service.KnowhowService"%>
<%@page import="community.model.dto.Knowhow"%>
<%@page import="community.model.dto.KnowhowExt"%>
<%@page import="community.model.dto.KnowhowComment"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://kit.fontawesome.com/97c6ec6a69.js"
	crossorigin="anonymous"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/knowhowView.css" />

<%

int no = Integer.parseInt(request.getParameter("no"));
boolean like = (boolean)request.getAttribute("like");


KnowhowExt knowhow = (KnowhowExt) request.getAttribute("knowhow");
List<KnowhowComment> comments = knowhow.getComments();
KnowhowComment qc = new KnowhowComment();

boolean canEdit = loginMember != null
		&& (loginMember.getMemberId().equals(knowhow.getMemberId()) || loginMember.getMemberRole() == MemberRole.A);

boolean canLike = loginMember != null;
%>
	<style>
	#likeBtn:hover{cursor:pointer;}
	</style>
<section id="board-container">
	<table id="tbl-board-view">

		<div class="knowhow-title-view">
			<h1><%=knowhow.getTitle()%></h1>
			<h3><%=knowhow.getNickName()%></h3>
			<h3><%=knowhow.getRegDate()%></h3>
		</div>

		<div class="knowhow-content-view">
			<%=knowhow.getContent()%><br>
			<%
			List<Attachment> attachments = knowhow.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				for (Attachment attach : attachments) {
			%>
			<img
				src="<%=request.getContextPath()%>/upload/community/knowhow/<%=attach.getRenamedFilename()%>"
				width=450px>
			<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
<%-- 			<h5>
				첨부파일 <img alt="첨부파일"
					src="<%=request.getContextPath()%>/images/file.png" width=13px>
				<a
					href="<%=request.getContextPath()%>/board/fileDownload?no=<%=attach.getNo()%>"><%=attach.getOriginalFilename()%></a>
			</h5> --%>
			<%
			}

			}
			%>
			<div class="view-end">
				<br> No.<%=knowhow.getNo()%>&nbsp;&nbsp; 조회
				<c><%=knowhow.getReadCount()%></c>
			</div>
			<div class="like-btn">
<%-- 		<%if(canLike){%> --%>
<form name="likeFrm" action="<%= request.getContextPath()%>/picture/pictureView" method="POST">
		<input type="hidden" id="likeMemId" name="memberId" value="<%= loginMember.getMemberId() %>" />
		<input type="hidden" id=likeBoardNum name="no" value="<%= knowhow.getNo()%>" />

	</form>

	<div id="LikeAlarm">
		<img src=<%= like ? "../images/like.png" : "../images/dislike.png" %> id="likeBtn" width="25px"/>좋아요 

	</div>
			

		</div>
		<%
		if (canEdit) {
		%>

		<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
		<input type="button" class="btn-upd" value="수정하기"
			onclick="updateBoard()">
		<input type="button" class="btn-del" value="삭제하기"
			onclick="deleteBoard()">
		<%
		}
		%>
	</table>

	<br style="margin-top: 30px;" />

	<div class="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/knowhow/knowhowComment"
				method="post" name="boardCommentFrm">
				<input type="hidden" name="knowhowNo" value="<%=knowhow.getNo()%>" />
				<input type="hidden" name="memberId"
					value="<%=loginMember != null ? loginMember.getMemberId() : ""%>" />
				<input type="hidden" name="nickName"
					value="<%=loginMember != null ? loginMember.getNickname() : ""%>" />
				<input type="hidden" name="commentLevel" value="1" /> <input
					type="hidden" name="commentRef" value="0" />
				<textarea name="content" cols="60" rows="3"  class="knowhow-comment"
					placeholder="댓글을 남겨 보세요."></textarea>
				<button type="submit" id="btn-comment-enroll1">입력</button>
			</form>
		</div>

		<%
		if (comments != null && !comments.isEmpty()) {
		%>
		<table id="tbl-comment">
			<tbody>
				<%
				for (KnowhowComment kc : comments) {

					boolean canDelete = loginMember != null
					&& (loginMember.getNickname().equals(kc.getNickName()) || loginMember.getMemberRole() == MemberRole.A);

					System.out.println("kc.getCommentLevel() = " + kc.getCommentLevel());
					if (kc.getCommentLevel() == 1) {
				%>
				<tr class="level1">

					<td><sub class="comment-writer"><%=kc.getNickName() != null ? kc.getNickName() : "(탈퇴회원)"%></sub>
						<sub class="comment-date"><%=kc.getRegDate()%></sub> <br /> <%=kc.getContent()%>
					</td>
					<td>

						<button class="btn-reply" value="<%=kc.getNo()%>">답글</button> <%
 if (canDelete) {
 %>
						<button class="btn-delete" value="<%=kc.getNo()%>">삭제</button> <%
 }
 %>
					</td>
				</tr>
				<%
				} else {
				%>
				<tr class="level2">
					<td><sub class="comment-writer"><%=kc.getNickName() != null ? kc.getNickName() : "(탈퇴회원)"%></sub>
						<sub class="comment-date"><%=kc.getRegDate()%></sub> <br /> <%=kc.getContent()%>
					</td>
					<td>
 <%
 if (canDelete) {
 %>
						<button class="btn-delete" value="<%=kc.getNo()%>">삭제</button> <%
 }
 %>
					</td>
				</tr>
				<%
				}
				}
				%>
			</tbody>
		</table>
		<%
		}
		%>
	</div>
</section>

<form
	action="<%=request.getContextPath()%>/knowhow/knowhowCommentDelete"
	name="boardCommentDelFrm" method="POST">
	<input type="hidden" name="no" /> <input type="hidden"
		name="knowhowNo" value="<%=knowhow.getNo()%>" />
</form>
<script>

$(document).ready(function(){
    $("#likeBtn").click(function(){
		$.ajax({
			url: "<%= request.getContextPath() %>/knowhow/knowhowListView",
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
/* function like(){	  
	$.ajax({		    
		type: "POST",		    
		cache: false,		    
		dataType: "text",		    
		data:{"memberId": $("#memberId").val(),
			"board_num" : $("#likeBoardNum").val()		
		}, 
		/* $('#like_form').serialize(),    */
		//아이디가 like_form인 곳의 모든 정보를 가져와  
		//파라미터 전송 형태(표준 쿼리형태)로 만들어줌		   
		/* success: 		    
			function(data){      					
			//ajax통신 성공시 넘어오는 데이터 통째 이름 =data		    	
			alert("'좋아요'가 반영되었습니다!") ;  
			// data중 put한 것의 이름 like                
			$("#like_result").html(data.like);  
			//id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.		   
			},   		    		    
			error: 		    
			function (request, status, error){  		      
				alert("ajax실패")                  		   
				}		  
			});
	} */

	   
	 
	   
	   
	   






document.querySelectorAll(".btn-delete").forEach((button) => {
	button.onclick = (e) => {
		if(!confirm("정말 삭제하시겠습니까?")) return;
		document.boardCommentDelFrm.no.value = e.target.value;
		document.boardCommentDelFrm.submit();
	}
});
document.querySelectorAll(".btn-reply").forEach((button) => {
	button.onclick = (e) => {
		if(<%=loginMember == null%>){
			loginAlert();
			return;
		}
					
		const {value : commentRef} = e.target;
		console.log(commentRef);
		
		// tr > td > form 
		const tr = document.createElement("tr");
		const td = document.createElement("td");
		td.colSpan = "2";
		td.style.textAlign = "left";
		
		const frm = document.createElement("form");
		frm.name = "boardCommentFrm";
		frm.action = "<%=request.getContextPath()%>/knowhow/knowhowComment";
		frm.method = "POST";
		frm.onsubmit = commentSubmitHandler;
		
		
		const inputBoardNo = document.createElement("input");
		inputBoardNo.type = "hidden";
		inputBoardNo.name = "knowhowNo";		
		inputBoardNo.value = "<%=knowhow.getNo()%>"
		
			const inputMemberId = document.createElement("input");
		inputMemberId.type = "hidden";
		inputMemberId.name = "memberId";
		inputMemberId.value = "<%=loginMember != null ? loginMember.getMemberId() : ""%>";
		
		const inputNickName = document.createElement("input");
		inputNickName.type = "hidden";
		inputNickName.name = "nickName";
		inputNickName.value = "<%=loginMember != null ? loginMember.getNickname() : ""%>";
		
		const inputCommentLevel = document.createElement("input");
		inputCommentLevel.type = "hidden";
		inputCommentLevel.name = "commentLevel";
		inputCommentLevel.value = "2";
		
		const inputCommentRef = document.createElement("input");
		inputCommentRef.type = "hidden";
		inputCommentRef.name = "commentRef";
		inputCommentRef.value = commentRef;
			
		const textarea = document.createElement("textarea");
		textarea.name = "content";
		textarea.cols = "60";
		textarea.rows = "1";
		
		const button = document.createElement("button");
		button.className = "btn-comment-enroll2";
		button.innerText = "입력"
		
		frm.append(inputBoardNo);
		frm.append(inputMemberId);
		frm.append(inputNickName);
		frm.append(inputCommentLevel);
		frm.append(inputCommentRef);
		frm.append(textarea);
		frm.append(button);
		td.append(frm);
		tr.append(td);
		
		const parent = e.target.parentElement.parentElement.parentElement;
		const ref = e.target.parentElement.parentElement.nextElementSibling;
		parent.insertBefore(tr, ref);
		e.target.onclick = null;
	};
});	


document.querySelector("textarea[name=content]").onfocus = (e) => {
	if(<%=loginMember == null%>)
		loginAlert();
};


const commentSubmitHandler = (e) => {
	if(<%=loginMember == null%>){
		loginAlert();
		return false; 		
	}
	
	const contentVal = e.target.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
		alert("댓글 내용을 작성해주세요.");
		e.target.content.focus();
		return false;
	}
	
};
document.boardCommentFrm.onsubmit = commentSubmitHandler;

const loginAlert = () => {
	alert("로그인후 이용할 수 있습니다.");
	document.querySelector("#memberId").focus();
};

</script>


<%
if (canEdit) {
%>
<form action="<%=request.getContextPath()%>/knowhow/knowhowDelete"
	name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%=knowhow.getNo()%>" />
</form>
<script>
const deleteBoard = () => {
	if(confirm("정말 이 게시글을 삭제하시겠습니까?"))
		document.boardDeleteFrm.submit();
};	

const updateBoard = () => {
	location.href = "<%=request.getContextPath()%>/knowhow/knowhowUpdate?no=<%=knowhow.getNo()%>";
}
</script>
<%
}
%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>