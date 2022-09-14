<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="community.model.dto.EventApplicants"%>
<%@page import="community.model.dto.EventAppExt"%>
 <%@ include file="/WEB-INF/views/common/header.jsp" %>
 <%
EventAppExt event = (EventAppExt) request.getAttribute("event");
 %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/event.css" />
 
 <script>
 window.onload = () => {	
		document.eventpartEnrollFrm.onsubmit = (e) => {
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
	}
 </script>
 
 <section id="eae-board-container">
 <form
	name="eventpartEnrollFrm"
	action="<%=request.getContextPath()%>/event/eventApplyEnroll"
	method="post"
	enctype="multipart/form-data">
	
	<table id="tbl-board-view">
	<label for="eventNo"  class="eventNo"><b>이벤트 선택</b></label>
<select name="eventNo"  id="eventNo" >
<option selected>------필수선택------</option>
<option value="63">꽃테리어 콘테스트 (진행중)</option>
<option value="62">핸드메이드 콘테스트 (종료)</option>
<option value="61">정리챌린지 (종료)</option>

</select>
			<tr>
		<th>제목</th>
		<td><input type="text" name="eventapplycode"  class="eventapplycode"></td>
	</tr>
	
 	<%-- <input type="hidden" name="eventNo"  value="<%=event.getEventNo()%>" /> --%>
	<tr>
		<th>작성자</th>
		<td>
			<input type="hidden" name="memberId" value="<%= loginMember.getMemberId()%>" />
			<input type="text" name="nickName"  class="eae-nickname" value="<%= loginMember.getNickname()%>" readonly/>
		</td>
	</tr>
	<tr>
		<th>첨부파일</th>
		<td>			
		<div class="filebox">
    <input class="upload-name" value="1회 1장 첨부" >
    <label for="file">첨 부</label> 
    <input type="file" id="file" name="upFile1">
</div>
		</td>
	</tr>
	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="content" class="eae-content"></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기" class="eae-enrollevent"> 
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
	
eventNo.addEventListener('change', (e) => {
	const{value}  = e.target;
	console.log(value);
});
</script>
 <%@ include file="/WEB-INF/views/common/footer.jsp" %>
