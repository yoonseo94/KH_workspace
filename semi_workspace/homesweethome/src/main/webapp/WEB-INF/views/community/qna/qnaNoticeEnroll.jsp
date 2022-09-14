<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/qnaboard.css" />

<script>
/**
* boardEnrollFrm 유효성 검사
*/
window.onload = () => {	
	document.qnanoticeEnrollFrm.onsubmit = (e) => {
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

<section id="notice-enroll-container">

<form
	name="qnanoticeEnrollFrm"
	action="<%=request.getContextPath() %>/qna/qnaNoticeEnroll" 
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title"  class="notice_e_title" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" readonly/>
			<input type="text" name="nickName"   class="notice_nickname" value="<%= loginMember.getNickname() %>" readonly/>
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
		<td><textarea rows="5" cols="40" name="content"  class="notice_content" ></textarea></td>
	</tr>
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기" class="btn-no-go">
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


