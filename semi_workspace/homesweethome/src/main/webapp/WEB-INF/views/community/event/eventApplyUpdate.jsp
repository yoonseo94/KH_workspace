<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
 <%@page import="community.model.dto.EventAppExt"%>
 <%@page import="community.model.dto.EventAppAtt"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%
	EventAppExt eventapp = (EventAppExt) request.getAttribute("eventapp");
%>
<link rel="stylesheet"href="<%=request.getContextPath()%>/css/community/event.css" />

<section id="update-eventApply">
<form 
	name="boardUpdateFrm" 
	action="<%=request.getContextPath() %>/event/eventApplyUpdate" 
	method="post"
	enctype="multipart/form-data">
<input type="hidden" name="no" value="<%= eventapp.getNo() %>" />
<table id="tbl-board-view">

	<tr>
		<th>이벤트 제목</th>
		<td><input type="text" name="eventapplyCode" class="eau-title" value="<%=eventapp.getEventapplyCode()%>" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="hidden" name="memberId" value="<%= eventapp.getMemberId() %>" readonly/>
			<input type="text" name="nickName"  class="eau-nickname" value="<%= eventapp.getNickName()%>" readonly/>
		</td>
	</tr>
	
<tr>
		<th>첨부파일</th>
		<td >
	<%
		List<EventAppAtt> attachments = eventapp.getAttachments();
		if(attachments != null && !attachments.isEmpty()){
			for(int i = 0; i < attachments.size(); i++){
				EventAppAtt attach = attachments.get(i);
	%>
			<img src="<%=request.getContextPath() %>/images/file.jpg" width="16px">
			<%=attach.getOriginalFilename()%>
			<input type="checkbox" name="delFile" id="delFile<%= i + 1 %>" value="<%= attach.getNo() %>"/>
			<label for="delFile<%= i + 1 %>">삭제</label>
			<br />
	<%
			}
		}
	%>
	<!-- 		<input type="file" name="upFile1" value="">
			<input type="file" name="upFile2" value=""  class="file"> -->
			<div class="filebox">
    <input class="upload-name" value="1회 1장 첨부" >
    <label for="file">첨 부</label> 
    <input type="file" id="file" name="upFile1">
</div>
		</td>
	</tr>

	<tr>
		<th>내 용</th>
		<td>
			<textarea rows="5" cols="40" name="content" class="eau-content"><%= eventapp.getContent() %></textarea>
		</td>
	</tr>
	
	<label for="eventNo"  class="eventNo"><b>이벤트 선택</b></label>
<select name="eventNo"  id="eventNo" >
<option>---이벤트번호 변경 불가---</option>
<option value="102"  selected>꽃테리어 콘테스트 (진행중)</option>
<option value="101">핸드메이드 콘테스트 (종료)</option>
<option value="81">정리챌린지 (종료)</option>
</select>
	<tr>
		<th colspan="2">
			<input type="submit"  class="eau-btn-modi" value="수정하기"/>
			<input type="button"  class="eau-btn-no"value="취소" onclick="history.go(-1);"/>
		</th>
	</tr>
</table>
</form>
</section>


<script>
eventNo.addEventListener('change',(e)=>{
	const{value}  = e.target;
	console.log(value);
});
</script>

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
	//내용을 작성하지 않은 경우 폼제출할 수 없음.
	const contentVal = frm.content.value.trim();
	if(!/^(.|\n)+$/.test(contentVal)){
		alert("내용을 작성해주세요.");
		frm.content.select();
		return false;
	}
}
$("#file").on('change',function(){
	  var fileName = $("#file").val();
	  $(".upload-name").val(fileName);
	});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>