<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@page import="community.model.dto.EventAttachment"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.EventExt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/event.css" />
<%
	EventExt event = (EventExt) request.getAttribute("event");
%>


<section id="event-update-container">
<h2>이벤트 수정하기</h2>
<form 
	name="boardUpdateFrm" 
	action="<%=request.getContextPath() %>/event/eventUpdate" 
	method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="no" value="<%= event.getNo() %>" />
	
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" class="title_part"name="title" value="<%= event.getEventTitle()%>" required></td>
	</tr>
	
	<tr>
		<th>첨부파일</th>
		<td >
<%
		List<EventAttachment> attachments = event.getAttachments();
		if(attachments != null && !attachments.isEmpty()){
			for(int i = 0; i < attachments.size(); i++){
				EventAttachment attach = attachments.get(i);
	%>
			<img src="<%= request.getContextPath() %>/images/file.jpg" width="16px">
			<%= attach.getOriginal_filename()%>
			<input type="checkbox" name="delFile" id="delFile<%= i + 1 %>" value="<%= attach.getNo() %>"/>
			<label for="delFile<%= i + 1 %>">삭제</label>
			<br />
	<%
			}
		}
	%>
			<div class="filebox">
    <input class="upload-name" value="1회 1장 첨부">
    <label for="file">첨 부</label> 
    <input type="file" id="file" name="upFile1">
		</td>
	</tr>
	
	
	<tr>
		<th>내 용</th>
		<td>
			<textarea rows="5" cols="40" name="content" class="content"><%= event.getEventContent() %></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" class="btn-updupd" value="수정하기"/>
			<input type="button" value="취소" class="btn-cancel"onclick="history.go(-1);"/>
		</th>
	</tr>
</table>
</form>
</section>
<script>
/**
 * 업로드 가능한 첨부파일 수 제한하기
 */
const len = document.querySelectorAll("[name=delFile]").length
for(let i = 0; i < len; i++)
	document.querySelectorAll("input[type=file]")[i].style.display = "none";
	
/**
 * [name=delFile] 체크/체크해제시 input[type=file] 노출/감춤처리
 */
document.querySelectorAll("[name=delFile]").forEach((delFile) => {
	delFile.onchange = (e) => {
		const {id, checked} = e.target;
		// console.log(id, checked);
		const n = id.replaceAll(/[^0-9]/g, "");
		const file = document.querySelector(`[name=upFile\${n}]`);
		file.style.display = checked ? "inline" : "none"; // 노출/감춤 처리
		checked || (file.value = ""); // 지정한 파일을 제거 
	};
});


document.boardUpdateFrm.onsubmit = (e) => {
	const frm = e.target;
	//제목을 작성하지 않은 경우 폼제출할 수 없음.
	const titleVal = frm.title.value.trim(); // 좌우공백
	if(!/^.+$/.test(titleVal)){
		alert("제목을 작성해주세요.");
		frm.title.select();
		return false;
	}		
					   
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	const contentVal = frm.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
		alert("내용을 작성해주세요.");
		frm.content.select();
		return false;
	}
}


</script>


<%@ include file="/WEB-INF/views/common/footer.jsp" %>