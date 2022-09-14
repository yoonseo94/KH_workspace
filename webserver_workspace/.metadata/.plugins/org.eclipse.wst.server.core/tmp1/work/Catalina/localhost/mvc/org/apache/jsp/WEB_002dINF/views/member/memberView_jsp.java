/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.62
 * Generated at: 2022-06-08 02:52:49 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.member;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;
import member.model.dto.MemberRole;
import member.model.dto.Member;

public final class memberView_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/views/common/header.jsp", Long.valueOf(1654650331275L));
    _jspx_dependants.put("/WEB-INF/views/common/footer.jsp", Long.valueOf(1652334477701L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("member.model.dto.Member");
    _jspx_imports_classes.add("java.util.Arrays");
    _jspx_imports_classes.add("java.util.List");
    _jspx_imports_classes.add("java.sql.Date");
    _jspx_imports_classes.add("member.model.dto.MemberRole");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	Member loginMember = (Member) session.getAttribute("loginMember");
	// System.out.println("loginMember@header.jsp = " + loginMember);
	
	String msg = (String) session.getAttribute("msg");
	if(msg != null)
		session.removeAttribute("msg");
	
	String saveId = null; // 아이디저장을 체크한 경우, memberId값이 담길 변수
	Cookie[] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie cookie : cookies){
			// System.out.println("Cookie{" + cookie.getName() + "=" + cookie.getValue() + "}");
			if("saveId".equals(cookie.getName())){
				saveId = cookie.getValue();
			}
		}
	}
	else {
		// System.out.println("> 이번 요청에 전달된 cookie가 없습니다.");
	}

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\">\r\n");
      out.write("<title>Hello MVC</title>\r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print(request.getContextPath() );
      out.write("/css/style.css\" />\r\n");
      out.write("<script src=\"");
      out.print( request.getContextPath() );
      out.write("/js/jquery-3.6.0.js\"></script>\r\n");
      out.write("<script>\r\n");
      out.write("window.onload = () => {\r\n");
 if(msg != null){ 
      out.write("\r\n");
      out.write("	alert(\"");
      out.print( msg );
      out.write("\");\r\n");
 } 
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
 if(loginMember == null) { 
      out.write("\r\n");
      out.write("	document.loginFrm.onsubmit = (e) => {\r\n");
      out.write("		const memberIdVal = memberId.value;\r\n");
      out.write("		const passwordVal = password.value;\r\n");
      out.write("		\r\n");
      out.write("		if(!/^.{4,}$/.test(memberIdVal)){\r\n");
      out.write("			alert(\"유효한 아이디를 입력해주세요.\");\r\n");
      out.write("			memberId.select();\r\n");
      out.write("			return false;\r\n");
      out.write("		}\r\n");
      out.write("		if(!/^.{4,}$/.test(passwordVal)){\r\n");
      out.write("			alert(\"유효한 비밀번호를 입력해주세요.\");\r\n");
      out.write("			password.select();\r\n");
      out.write("			return false;\r\n");
      out.write("		}\r\n");
      out.write("	};	\r\n");
 } 
      out.write("\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<div id=\"container\">\r\n");
      out.write("		<header>\r\n");
      out.write("			<h1>Hello MVC</h1>\r\n");
      out.write("			<div class=\"login-container\">\r\n");
      out.write("			");
 if(loginMember == null){ 
      out.write("\r\n");
      out.write("				");
      out.write("\r\n");
      out.write("				<!-- 로그인폼 시작 -->\r\n");
      out.write("				<form id=\"loginFrm\" name=\"loginFrm\" method=\"POST\" action=\"");
      out.print( request.getContextPath() );
      out.write("/member/login\">\r\n");
      out.write("					<table>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td><input type=\"text\" name=\"memberId\" id=\"memberId\" placeholder=\"아이디\" tabindex=\"1\" value=\"");
      out.print( saveId != null ? saveId : "" );
      out.write("\"></td>\r\n");
      out.write("							<td><input type=\"submit\" value=\"로그인\" tabindex=\"3\"></td>\r\n");
      out.write("						</tr>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td><input type=\"password\" name=\"password\" id=\"password\" placeholder=\"비밀번호\" tabindex=\"2\"></td>\r\n");
      out.write("							<td></td>\r\n");
      out.write("						</tr>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td colspan=\"2\">\r\n");
      out.write("								<input type=\"checkbox\" name=\"saveId\" id=\"saveId\" ");
      out.print( saveId != null ? "checked" : "" );
      out.write("/>\r\n");
      out.write("								<label for=\"saveId\">아이디저장</label>&nbsp;&nbsp;\r\n");
      out.write("								<input type=\"button\" value=\"회원가입\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/member/memberEnroll';\">\r\n");
      out.write("							</td>\r\n");
      out.write("						</tr>\r\n");
      out.write("					</table>\r\n");
      out.write("				</form>\r\n");
      out.write("				<!-- 로그인폼 끝-->\r\n");
      out.write("			");
 } else { 
      out.write("\r\n");
      out.write("				");
      out.write("\r\n");
      out.write("				<table id=\"login\">\r\n");
      out.write("					<tbody>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td>");
      out.print( loginMember.getMemberName() );
      out.write("님, 안녕하세요.</td>\r\n");
      out.write("						</tr>\r\n");
      out.write("						<tr>\r\n");
      out.write("							<td>\r\n");
      out.write("								<input type=\"button\" value=\"내정보보기\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/member/memberView';\"/>\r\n");
      out.write("								<input type=\"button\" value=\"로그아웃\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/member/logout';\"/>							\r\n");
      out.write("							</td>\r\n");
      out.write("						</tr>\r\n");
      out.write("					</tbody>\r\n");
      out.write("				</table>\r\n");
      out.write("			");
 } 
      out.write("\r\n");
      out.write("			</div>\r\n");
      out.write("			<!-- 메인메뉴 시작 -->\r\n");
      out.write("			<nav>\r\n");
      out.write("				<ul class=\"main-nav\">\r\n");
      out.write("					<li class=\"home\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("\">Home</a></li>\r\n");
      out.write("					<li class=\"notice\"><a href=\"#\">공지사항</a></li>\r\n");
      out.write("					<li class=\"board\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/board/boardList\">게시판</a></li>\r\n");
      out.write("					<li class=\"photo\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/photo/photoList\">사진게시판</a></li>\r\n");
      out.write("					<li class=\"chat\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/chat/chatroom\">채팅</a></li>\r\n");
      out.write("					");
 if(loginMember != null && loginMember.getMemberRole() == MemberRole.A) { 
      out.write("\r\n");
      out.write("						<li class=\"admin\"><a href=\"");
      out.print( request.getContextPath() );
      out.write("/admin/memberList\">회원관리</a></li>\r\n");
      out.write("					");
 } 
      out.write("\r\n");
      out.write("				</ul>\r\n");
      out.write("			</nav>\r\n");
      out.write("			<!-- 메인메뉴 끝-->\r\n");
      out.write("					\r\n");
      out.write("		</header>\r\n");
      out.write("			\r\n");
      out.write("		<section id=\"content\">");
      out.write('\r');
      out.write('\n');

	String memberId = loginMember.getMemberId();
	String password = loginMember.getPassword();
	String memberName = loginMember.getMemberName();
	Date birthday = loginMember.getBirthday(); // null값이어도 input:datetime에서 무시함.
	String email = loginMember.getEmail() != null ? loginMember.getEmail() : "";
	String phone = loginMember.getPhone();
	String address = loginMember.getAddress() != null ? loginMember.getAddress() : "";
	
	String gender = loginMember.getGender() != null ? loginMember.getGender() : "";
	String hobby = loginMember.getHobby(); // 등산,게임

	// "운동,독서".contains("운동")
	List<String> hobbyList = null;
	if(hobby != null){
		String[] arr = hobby.split(",");
		hobbyList = Arrays.asList(arr); // String[] -> List<String>
	}


      out.write("\r\n");
      out.write("<section id=enroll-container>\r\n");
      out.write("	<h2>회원 정보</h2>\r\n");
      out.write("	<form name=\"memberUpdateFrm\" method=\"post\" action=\"");
      out.print( request.getContextPath() );
      out.write("/member/memberUpdate\">\r\n");
      out.write("		<table>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>아이디<sup>*</sup></th>\r\n");
      out.write("				<td>\r\n");
      out.write("					<input type=\"text\" name=\"memberId\" id=\"memberId_\" value=\"");
      out.print( memberId );
      out.write("\" readonly>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			");
      out.write("\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>이름<sup>*</sup></th>\r\n");
      out.write("				<td>	\r\n");
      out.write("				<input type=\"text\"  name=\"memberName\" id=\"memberName\" value=\"");
      out.print( memberName );
      out.write("\"  required><br>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>생년월일</th>\r\n");
      out.write("				<td>	\r\n");
      out.write("				<input type=\"date\" name=\"birthday\" id=\"birthday\" value=\"");
      out.print( birthday );
      out.write("\"><br>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr> \r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>이메일</th>\r\n");
      out.write("				<td>	\r\n");
      out.write("					<input type=\"email\" placeholder=\"abc@xyz.com\" name=\"email\" id=\"email\" value=\"");
      out.print( email );
      out.write("\"><br>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>휴대폰<sup>*</sup></th>\r\n");
      out.write("				<td>	\r\n");
      out.write("					<input type=\"tel\" placeholder=\"(-없이)01012345678\" name=\"phone\" id=\"phone\" maxlength=\"11\" value=\"");
      out.print( phone );
      out.write("\" required><br>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>주소</th>\r\n");
      out.write("				<td>	\r\n");
      out.write("					<input type=\"text\" placeholder=\"\" name=\"address\" id=\"address\" value=\"");
      out.print( address );
      out.write("\"><br>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>성별 </th>\r\n");
      out.write("				<td>\r\n");
      out.write("			       		 <input type=\"radio\" name=\"gender\" id=\"gender0\" value=\"M\" ");
      out.print( "M".equals(gender) ? "checked" : "" );
      out.write(">\r\n");
      out.write("						 <label for=\"gender0\">남</label>\r\n");
      out.write("						 <input type=\"radio\" name=\"gender\" id=\"gender1\" value=\"F\" ");
      out.print( "F".equals(gender) ? "checked" : "" );
      out.write(">\r\n");
      out.write("						 <label for=\"gender1\">여</label>\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("			<tr>\r\n");
      out.write("				<th>취미 </th>\r\n");
      out.write("				<td>\r\n");
      out.write("					<input type=\"checkbox\" name=\"hobby\" id=\"hobby0\" value=\"운동\" ");
      out.print( hobbyList != null && hobbyList.contains("운동") ? "checked" : "" );
      out.write("><label for=\"hobby0\">운동</label>\r\n");
      out.write("					<input type=\"checkbox\" name=\"hobby\" id=\"hobby1\" value=\"등산\" ");
      out.print( hobbyList != null && hobbyList.contains("등산") ? "checked" : "" );
      out.write("><label for=\"hobby1\">등산</label>\r\n");
      out.write("					<input type=\"checkbox\" name=\"hobby\" id=\"hobby2\" value=\"독서\" ");
      out.print( hobbyList != null && hobbyList.contains("독서") ? "checked" : "" );
      out.write("><label for=\"hobby2\">독서</label><br />\r\n");
      out.write("					<input type=\"checkbox\" name=\"hobby\" id=\"hobby3\" value=\"게임\" ");
      out.print( hobbyList != null && hobbyList.contains("게임") ? "checked" : "" );
      out.write("><label for=\"hobby3\">게임</label>\r\n");
      out.write("					<input type=\"checkbox\" name=\"hobby\" id=\"hobby4\" value=\"여행\" ");
      out.print( hobbyList != null && hobbyList.contains("여행") ? "checked" : "" );
      out.write("><label for=\"hobby4\">여행</label><br />\r\n");
      out.write("				</td>\r\n");
      out.write("			</tr>\r\n");
      out.write("		</table>\r\n");
      out.write("        <input type=\"submit\" value=\"정보수정\"/>\r\n");
      out.write("        <input type=\"button\" value=\"비밀번호수정\" onclick=\"location.href='");
      out.print( request.getContextPath() );
      out.write("/member/passwordUpdate';\"/>\r\n");
      out.write("        <input type=\"button\" onclick=\"deleteMember();\" value=\"탈퇴\"/>\r\n");
      out.write("	</form>\r\n");
      out.write("</section>\r\n");
      out.write("<!-- 회원탈퇴폼 : POST /member/memberDelete 전송을 위해 시각화되지 않는 폼태그 이용 -->\r\n");
      out.write("<form \r\n");
      out.write("	name=\"memberDelFrm\" \r\n");
      out.write("	action=\"");
      out.print( request.getContextPath() );
      out.write("/member/memberDelete\" \r\n");
      out.write("	method=\"POST\">\r\n");
      out.write("	<input type=\"hidden\" name=\"memberId\" value=\"");
      out.print( loginMember.getMemberId() );
      out.write("\" />\r\n");
      out.write("</form>\r\n");
      out.write("<script>\r\n");
      out.write("const deleteMember = () => {\r\n");
      out.write("	if(confirm(\"정말로 탈퇴하시겠습니까?\")){\r\n");
      out.write("		document.memberDelFrm.submit();\r\n");
      out.write("	}\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("/* const updateMember = () => {\r\n");
      out.write("	document.memberUpdateFrm.submit(); // form.submit()하는 경우 handler가 호출되지 않는다.\r\n");
      out.write("};\r\n");
      out.write(" */\r\n");
      out.write("/**\r\n");
      out.write(" * 회원수정폼 유효성 검사\r\n");
      out.write(" */\r\n");
      out.write("document.memberUpdateFrm.onsubmit = () => {\r\n");
      out.write("	// memberName\r\n");
      out.write("	if(!/^[가-힣]{2,}$/.test(memberName.value)){\r\n");
      out.write("		alert(\"이름은 한글 2글자이상 입력해주세요.\");\r\n");
      out.write("		return false;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("	// phone\r\n");
      out.write("	if(!/^010\\d{8}$/.test(phone.value)){\r\n");
      out.write("		alert(\"유효한 전화번호를 입력하세요.\");\r\n");
      out.write("		return false;\r\n");
      out.write("	}\r\n");
      out.write("};\r\n");
      out.write("</script>\r\n");
      out.write("\r\n");
      out.write("		</section>\r\n");
      out.write("\r\n");
      out.write("		<footer>\r\n");
      out.write("			<p>&lt;Copyright 1998-2021 <strong>KH정보교육원</strong>. All rights reserved.&gt;</p>\r\n");
      out.write("		</footer>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
      out.write('\r');
      out.write('\n');
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
