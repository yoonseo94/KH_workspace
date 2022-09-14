<%@page import="member.model.dto.MemberExt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sweet Talk</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/customerservice/sweettalk.css" />
<% 
	MemberExt loginMember = (MemberExt) request.getAttribute("loginMember");
	System.out.println("loginMember@jsp = " + loginMember);
%>
</head>
<body >
	<aside id="sweet-talk-top"><div id="title-wrapper"><span id="top-title">1:1 SWEET TALK</span></div></aside>
	<aside id="sweet-talk-aside">
		<div id="wrap" class="wrap">
			<div id="content" class="content" style="top: 60px; bottom: 0px;">
				<div class="msg_aside_box">
					<div class="aside-img-wrapper"><img id="chat-img-icon" src= "<%= request.getContextPath() %>/images/customerservice/comments.png" alt="채팅 이미지"/> </div>
					<div class="msg_aside_imgbx">
						기다리지 말고 <img src="" alt="" /><br>
						<strong>Sweet Talk</strong><br>해보세요
					</div>
					<div class="msg_aside_type">
				<%-- 		<div class="msg_aside_chat">
							<span class="ico_bot"><img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/bot.png" alt="프로필 이미지"></span><strong>고객센터봇</strong><span class="inner-txt">배송, 교환
								문의를 24시간 언제든 간편하게</span>
						</div> --%>
						<div class="msg_aside_chat">
							<span class="ico_people"><img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/counseller.png" alt=""></span><strong>고객상담사</strong><span class="inner-txt">꼼꼼한 답변이
								필요하다면 상담사 연결</span>
						</div>
					</div>
				</div>
			</div>
		</div>
	</aside>
			<section id="sweet-talk-section">
				<div class="cs-talk-profile">
					<div class="thmb">
						<span class="thmb_img "><span><img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/comments.png" alt="프로필 이미지"></span></span>
					</div>
					<div class="detail">
						<div>
							<p>고객센터톡</p>
						</div>
						<div class="desc">
							고객상담사 연결가능시간<span class="bar"> | </span>평일
								09:00~18:00
						</div>
					</div>
				</div>
 
			<div id="sweet-talk-content" class="sweet-talk-content">
				<div id="msg-view" class="msg-view" style= "bottom: 64px;">
					<div class="msg-date">
						<em> 2022.06.19 (일) </em>
					</div>
					<div class="msg-unit-cs">
						<div class="msg-thmb">
							<span class="thmb-img">
								<img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/counseller.png" alt="프로필 이미지">
							</span>
						</div>
						<div class="msg-text-inner">
							<div class="msg-name">상담사</div>
							<div class="msg-bx">
							 	<div id="1924699364_text" class="msg-bubble ty-txt">
									<p class="msg-txt">
										<span><span><b></b>안녕하세요?</span></span><span><br>
										<span><strong>Home Sweet Home</strong> 고객센터 <br /><strong>Sweet Talk</strong> 상담사 %%%입니다. <br />무엇을 도와드릴까요? </span></span>
									</p>
								</div>
								<div class="msg-info">
									<span class="tx-time msg-time-1924699364"> 오후 05:31 </span>
								</div> 
							</div>
						</div>
					</div>
					<div class="msg-unit-me">
						<div class="msg-text-inner">
							<div class="msg-name"><%-- <%= loginMember.getMemberName() %> --%>
							</div>
							<div class="msg-bx">
								<div id="1924699448_text" class="msg-bubble ty-txt">
									<p class="msg-txt">
										<span><span>기타 문의</span></span>
									</p>
								</div>
								<div class="msg-info">
									<span class="tx-time msg-time-1924699448"> 오후 05:31 </span>
								</div> 
							</div>
						</div>
						<div class="msg-thmb">
							<span class="thmb-img">
								<img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/bot.png" alt="프로필 이미지">
							</span>
						</div>
					</div>
					<div class="top-bottom-btn-wrapper">
						<button type="button" id="moveTop" class="top-btn" onclick="topMove();" style="display: block; background-image: url(<%= request.getContextPath() %>/images/customerservice/arrow3.png)"></button>
						<button type="button" id="moveBottom" class="bottom-btn" onclick="bottomMove();" style="display: block; background-image: url(<%= request.getContextPath() %>/images/customerservice/arrow3.png)"></button>
					</div>
					<div class="msg-input-bx">
						<div class="col-group">
							<div class="col-input">
								<div class="inp-tx">
									<label for="chatTextarea"></label>
									<textarea name="chatTextarea" autocomplete="off"
										id="chatTextarea" rows="1" data-min-rows="1"
										placeholder="메시지를 입력하세요."
										style="overflow: hidden; height: 20px;"></textarea>
								</div>
							</div>
							<div class="txt-send-btn">
								<div class="bta">
									<button id="sendMessageBtn" type="button" class="btn-submit-send" style="background-image: url(<%= request.getContextPath() %>/images/customerservice/send.png)"></button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			</section>
			<footer id="sweet-talk-footer" class="footer">
				<div id="setting_wrap" class="pop_wrap bg ty_drawer v2">
				
					<div id="setting_content" class="content">
						<div class="chatroom_info">
							<div class="chatroom_profile_img">
							<img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/counseller.png" alt="프로필 이미지">
							</div>
							<p class="chatroom_txt">
								상담사 연결 가능 시간<br>평일 09:00~18:00
							</p>
							<div class="chatroom_controls">
								<a href="tel:1111 1111" class="chatroom_tel">
									<span><img class="sweet-talk-bot-img" src="<%= request.getContextPath() %>/images/customerservice/call.png" alt="프로필 이미지"></span>
								</a>
								<a	class="chatroom_tel_num">
									<span>1111 - 1111</span>
								</a>
							</div>
						</div>
						<div class="btn-leave-area">
							<button type="button" class="btn-leave-chatroom" onclick="closeChat()">채팅방 나가기</button>
						</div>
					</div>
				</div>
			</footer>
