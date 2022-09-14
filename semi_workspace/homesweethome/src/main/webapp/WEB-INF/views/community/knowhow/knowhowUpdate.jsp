<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
 <%@page import="community.model.dto.Attachment"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.KnowhowExt"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/knowhowList.css" />
 <%
 KnowhowExt knowhow = (KnowhowExt) request.getAttribute("knowhow");
%>

 <section id="knowhow-up-container">
<form 
	name="boardUpdateFrm" 
	action="<%=request.getContextPath() %>/knowhow/knowhowUpdate" 
	method="post"
	enctype="multipart/form-data">
	<input type="hidden" name="no" value="<%= knowhow.getNo() %>" />
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title"  class="kh-title" value="<%= knowhow.getTitle() %>" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="hidden" name="memberId" value="<%= knowhow.getMemberId() %>" readonly/>
			<input type="text" name="nickName"  class="kh-nickname" value="<%= knowhow.getNickName() %>" readonly/>
		</td>
	</tr>
	
	<tr>
		<th>첨부파일</th>
		<td >
	<%
		List<Attachment> attachments = knowhow.getAttachments();
		if(attachments != null && !attachments.isEmpty()){
			for(int i = 0; i < attachments.size(); i++){
				Attachment attach = attachments.get(i);
	%>
			<img src="<%= request.getContextPath() %>/images/file.png" width="16px">
			<%= attach.getOriginalFilename()%>
			<input type="checkbox" name="delFile" id="delFile<%= i + 1 %>" value="<%= attach.getNo() %>"/>
			<label for="delFile<%= i + 1 %>">삭제</label>
			<br />
	<%
			}
		}
	%>
<div class="filebox">
    <input class="upload-name" value="1회 1장 첨부" >
    <label for="file">첨 부</label> 
    <input type="file" id="file" name="upFile1">
    <input type="file" id="file" name="upFile2">
</div>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td>
			<textarea rows="5" cols="40"  class="kh-content" name="content"><%= knowhow.getContent() %></textarea>
		</td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" class="kh-update" value="수정"/>
			<input type="button" class="kh-cancle" value="취소" onclick="history.go(-1);"/>
		</th>
	</tr>
</table>
</form>
</section>

<script>
$("#file").on('change',function(){
	  var fileName = $("#file").val();
	  $(".upload-name").val(fileName);
	});


</script>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>