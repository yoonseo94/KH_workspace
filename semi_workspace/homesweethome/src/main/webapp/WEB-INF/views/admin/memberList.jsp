<%@page import="java.util.List"%>
<%@ page import="member.model.dto.Member, java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>
<%@ include file="/WEB-INF/views/common/communitysubmenu.jsp" %>
<%
	List<Member> memberList = (List<Member>) request.getAttribute("memberList");
	String pagebar = (String) request.getAttribute("pagebar");
	
	String searchType = request.getParameter("searchType");
	String searchKeyword = request.getParameter("searchKeyword");
	System.out.println("searchType = " + searchType);
	System.out.println("searchKeyword = " +searchKeyword);
%>
<link rel="stylesheet" href="<%=request.getContextPath() %>/css/memberList.css" />

<section id="memberList-container">
	<div id="title-wrapper">
		<h2 id="cont-title">회원관리</h2>	
	</div>
	<div id="search-container">
    	<label for="searchType">검색타입 :</label> 
        <select id="searchType">
            <option value="member_id" <%="member_id".equals(searchType)?"selected":""%>>아이디</option>		
			<option value="member_name" <%="member_name".equals(searchType)?"selected":""%>>회원명</option>
        </select>
        <div id="search-memberId" class="search-type">
            <form class="search-frm" action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_id"/>
                <input type="text" class="input-val" name="searchKeyword"  size="25" placeholder="검색할 아이디를 입력하세요." value="<%= "member_id".equals(searchType) ? searchKeyword : "" %>"/>
                <button class="btn-finder" type="submit">검색</button>			
            </form>	
        </div>
        <div id="search-memberName" class="search-type">
            <form class="search-frm" action="<%=request.getContextPath()%>/admin/memberFinder">
                <input type="hidden" name="searchType" value="member_name"/>
                <input type="text" class="input-val" name="searchKeyword" size="25" placeholder="검색할 회원명을 입력하세요." value="<%= "member_name".equals(searchType) ? searchKeyword : "" %>"/>
                <button class="btn-finder" type="submit">검색</button>			
            </form>	
        </div>
    </div>
        <hr class="hr1" />	
	<table id="tbl-member">
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>회원권한</th>
				<th>성별</th>
				<th>생년월일</th>
				<th>이메일</th>
				<th>전화번호</th>
				<th>가입일</th>
				<th>강제탈퇴</th>
			</tr>
		</thead>
		<tbody>
		
<%
		if(memberList != null && !memberList.isEmpty()){
				Member member = new Member();
			for(int i = 0; i < memberList.size(); i++){
				member = memberList.get(i);
%>				
			<tr>
				<td><%= i + 1 %></td>
				<td><%= member.getMemberId() %></td>
				<td><%= member.getMemberName() %></td>
				<td>
					<select class="member-role" data-member-id="<%= member.getMemberId() %>">
						<option value="<%= MemberRole.A %>" <%= member.getMemberRole() == MemberRole.A ? "selected" : "" %>>관리자</option>
						<option value="<%= MemberRole.U %>" <%= member.getMemberRole() == MemberRole.U ? "selected" : "" %>>일반회원</option>
					</select>
				</td>
				<td><%= member.getGender() != null ? member.getGender() : "" %></td>
				<td><%= member.getBirthday() != null ? member.getBirthday() : "" %></td>
				<td><%= member.getEmail() != null ? member.getEmail() : "" %></td>
				<td><%= member.getPhone() %></td>
				<td><%= member.getEnrollDate() %></td>
				<td><button type="button" class="btn-forced-withdrawal" id="<%= i + 1 %>" name="<%= member.getMemberId() %>" >강제탈퇴</button></td>
			</tr>			
<%
			}
		}
		else {
%>			
			<tr>
				<td colspan="9">조회된 회원이 없습니다.</td>
			</tr>
<%
		}
%>		
		</tbody>
	</table>
	<hr class="hr1" />	
	<div id="pagebar">
		<%= pagebar %>
	</div>
</section>
<form 
	action="<%= request.getContextPath() %>/admin/memberRoleUpdate" 
	name="updateMemberRoleFrm"
	method="POST">
	<input type="hidden" name="memberId" />
	<input type="hidden" name="memberRole" />
</form>
<form 
	action="<%= request.getContextPath() %>/member/memberDelete" 
	name="withdrawMemberFrm"
	method="POST">
	<input type="hidden" name="memberId" />
</form>

<script>


	$(".btn-forced-withdrawal").click(function(){                	      
			const checkBtn = $(this);
			const tr = checkBtn.parent().parent();    
			const td = tr.children();    
			const memberId = td.eq(1).text();   
			const memberName = td.eq(2).text();         
			
		 	if(confirm(`정말로 [\${memberId} : \${memberName}] 회원을 강제로 탈퇴시키시겠습니까?`)){
			 	const frm = document.withdrawMemberFrm;
				frm.memberId.value = memberId;
				frm.submit(); 
			} 
		});

window.onload = () => {
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	}); 
	
	document.querySelector("#search-memberId").style.display = "inline-block";
}; 

searchType.addEventListener('change', (e) => {
	const {value} = e.target;  
	
	document.querySelectorAll(".search-type").forEach((div) => {
		div.style.display = "none";
	}); 
	
	let id = "";
	switch(value){
		case "member_id": id = "search-memberId"; break; 
		case "member_name": id = "search-memberName"; break; 
	}
	document.querySelector(`#\${id}`).style.display = "inline-block";
});


document.querySelectorAll(".member-role").forEach((select) => {
	select.addEventListener('change', (e) => {
		const memberId = e.target.dataset.memberId;
		const memberRole = e.target.value;

		if(confirm(`[\${memberId}]의 권한을 [\${memberRole}]로 변경하시겠습니까?`)){
			const frm = document.updateMemberRoleFrm;
			frm.memberId.value = memberId;
			frm.memberRole.value = memberRole;
			frm.submit();
		}
		else {
			e.target.querySelector("[selected]").selected = true;
		}
		
	});
});


</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>