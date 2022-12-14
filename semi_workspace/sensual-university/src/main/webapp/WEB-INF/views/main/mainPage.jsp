<%@page import="notice.model.dto.NoticeExt"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<%
	List<NoticeExt> list = (List<NoticeExt>) request.getAttribute("list");
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/mainPage.css" />
		<section id="section_slide">
			<div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
			  <div class="carousel-indicators">
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
			    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
			  </div>
			  <div class="carousel-inner">
			    <div class="carousel-item active" data-bs-interval="10000">
			      <img src="<%= request.getContextPath() %>/images/university.jfif" class="d-block w-100" alt="">
			    </div>
			    <div class="carousel-item" data-bs-interval="2000">
			      <img src="<%= request.getContextPath() %>/images/flash.PNG" class="d-block w-100" alt="">
			    </div>
			    <div class="carousel-item">
			      <img src="<%= request.getContextPath() %>/images/university2.png" class="d-block w-100" alt="">
			    </div>
			  </div>
			  <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
			    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Previous</span>
			  </button>
			  <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
			    <span class="carousel-control-next-icon" aria-hidden="true"></span>
			    <span class="visually-hidden">Next</span>
			  </button>
			</div>
</section>
<section id="section" class="section" >
	<div class="info">
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.S) { %>
		<div id="s_info">
			<p id="p_welcome"><span class="span_name"><%= loginMember.getMemberName() %></span>???, ???????????????!</p>
			<p>???&nbsp????????? : <%= loginMember.getMemberId() %></p>
			<p>???&nbsp?????? : <%= loginMember.getMemberNo() %></p><p>???&nbsp<%= loginMember.getMemberLevel() %>?????? ?????????</p>
		</div>
	<% } %>
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.A) { %>
		<div class="a_p_welcome">
			<p><span class="span_name">?????????</span>???, <br />???????????????!</p>
		</div>
	<% } %>
	<% if(loginMember != null && loginMember.getMemberRole() == MemberRole.P) { %>
		<div class="a_p_welcome">
			<p><span class="span_name"><%= loginMember.getMemberName() %></span>?????????, <br />???????????????!</p>
		</div>
	<% } %>
	</div>
	<div class="calendar">
		<p>Calendar View</p>
		<input type="text" id="datepicker">
	</div>
	<div class="main_notice_list">
		<p>Notice View</p>
			<h5><a href="<%= request.getContextPath()%>/notice/noticeList">MORE ></a></h5>
			
			<table id="" class="tbl_list table">
			<tbody>
			<%
			if(list != null && !list.isEmpty()) {
				for(NoticeExt notice : list) {
			%>
			<tr onclick="location.href='<%= request.getContextPath() %>/notice/noticeView?no=<%= notice.getNoticeNo() %>';">
				<td>???</td>
				<td>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp<%= notice.getNoticeTitle() %></td>
				<td><%= notice.getNoticeDate() %></td>
			</tr>
			<%
				}
			} else {
			%>
				<p> ????????? ????????? ????????????.</p>
			<%
				}
			%>
			</tbody>
		</table>
			<div style="text-align:center" id='pageBar'><%= request.getAttribute("pagebar") %></div>
	</div>
</section>
<script>
$("#datepicker").datepicker();                    
$("#datepicker2").datepicker();
		$(function() {
		//input??? datepicker??? ??????
		    $("#datepicker").datepicker({
		                dateFormat: 'yy-mm-dd' //Input Display Format ??????
		                ,showOtherMonths: true //??? ????????? ???????????? ???????????? ????????? ??????
		                ,showMonthAfterYear:true //?????? ?????? ?????????, ?????? ??? ??????
		                ,changeYear: true //?????????????????? ??? ?????? ??????
		                ,changeMonth: true //?????????????????? ??? ?????? ??????                
		                ,showOn: "both" //button:????????? ????????????,????????? ???????????? ?????? ?????? ^ both:????????? ????????????,????????? ???????????? input??? ???????????? ?????? ??????  
		                ,buttonImage: "http://jqueryui.com/resources/demos/datepicker/images/calendar.gif" //?????? ????????? ??????
		                ,buttonImageOnly: true //?????? ????????? ?????? ????????? ?????????, ???????????? ????????? ???
		                ,buttonText: "??????" //????????? ????????? ?????? ?????? ??? ???????????? ?????????                
		                ,yearSuffix: "???" //????????? ?????? ?????? ?????? ?????? ?????????
		                ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //????????? ??? ?????? ?????????
		                ,monthNames: ['1???','2???','3???','4???','5???','6???','7???','8???','9???','10???','11???','12???'] //????????? ??? ?????? Tooltip ?????????
		                ,dayNamesMin: ['???','???','???','???','???','???','???'] //????????? ?????? ?????? ?????????
		                ,dayNames: ['?????????','?????????','?????????','?????????','?????????','?????????','?????????'] //????????? ?????? ?????? Tooltip ?????????
		                ,minDate: "-100Y" //?????? ????????????(-1D:?????????, -1M:?????????, -1Y:?????????)
		                ,maxDate: "+100Y" //?????? ????????????(+1D:?????????, -1M:?????????, -1Y:?????????) 
		    
		            });                    
		            
		            //???????????? ?????? ????????? ??????
		            $('#datepicker').datepicker('setDate', 'today'); //(-1D:?????????, -1M:?????????, -1Y:?????????), (+1D:?????????, -1M:?????????, -1Y:?????????)            
		        });
		        
		        $.datepicker.setDefaults({
		            dateFormat: 'yy-mm-dd' //Input Display Format ??????
		        });
		        $.datepicker.setDefaults({
		            showOtherMonths: true //??? ????????? ???????????? ???????????? ????????? ??????
		        });
		        $.datepicker.setDefaults({
		            showOn: "button" //button:????????? ????????????,????????? ???????????? ?????? ?????? ^ both:????????? ????????????,????????? ???????????? input??? ???????????? ?????? ??????      
		        });
		        $.datepicker.setDefaults({
		            changeYear: true //?????????????????? ??? ?????? ??????
		            ,changeMonth: true //?????????????????? ??? ?????? ??????            
		        });
    </script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>