<%@page import="community.model.dto.Attachment"%>
<%@page import="community.model.dto.QnaBoardComment"%>
<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member"%>
<%@page import="community.model.dto.QnaBoardExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<%
	QnaBoardExt board = (QnaBoardExt) request.getAttribute("board");
	QnaBoardComment qc = new QnaBoardComment();
	List<QnaBoardComment> comments = board.getBoardComments();

	boolean canEdit = loginMember != null 
			&& (loginMember.getNickname().equals(board.getNickName()) 
					|| loginMember.getMemberRole() == MemberRole.A);	
	
%>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/qnaboard.css" />
<section id="qnaboard-view-container">
	<table id="tbl-board-view">
	
	<div class="title-view">
	<h4>질문과 답변</h4>
<h1><%= board.getTitle() %></h1>
<h2><%= board.getNickName() %></h2>
<h2><%=board.getRegDate() %></h2>
</div>

<div class="content-view">
<%= board.getContent() %><br>

		<% 
			List<Attachment> attachments = board.getAttachments();
			if(attachments != null && !attachments.isEmpty()){
				for(Attachment attach : attachments){
		%>
				<img src="<%=request.getContextPath()%>/upload/community/qna/<%=attach.getRenamedFilename()%>" width="320px"><br>
		<%
				}
			}
		%>
<div class="view-end">
 No.<%= board.getNo() %>&nbsp;&nbsp;
조회 <c><%= board.getReadCount() %></c>
</div>
</div>
		<% if(canEdit){ %>

			<%-- 작성자와 관리자만 마지막행 수정/삭제버튼이 보일수 있게 할 것 --%>
				<input type="button"  class="btn-upd" value="수정하기" onclick="updateBoard()">
				<input type="button" class= "btn-del" value="삭제하기" onclick="deleteBoard()">
		<% } %>
	</table>
	
	<br style="margin-top:16rem;" />	
    
	<div class="comment-container">
        <div class="comment-editor">
            <form
				action="<%=request.getContextPath()%>/qna/qnaBoardComment" method="post" name="boardCommentFrm">
                <input type="hidden" name="boardNo" value="<%= board.getNo() %>" />
                <input type="hidden" name="memberId" value="<%= loginMember != null ? loginMember.getMemberId() : "" %>" />
                <input type="hidden" name="nickName" value="<%= loginMember != null ? loginMember.getNickname() : "" %>" />
                <input type="hidden" name="commentLevel" value="1" />
                <input type="hidden" name="commentRef" value="0" />    
				<textarea name="content"  class="conmment-content" cols="60" rows="3" placeholder="댓글을 남겨 보세요."></textarea>
                <button type="submit" id="btn-comment-enroll1" >입력</button>
            </form>
        </div>

		<% if (comments != null && !comments.isEmpty()) {%>
		<table id="tbl-comment">
			<tbody>
			<% 
				for (QnaBoardComment bc : comments){ 
					
					boolean canDelete = loginMember != null 
							&& (loginMember.getNickname().equals(bc.getNickName()) 
									|| loginMember.getMemberRole() == MemberRole.A);
							
					System.out.println("bc.getCommentLevel() = " + bc.getCommentLevel());
					if(bc.getCommentLevel() == 1){
			%>
				<tr class="level1">
					<td>
						<sub class="comment-writer"><%= bc.getNickName() != null ? bc.getNickName() : "(탈퇴회원)" %></sub>
						<sub class="comment-date"><%= bc.getRegDate() %></sub>
						<br />
						<%= bc.getContent() %>
					</td>
					<td>
						<button class="btn-reply" value="<%= bc.getNo() %>">답글</button>
						<% if(canDelete){ %>
							<button class="btn-delete" value="<%= bc.getNo() %>">삭제</button>
						<% } %>
					</td>
				</tr>
			<% 		
			} else { 
			%>
				<tr class="level2">
					<td>
						<sub class="comment-writer"><%= bc.getNickName() != null ? bc.getNickName(): "(탈퇴회원)" %></sub>
						<sub class="comment-date"><%= bc.getRegDate() %></sub>
						<br />
						<%= bc.getContent() %>
					</td>
					<td>
																							
						<% if(canDelete){ %>
							<button class="btn-delete" value="<%= bc.getNo()%>">삭제</button>
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



<%-- @실습문제 - .btn-delete 클릭핸들러를 통해 boardCommentDelFrm 동적으로 전송 --%>
<form action="<%=request.getContextPath()%>/qna/qnaBoardCommentDelete" name="boardCommentDelFrm" method="POST">
	<input type="hidden" name="no" />
	<input type="hidden" name="boardNo"   value="<%= board.getNo() %>"/>
</form>


<script>
$(document).ready(function(){
    $("#likeUpDown").click(function(){
		$.ajax({
			url: "<%= request.getContextPath() %>/qna/qnaBoardView?no=<%=board.getNo()%>",
			method: "POST", 
			dataType: "text", 
			data:  {"memberId": $("#likememberId").val(),
					"cono" : $("#likeno").val()
				}, 
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
		if(<%= loginMember == null %>){
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
		frm.action = "<%=request.getContextPath()%>/qna/qnaBoardComment";
		frm.method = "POST";
		frm.onsubmit = commentSubmitHandler; // 동적생성한 요소는 핸들러바인딩도 새로 해야한다.
		
		const inputBoardNo = document.createElement("input");
		inputBoardNo.type = "hidden";
		inputBoardNo.name = "boardNo";		
		inputBoardNo.value = "<%= board.getNo() %>"
		
		const inputMemberId = document.createElement("input");
		inputMemberId.type = "hidden";
		inputMemberId.name = "memberId";
		inputMemberId.value = "<%= loginMember != null ? loginMember.getMemberId() : "" %>";
		
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
	if(<%= loginMember == null %>)
		loginAlert();
};

const commentSubmitHandler = (e) => {
	if(<%= loginMember == null %>){
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

<% if(canEdit){ %>
<form action="<%= request.getContextPath() %>/qna/qnaBoardDelete" name="boardDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= board.getNo() %>" />
</form>
<script>
/**
 * POST /board/boardDelete
 * - no전송
 * - 저장된 파일 삭제 : java.io.File 
 */
const deleteBoard = () => {
	if(confirm("정말 이 게시글을 삭제하시겠습니까?"))
		document.boardDeleteFrm.submit();
};	

const updateBoard = () => {
	location.href = "<%= request.getContextPath() %>/qna/qnaBoardUpdate?no=<%= board.getNo() %>";
}
</script>
<% } %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>