</body>
<script>
const closeChat = () => {
	self.close();
}
const topMove = () => {
	  document.body.scrollTop = 0;
	  document.documentElement.scrollTop = 0; 
}
const bottomMove = () => {
	// self.close();
}
  
  const messageHandler = (payload) => {
const {type, sender, msg, time, clientCnt} = payload;
	const html = `
	<li class="\${type !== 'chat' ? 'center' : ''}">
		<span class="badge">\${sender}</span>
		\${msg}
	</li>`;
	document.querySelector("#msg-unit-me").insertAdjacentHTML('beforeend', html);
	
	// 스크롤해서 하단부 노출!
	const container = document.querySelector("#msg-container");
	container.scrollTop = container.scrollHeight;
	

};

ws.onerror = (e) => {
	console.log('error', e);
};
ws.onclose = (e) => {
	console.log('close', e);
};
const host = location.host; // 접속하는 있는 서버 도메인
const ws = new WebSocket(`ws://\${host}<%= request.getContextPath() %>/chat/ws`);

ws.onopen = (e) => {
	console.log('open', e);
};
ws.onmessage = (e) => {
/* 	console.log('message', e);
	const payload = JSON.parse(e.data);
	const {type, sender, receiver, msg, time} = payload;
	console.log(type, sender, receiver, msg, time);
	
	let html;
	switch(type){
	case "welcome":
	case "goodbye": 
	case "chat": messageHandler(payload); break;

	} */
	
};
<%-- document.querySelector("#send").addEventListener('click', (e) => {
	const textarea = document.querySelector("#msg");
	if(!/^(.|\n)+$/.test(textarea.value)) return; 
	const msg = {
		type : "chat",
		sender : "<%= loginMember.getMemberId() %>",
		msg : textarea.value,
		time : Date.now()
	};
	const payload = JSON.stringify(msg);
	ws.send(payload);
	textarea.value = "";
	textarea.focus();
});  --%>

</script>
</html>