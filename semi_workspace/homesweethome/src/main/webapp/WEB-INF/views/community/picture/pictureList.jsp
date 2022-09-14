<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/community/pictureList.css" />
<%@ include file="/WEB-INF/views/common/header.jsp"%>
<%@page import="java.util.List"%>
<%@page import="community.model.dto.PictureExt"%>
<%
List<PictureExt> list = (List<PictureExt>) request.getAttribute("list");
%>



<nav id="board_top">
	<h2>사진</h2>

<h5>
<a href="<%=request.getContextPath()%>/community/picture">최신순 |</a>
<a href="<%=request.getContextPath()%>/community/picture/sortByRead">조회수순</a>
</h5>

	<div class="ViewByTheme">
<input type="button" value="전체" id="btn-storage" name="btn-go"
	class="btn-go"
	onclick="location.href='<%=request.getContextPath()%>/community/picture';" />
<input type="button" value="원룸&오피스텔" id="btn-oneoffi" name="btn-go"
	class="btn-go"
	onclick="location.href='<%=request.getContextPath()%>/picture/pictureViewByTheme?catenum=<%=10%>';" />
<input type="button" value="아파트"  id="btn-apart" name="btn-go"
	class="btn-go"
	onclick="location.href='<%=request.getContextPath()%>/picture/pictureViewByTheme?catenum=<%=20%>';" />

     </div>  

</nav>



<div class="write-btn">
	<input type="button" value="사진 올리기" id="btn-write" name="btn-write"
		class="btn-write"
		onclick="location.href='<%=request.getContextPath()%>/picture/pictureEnroll';" />
</div>


<div class="category">
	<%
	if (list == null || list.isEmpty()) {
	%>
	<td>등록된 사진이 없습니다.</td>

	<%
	} else {
	for (PictureExt pic : list) {
	%>
	<a
		href="<%=request.getContextPath()%>/picture/pictureView?no=<%=pic.getImgNo()%>"
		class="thumbnail"> <img
		src="<%=request.getContextPath()%>/upload/community/picture/<%=pic.getCoverPhoto()%>"
		class="thumb_nail"><b><%=pic.getTitle()%></b>

		<p><%=pic.getNickName()%>
			· 조회수
			<%=pic.getReadCount()%>· <img
				src="<%=request.getContextPath()%>/images/like.png" width="17px">
</p>
	</a>
	<%
	}
	}
	%>
</div>

<br>
<script>
/* selectOption.addEventListener('change',(e)=>{
	const{value}  = e.target;
	console.log(value);
}); */

</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>