/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.62
 * Generated at: 2022-06-08 05:19:12 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views.chat;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import member.model.dto.MemberRole;
import member.model.dto.Member;

public final class chatroom_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\r');
      out.write('\n');
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
      out.write(" \r\n");
      out.write("<link rel=\"stylesheet\" href=\"");
      out.print( request.getContextPath() );
      out.write("/css/chat.css\" />\r\n");
      out.write("<section id=\"chat-container\">	\r\n");
      out.write("	<h2>채팅</h2>\r\n");
      out.write("	<button type=\"button\" id=\"btn-userList\">현재사용자확인</button>\r\n");
      out.write("	<span id=\"clientCnt\"></span>\r\n");
      out.write("	\r\n");
      out.write("	<div id=\"msg-container\">\r\n");
      out.write("		<ul></ul>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<div id=\"msg-editor\" class=\"editor\">\r\n");
      out.write("		<textarea name=\"\" id=\"msg\" cols=\"30\" rows=\"2\"></textarea>\r\n");
      out.write("		<button type=\"button\" id=\"send\">Send</button>\r\n");
      out.write("	</div>\r\n");
      out.write("	\r\n");
      out.write("	<hr style=\"margin:20px 0\" />\r\n");
      out.write("\r\n");
      out.write("	<!-- dm관련 섹션 -->		\r\n");
      out.write("	<div id=\"dm-container\" >\r\n");
      out.write("		<label for=\"dm-client\">DM</label>\r\n");
      out.write("		<select class=\"custom-select\" id=\"dm-client\">\r\n");
      out.write("			<option value=\"\" disabled selected>접속자 목록</option>\r\n");
      out.write("		</select>\r\n");
      out.write("	</div>\r\n");
      out.write("	<div id=\"dm-editor\" class=\"editor\">\r\n");
      out.write("		<textarea id=\"dm-msg\" cols=\"30\" rows=\"2\"></textarea>\r\n");
      out.write("		<button type=\"button\" id=\"dm-send\">Send</button>\r\n");
      out.write("	</div>\r\n");
      out.write("</section>\r\n");
      out.write("<script>\r\n");
      out.write("const host = location.host; // 접속하는 있는 서버 도메인\r\n");
      out.write("const ws = new WebSocket(`ws://${host}");
      out.print( request.getContextPath() );
      out.write("/chat/ws`);\r\n");
      out.write("\r\n");
      out.write("ws.onopen = (e) => {\r\n");
      out.write("	console.log('open', e);\r\n");
      out.write("};\r\n");
      out.write("ws.onmessage = (e) => {\r\n");
      out.write("	console.log('message', e);\r\n");
      out.write("	const payload = JSON.parse(e.data);\r\n");
      out.write("	const {type, sender, receiver, msg, time} = payload;\r\n");
      out.write("	console.log(type, sender, receiver, msg, time);\r\n");
      out.write("	\r\n");
      out.write("	let html;\r\n");
      out.write("	switch(type){\r\n");
      out.write("	case \"welcome\":\r\n");
      out.write("	case \"goodbye\": \r\n");
      out.write("	case \"chat\": messageHandler(payload); break;\r\n");
      out.write("	case \"dm\":\r\n");
      out.write("		alert(`${sender}로부터 DM이 도착했습니다.\r\n");
      out.write("----------------------------------------\r\n");
      out.write("발신자 : ${sender}\r\n");
      out.write("수신자 : ${receiver}\r\n");
      out.write("내용 : ${msg}`);\r\n");
      out.write("		break;\r\n");
      out.write("	}\r\n");
      out.write("	\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("const messageHandler = (payload) => {\r\n");
      out.write("	const {type, sender, msg, time, clientCnt} = payload;\r\n");
      out.write("	const html = `\r\n");
      out.write("	<li class=\"${type !== 'chat' ? 'center' : ''}\">\r\n");
      out.write("		<span class=\"badge\">${sender}</span>\r\n");
      out.write("		${msg}\r\n");
      out.write("	</li>`;\r\n");
      out.write("	document.querySelector(\"#msg-container ul\").insertAdjacentHTML('beforeend', html);\r\n");
      out.write("	\r\n");
      out.write("	// 스크롤해서 하단부 노출!\r\n");
      out.write("	const container = document.querySelector(\"#msg-container\");\r\n");
      out.write("	container.scrollTop = container.scrollHeight;\r\n");
      out.write("	\r\n");
      out.write("	// 채팅인원수 관리\r\n");
      out.write("	clientCnt && (document.querySelector(\"#clientCnt\").innerHTML = clientCnt);\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("ws.onerror = (e) => {\r\n");
      out.write("	console.log('error', e);\r\n");
      out.write("};\r\n");
      out.write("ws.onclose = (e) => {\r\n");
      out.write("	console.log('close', e);\r\n");
      out.write("};\r\n");
      out.write("\r\n");
      out.write("document.querySelector(\"#send\").addEventListener('click', (e) => {\r\n");
      out.write("	const textarea = document.querySelector(\"#msg\");\r\n");
      out.write("	if(!/^(.|\\n)+$/.test(textarea.value)) return; \r\n");
      out.write("	const msg = {\r\n");
      out.write("		type : \"chat\",\r\n");
      out.write("		sender : \"");
      out.print( loginMember.getMemberId() );
      out.write("\",\r\n");
      out.write("		msg : textarea.value,\r\n");
      out.write("		time : Date.now()\r\n");
      out.write("	};\r\n");
      out.write("	const payload = JSON.stringify(msg);\r\n");
      out.write("	ws.send(payload);\r\n");
      out.write("	textarea.value = \"\";\r\n");
      out.write("	textarea.focus();\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("// DM\r\n");
      out.write("document.querySelector(\"#dm-client\").addEventListener('focus', (e) => {\r\n");
      out.write("	$.ajax({\r\n");
      out.write("		url : \"");
      out.print( request.getContextPath() );
      out.write("/chat/clients\",\r\n");
      out.write("		success(clients){\r\n");
      out.write("			console.log(clients);\r\n");
      out.write("			e.target.innerHTML = \"\";\r\n");
      out.write("			clients.forEach((client) => {\r\n");
      out.write("				const option = `<option value=\"${client}\">${client}</option>`;\r\n");
      out.write("				e.target.insertAdjacentHTML('beforeend', option);\r\n");
      out.write("			});\r\n");
      out.write("		},\r\n");
      out.write("		error : console.log\r\n");
      out.write("	});\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("document.querySelector(\"#dm-send\").addEventListener('click', (e) => {\r\n");
      out.write("	const receiver = document.querySelector(\"#dm-client\");\r\n");
      out.write("	const textarea = document.querySelector(\"#dm-msg\");\r\n");
      out.write("	\r\n");
      out.write("	console.log(receiver.value, textarea.value);\r\n");
      out.write("	\r\n");
      out.write("	if(!receiver.value || !textarea.value) return;\r\n");
      out.write("	\r\n");
      out.write("	const dm = {\r\n");
      out.write("		type : \"dm\",\r\n");
      out.write("		msg : textarea.value,\r\n");
      out.write("		sender : \"");
      out.print( loginMember.getMemberId() );
      out.write("\",\r\n");
      out.write("		receiver : receiver.value,\r\n");
      out.write("		time : Date.now()\r\n");
      out.write("	};\r\n");
      out.write("	\r\n");
      out.write("	$.ajax({\r\n");
      out.write("		url : \"");
      out.print( request.getContextPath() );
      out.write("/chat/sendDM\",\r\n");
      out.write("		method : \"POST\",\r\n");
      out.write("		data : {\r\n");
      out.write("			dm : JSON.stringify(dm)\r\n");
      out.write("		},\r\n");
      out.write("		success(resp){\r\n");
      out.write("			console.log(resp)\r\n");
      out.write("		},\r\n");
      out.write("		error : console.log,\r\n");
      out.write("		complete(){\r\n");
      out.write("			receiver.innerHTML = \"<option value='' disabled selected>접속자목록</option>\";\r\n");
      out.write("			textarea.value = \"\";\r\n");
      out.write("		}\r\n");
      out.write("	});\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("});\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
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
      out.write(' ');
      out.write(' ');
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