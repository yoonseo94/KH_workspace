<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>    
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/qnaboard.css" />
<style>
  .filebox .upload-name {
    display: inline-block;
    height: 40px;
    padding: 0 10px;
    vertical-align: middle;
    border: 1px solid #dddddd;
    width: 78%;
    color: #999999;
}
.filebox label {
    display: inline-block;
    padding: 0.41rem 0.82rem;
    color: #fff;
    vertical-align: middle;
    background-color: #35c5f0;
    opacity:0.87;
    cursor: pointer;
    height: 1.4rem;
    margin-left: 1px;
    margin-top:0.3rem;
}
  .filebox input[type="file"] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
}
  
</style>
<script>
/**
* boardEnrollFrm 유효성 검사
*/
window.onload = () => {	
	document.qnaboardEnrollFrm.onsubmit = (e) => {
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
<section id="board-container">

<form
	name="qnaboardEnrollFrm"
	action="<%=request.getContextPath() %>/qna/qnaBoardEnroll" 
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-board-view">
	<tr>
		<th>제 목</th>
		<td><input type="text"  class="qnatitle"  name="title" required></td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>
			<input type="hidden" name="memberId" value="<%= loginMember.getMemberId() %>" />
			<input type="text" name="nickName"  class="qnanickname" value="<%= loginMember.getNickname() %>" readonly/>
		</td>
	</tr>

<!-- 		<th>첨부파일</th>
		<td>			
			<input type="file" name="upFile1">
			<br>
			<input type="file" name="upFile2">    

		</td> -->			

	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="content" class="qnacontent" ></textarea></td>
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
		<th colspan="2">
			<input type="submit" value="등록하기" class="btn-qa-go">
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
