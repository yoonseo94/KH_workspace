<%@page import="community.model.dto.QnaBoardComment"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="community.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.LikeDTO"%>
<%@page import="community.model.dto.QnaNoticeExt"%>
<%@page import="community.model.dto.QnaNoticeComment"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<%
int no = Integer.parseInt(request.getParameter("no"));
QnaNoticeExt board = (QnaNoticeExt) request.getAttribute("board");
List<QnaNoticeComment> comments = board.getComments();
//공지crud는 관리자만
boolean canEdit = loginMember != null && (loginMember.getMemberRole() == MemberRole.A);

//댓글은 회원+관리자만
boolean canEdit2 = loginMember != null
		&& (loginMember.getNickname().equals(board.getNickName()) || loginMember.getMemberRole() == MemberRole.A);
%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/qnaboard.css" />
<section id="notice-view-container">
	<table id="tbl-board-view">
		<div class="title-view">
			<h2>공지사항</h2>
			<h1><%=board.getTitle()%></h1>
			<h2><%=board.getNickName()%></h2>
		</div>


		<div class="content-view">
			<%
			List<Attachment> attachments = board.getAttachments();
			if (attachments != null && !attachments.isEmpty()) {
				for (Attachment attach : attachments) {
			%>
			첨부파일:
			<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
			<img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.jpg"
				width=16px><%--  <a
				href="<%=request.getContextPath()%>/board/fileDownload?no=<%=attach.getNo()%>"><%=attach.getOriginalFilename()%></a> --%>
			<br> 
			<img src="<%=request.getContextPath()%>/upload/community/qna/<%=attach.getRenamedFilename()%>"
				width=500px><br>

			<%
			}
			%>

			<%
			}
			%>
			</div>
			<%=board.getContent()%>

			<div class="view-end">
				No.<%=board.getNo()%>&nbsp;&nbsp; 조회
				<c><%=board.getReadCnt()%></c><br><br>

				<%
				if (canEdit) {
				%>
			
				<%-- 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
				<input type="button" value="수정하기" onclick="updateBoard()"
					class="notice-btn"> <input type="button" value="삭제하기"
					onclick="deleteBoard()" class="notice-btn">
				<%
				}
				%>

			</div>
	</table>

	<br style="margin-top: 3rem;" />

	<div class="comment-container">
		<div class="comment-editor">
			<form action="<%=request.getContextPath()%>/qna/qnaNoticeComment"
				method="post" name="boardCommentFrm">
				<input type="hidden" name="noticeNo" value="<%=board.getNo()%>" />
				<input type="hidden" name="memberId"
					value="<%=loginMember != null ? loginMember.getMemberId() : ""%>" />
				<input type="hidden" name="nickName"
					value="<%=loginMember != null ? loginMember.getNickname() : ""%>" />
				<input type="hidden" name="commentLevel" value="1" /> <input
					type="hidden" name="commentRef" value="0" />
				<textarea name="content" class="comment-content"  cols="60" rows="3"></textarea>
				<button type="submit" id="btn-comment-enroll1">등록</button>
			</form>
		</div>
		<% if (comments != null && !comments.isEmpty()) {%>
		<table id="tbl-comment">
			<tbody>
			<% 
				for (QnaNoticeComment nc : comments){ 
					
					boolean canDelete = loginMember != null 
							&& (loginMember.getNickname().equals(nc.getNickName()) 
									|| loginMember.getMemberRole() == MemberRole.A);
							
					System.out.println("nc.getCommentLevel() = " + nc.getCommentLevel());
					if(nc.getCommentLevel() == 1){
			%>
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%= nc.getNickName() != null ? nc.getNickName() : "(탈퇴회원)" %></sub>
						<sub class="comment-date"><%= nc.getRegDate() %></sub>
						<br />
						<%= nc.getContent() %>
					</td>

					
					<td>
						<button class="btn-reply" value="<%= nc.getCommentNo() %>">답글</button>
						<% if(canDelete){ %>
							<button class="btn-delete" value="<%= nc.getCommentNo() %>">삭제</button>
						<% } %>
					</td>
				</tr>
			<% 		
			} else { 
			%>
				<tr class="level2">
					<td>
						<sub class="comment-writer"><%= nc.getNickName() != null ? nc.getNickName(): "(탈퇴회원)" %></sub>
						<sub class="comment-date"><%= nc.getRegDate() %></sub>
						<br />
						<%= nc.getContent() %>
					</td>
					<td>
																					
						<% if(canDelete){ %>
							<button class="btn-delete" value="<%= nc.getCommentNo()%>">삭제</button>
						<% } %>
					</td>
				</tr>
			<% 
					}	
				} 
			%>
			</tbody>
		</table>
		<% } %>
	</div>
</section>



<form action="<%=request.getContextPath()%>/qna/qnaNoticeCommentDelete"
	name="boardCommentDelFrm" method="POST">
	<input type="hidden" name="no" /> <input type="hidden" name="noticeNo"
		value="<%=board.getNo()%>" />
</form>


<script>


document.querySelectorAll(".btn-delete").forEach((button) => {
	button.onclick = (e) => {
		if(!confirm("정말 삭제하시겠습니까?")) return;
		document.boardCommentDelFrm.no.value = e.target.value;
		document.boardCommentDelFrm.submit();
	}
});



// tbody > tr > td > .btn-reply
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
		frm.action = "<%=request.getContextPath()%>/qna/qnaNoticeComment";
		frm.method = "POST";
		frm.onsubmit = commentSubmitHandler; // 동적생성한 요소는 핸들러바인딩도 새로 해야한다.
		
		const inputNoticeNo = document.createElement("input");
		inputNoticeNo.type = "hidden";
		inputNoticeNo.name = "noticeNo";		
		inputNoticeNo.value = "<%=board.getNo()%>"
		
		const inputMemberId = document.createElement("input");
		inputMemberId.type = "hidden";
		inputMemberId.name = "memberId";
		inputMemberId.value = "<%=loginMember != null ? loginMember.getMemberId() : ""%>";
		
		const inputNickName = document.createElement("input");
		inputNickName.type = "hidden";
		inputNickName.name = "nickName";
		inputNickName.value = "<%= loginMember != null ? loginMember.getNickname() : "" %>";
		
		
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
		button.innerText = "등록"
		
		frm.append(inputNoticeNo);
		frm.append(inputMemberId);
		frm.append(inputNickName);
		frm.append(inputCommentLevel);
		frm.append(inputCommentRef);
		frm.append(textarea);
		frm.append(button);
		td.append(frm);
		tr.append(td);
		
		// console.log(tr);
		// console.dir(td);
		
		// 1.부모요소 tbody
		const parent = e.target.parentElement.parentElement.parentElement;
		// console.log(parent); // td - tr - tbody
		// 2.기준요소 다음tr태그 
		const ref = e.target.parentElement.parentElement.nextElementSibling;
		// console.log(ref);
		
		// 생성된 tr 추가
		parent.insertBefore(tr, ref);
		
		// 이벤트핸들링은 1회만 허용.
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
<form action="<%=request.getContextPath()%>/qna/qnaNoticeDelete"
	name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%=board.getNo()%>" />
</form>
<script>

const deleteBoard = () => {
	if(confirm("정말 이 게시글을 삭제하시겠습니까?"))
		document.boardDeleteFrm.submit();
};	

const updateBoard = () => {
	location.href = "<%=request.getContextPath()%>/qna/qnaNoticeUpdate?no=<%=board.getNo()%>";
}
</script>
<%
}
%>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>
