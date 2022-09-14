<%@page import="notice.model.dto.NoticeExt"%>
<%@page import="notice.model.dto.NoticeAttachment"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/board.css" />
<%
NoticeExt notice = (NoticeExt)request.getAttribute("notice");	
	boolean canEdit = loginMember != null 
	&& (loginMember.getMemberId().equals(notice.getMemberId())
	|| loginMember.getMemberRole() == MemberRole.A);
%>
<section id="" class="notice_container_view section">
<div style="margin-top:100px;"></div>
	<div class="container">
		<div class="row">
			<span style="font-weight:bold">공지사항</span>	
			<table class="tbl_view table table-bordered">
				<tbody>
					<tr>
						<th style="width: 20%;">제목</th>
						<td colspan="2"><%=notice.getNoticeTitle()%></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td colspan="2"><%=notice.getMemberId()%></td>
					</tr>
					<tr>
						<th>내용</th>
						<td nowrap height="200" colspan="2"><textarea cols="116" rows="20" style="border: none; resize: none;" readonly><%=notice.getNoticeContent()%></textarea></td>
					</tr>
					<tr>
						<th>조회수</th>
						<td><%=notice.getNoticeReadCount()%></td>
					</tr>

					<tr id="th_file" style="text-align:right" >
					<%

						List<NoticeAttachment> attachments = notice.getNoticeAttachments();
							if(attachments != null && !attachments.isEmpty()){
								for(NoticeAttachment attach : attachments){
					%>
						<td colspan="3">
						
							<%-- 첨부파일이 있을경우만, 이미지와 함께 original파일명 표시 --%>
							<img alt="첨부파일" src="<%=request.getContextPath() %>/images/file.png" width=16px>
							<a href="<%= request.getContextPath() %>/notice/fileDownload?no=<%= attach.getNoticeAttachmentNo() %>"><%= attach.getOriginalFilename() %></a>
						</td>
					</tr>
					<%
							}
						}
					%>
					</div>
					</tbody>
					<% if(canEdit){ %>
					<tr>
						<th colspan="2" style="text-align: end">
							<input type="button" id="delete_btn" class="view_btn btn btn-primary" value="삭제하기" onclick="deleteNotice()">
							<input type="button" class="view_btn btn btn-primary" value="수정하기" onclick="updateNotice()">
						</th>
					</tr>
					<% } %>
			</table>
	</div>
<div style="margin-bottom:100px;"></div>
</section>
<% if(canEdit){ %>
<form action="<%= request.getContextPath() %>/notice/noticeDelete" name="noticeDeleteFrm" method="POST">
	<input type="hidden" name="no" value="<%= notice.getNoticeNo() %>" />
</form>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<script>
$().ready(function () {
    $("#delete_btn").click(function () {
        Swal.fire({
            title: '정말로 삭제하시겠습니까?',
            text: "다시 되돌릴 수 없습니다. 신중하세요.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
            	document.noticeDeleteFrm.submit();
            }
        })
    });
});
const updateNotice = () => {
	location.href = "<%= request.getContextPath() %>/notice/noticeUpdate?no=<%= notice.getNoticeNo() %>";
}
</script>
<% } %>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      