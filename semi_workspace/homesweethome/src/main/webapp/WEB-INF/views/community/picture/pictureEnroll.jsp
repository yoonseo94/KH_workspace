<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.PictureExt"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/community/pictureList.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%
PictureExt list = (PictureExt) request.getAttribute("list");
%>




<script>
window.onload = () => {	
	document.knowhowEnrollFrm.onsubmit = (e) => {
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

<section id="pic-enr-container">

<form
	name="pictureEnrollFrm"
	action="<%=request.getContextPath() %>/picture/pictureEnroll"
	method="post"
	enctype="multipart/form-data">
	<table id="tbl-board-view">
	


<label for="categoryShape" ><b>주거형태</b></label>
<select name="categoryShape"  id="categoryShape" >
<option selected>주거형태</option>
<option value="10">원룸/오피스텔</option>
<option value="20">아파트</option>
</select>
	
	
	<tr>
		<th>제 목</th>
		<td><input type="text" name="title" class="pic-enr-title" required></td>
	</tr>
	<tr>

		<th>작성자</th>
		<td>
			<input type="hidden" name="memberId" value="<%=loginMember.getMemberId() %>" />
			<input type="text" name="nickName" class="pic-enr-nick" value="<%=loginMember.getNickname() %>" readonly/>
		</td>
	</tr>
	
	<tr>
		<th>썸네일파일</th>
		<td>			
			<input type="text" name="coverPhoto" class="pic-enr-thumb" >
		</td>
	</tr>
	
	<tr>
		<th>첨부파일</th>
		<td>			
		<div class="filebox">
    <input class="upload-name" value="1회 1장 첨부" >
    <label for="file">첨 부</label> 
    <input type="file" id="file" name="upFile1">
    <input type="file" id="file" name="upFile2">
    <input type="file" id="file" name="upFile3">
</div>
		</td>
	</tr>

	<tr>
		<th>내 용</th>
		<td><textarea rows="5" cols="40" name="content" class="pic-enr-content" ></textarea></td>
	</tr>
	
	<tr>
		<th colspan="2">
			<input type="submit" value="등록하기" class="pic-enr-btn">
		</th>
	</tr>
</table>




</form>
</section>


<script>
categorySpace.addEventListener('change',(e)=>{
	const{value}  = e.target;
	console.log(value);
});

categoryShape.addEventListener('change',(e)=>{
	const{value}  = e.target;
	console.log(value);
});

$("#file").on('change',function(){
	  var fileName = $("#file").val();
	  $(".upload-name").val(fileName);
	});
</script>





<%@ include file="/WEB-INF/views/common/footer.jsp"%>