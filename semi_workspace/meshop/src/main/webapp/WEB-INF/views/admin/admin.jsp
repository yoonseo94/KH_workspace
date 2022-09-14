<%@page import="java.util.List"%>
<%@page import="com.meshop.product.entity.*"%>
<%@page import="com.meshop.member.entity.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
<title>관리자 페이지</title>
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/main.css" />
  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin.css" />
</head>
<body>
	<%@ include file="/WEB-INF/views/common/header.jsp" %>
	<div class="frame">
        <div class="admin-menu">
            <p>관리자 페이지</p>
            <ul class="admin-menu-list">
                <li><a id="memberBtn">회원 관리</a></li>
                <li><a id="reportBtn">신고 관리</a></li>
            </ul>
        </div>
        <div class="table-section">
            <table id="tbl-admin">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>ID</th>
                        <th>Store</th>
                        <th>게시글 수</th>
                        <th>평점</th>
                        <th>가입 날짜</th>
                    </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
        <script>
        memberBtn.addEventListener('click',(e)=>{
            //회원 관리 진하게
            e.target.style.fontWeight = "700";
            
            //신고 관리 연하게
            const reportBtn = document.querySelector('#reportBtn');
            reportBtn.style.fontWeight = "500";

            // 비동기 통신
            $.ajax({
                url : "<%=request.getContextPath() %>/admin/member",
                method : "GET",
                success(response){
                    console.log(response);
                    const html = getMemberTableHTML(response);
                    document.querySelector('.table-section').innerHTML = html;
                },
                error : console.log
            });
        });
        const getMemberTableHTML = (response)=>{
            let html = `
            <table id="tbl-admin">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>Store_Name</th>
                        <th>Name</th>
                        <th>게시글 수</th>
                        <th>평점</th>
                        <th>가입 날짜</th>
                    </tr>
                </thead>
                <tbody>
            `;
            //response => List
            response.forEach((member)=>{
                //구조 분해 할당
                const {memberId, memberName, storeName, storeGrade, BoardCount, joinDate} = member;
                let tr = "<tr>";
                    tr += `<td>\${memberId}</td>`;
                    tr += `<td>\${storeName}</td>`;
                    tr += `<td>\${memberName}</td>`;
                    tr += `<td>\${BoardCount}</td>`;
                    tr += `<td>\${storeGrade}</td>`;
                    tr += `<td>\${joinDate}</td>`;
                    tr += "</tr>";
                html += tr;
            });
            html += `</tbody></table>`;

            return html;
        };

        reportBtn.addEventListener('click',(e)=>{
            //회원 관리 진하게
            e.target.style.fontWeight = "700";
            
            //신고 관리 연하게
            const memberBtn = document.querySelector('#memberBtn');
            memberBtn.style.fontWeight = "500";

            $.ajax({
                url : "<%=request.getContextPath() %>/admin/report",
                method : "GET",
                success(response){
                    console.log(response);
                    const html = getReportTableHTML(response);
                    document.querySelector('.table-section').innerHTML = html;
                },
                error : console.log
            });
        });
        const getReportTableHTML = (response)=>{
            let html = `
            <table id="tbl-admin">
                <thead>
                    <tr>
                        <th>Index</th>
                        <th>게시글 ID</th>
                        <th>신고 게시글</th>
                        <th>신고 누적 횟수</th>
                        <th>비고</th>
                        <th>비활성화</th>
                    </tr>
                </thead>
                <tbody>
            `;
            //response => List
            response.forEach((report,index)=>{
            	//ContextPath
            	const cp = "<%= request.getContextPath() %>";
                //구조 분해 할당
                 const {product, count} = report;
                 const {productId, title} = product;
                 console.log(productId, title);
                 let tr = "<tr>";
                     tr += `<td>\${index+1}</td>`;
                     tr += `<td>\${productId}</a></td>`;
                     tr += `<td><a href="\${cp}/admin/report/\${productId}">\${title}</a></td>`;
                     tr += `<td>\${count}</td>`;
                     tr += `<td>신고 유형</td>`;
                     tr += `<td>비활성화</td>`;
                     tr += "</tr>";
                 html += tr;
            });
            html += `</tbody></table>`;

            return html;
        };
        window.addEventListener('load',()=>{
            //헤더 높이 구하기
            const header = document.querySelector('.header');

            //메인 컨텐츠의 padding top 높이 조절하기
            const frame = document.querySelector('.frame');
            frame.style.paddingTop = `\${header.offsetHeight}px`;
        });
    </script>
	<%@ include file="/WEB-INF/views/common/footer.jsp" %>