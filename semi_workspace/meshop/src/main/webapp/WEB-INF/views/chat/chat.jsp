<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

  <link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/chatroom.css">
<div class="frame">
    <div class="chatroom-section">

    </div>
    <div class="chat-section">
       <div class="chatroom-info">
       	<input type=hidden id="storeName">
           <h3 class="chatroom-title"></h3>
           <span></span>
       </div>
       <div class="chat-container">
           <ul>
           </ul>
       </div>
       <div class="input-section">
       		<input type=hidden id="chatroom_id">
       		<input type=hidden id="opponent">
           <textarea id="chat_area"></textarea>
           <button id="chat_btn">전송</button>
       </div>
   </div>
</div>
<script>
	const host = location.host;	//localhost (접속하고 있는 서버 도메인)
	const ws = new WebSocket(`ws://\${host}<%= request.getContextPath()%>/chat/ws`);
	
	ws.onopen = (e) => {
		console.log('open', e);
	}
	ws.onmessage = (e) => {
		// onmessage 가 실행되었을 때
		console.log('message', e);
		
		//string -> json 객체
		const payload = JSON.parse(e.data);
		
		//구조분해 할당
		const {type, senderId, receiverId, chatroomId, chat, time} = payload;
		const storeName = document.querySelector('#storeName').value;
		
		// 후에 Session에 storename 넣으면 바꾸기
		alert(`\${storeName} : \${chat}`);
		
		//현재 채팅방에 있으면 메시지 출력
		if(document.querySelector('#chatroom_id').value == chatroomId){
			const ul = document.querySelector('.chat-container ul');
	        let html = `<li class="you"><p class="who">\${storeName} : </p><p class="message">\${chat}</p></li>`;
	        ul.insertAdjacentHTML('beforeend',html);
	        
			// 스크롤해서 하단부 노출!
			showRecentChat();
		}

		//채팅방 리스트 새로고침
		getChatList(memberId);
	};
	
	const memberId = '<%=loginMember.getMemberId()%>';
    window.addEventListener('load',()=>{
    	//멤버 아이디 구하기
    	console.log(memberId);
        //헤더 높이 구하기
        const header = document.querySelector('.header');

        //메인 컨텐츠의 padding top 높이 조절하기
        const frame = document.querySelector('.frame');
        frame.style.paddingTop = `\${header.offsetHeight}px`;
        
        // 회원 아이디에 따른 채팅 리스트 가져오기
        getChatList(memberId);
        
        //let chatroomId = 1;
        //getChatContent(chatroomId,'','');
        
    });
    
    chat_btn.addEventListener('click',()=>{
        //채팅메시지 정보
        let senderId = memberId;
        let receiverId = document.querySelector('#opponent').value;
        let chatroom_id = document.querySelector('#chatroom_id').value;
        console.log('senderId', senderId);
        console.log('receiverId', receiverId);
        console.log('chatroom_id', chatroom_id);
        
        //textarea 값 가져오기
        const value = document.querySelector('#chat_area').value;
        
		//정규식 검사
		if(!/^(.|\n)+$/.test(value)) return;
		
		if(receiverId == "" || chatroom_id == ""){
			alert("채팅 상대를 선택해주세요.");
			return;
		}
        
		//msg 객체 생성
		const msg = {
			type : "dm",
			senderId : senderId,
			receiverId : receiverId,
			chatroomId : chatroom_id,
			chat : value,
			time : Date.now()
		};
        
        //데이터베이스 입력 요청
        $.ajax({
            url : "<%=request.getContextPath() %>/chat/add",
            data : {
            	dm : JSON.stringify(msg)
            },
            method : "POST",
            success(response){
                addChat(value);
            },
            error: console.log
        });
        
    });
    const getChatList = (memberId) =>{
        $.ajax({
            url : "<%=request.getContextPath() %>/chat/chatlist",
            method : "POST",
            data : {
                memberId : memberId
            },
            success(response){
                // 채팅방 리스트 가져오기
				let html = "";
                response.forEach((chatroom)=>{
                	const {no, sellerId, buyerId, productId, title, storeName} = chatroom;
                	
                	let opponent = "";
                	
                	//상대방 뽑기
                	if(sellerId === memberId){
                		opponent = buyerId;
                	}else{
                		opponent = sellerId;
                	}
                	
                	//채팅방 리스트 HTML
                    html += `<div class="chatroom" onclick="getChatContent(\${no},'\${storeName}','\${title}','\${opponent}',this)">
                        <div class="room-content">
                            <span class="chat-store-name">\${storeName}</span>
                            <span class="chat-title">\${title}</span>
                        </div>
                    </div>`;
                });
                const div = document.querySelector('.chatroom-section');
                div.innerHTML = html;
            },
            error : console.log
        });
    };
    const getChatContent = (chatroomId, storeName, title, opponent, e) =>{
    	//현재 채팅방 색 표시하기
        const chatroomList = document.querySelectorAll('.chatroom');
        chatroomList.forEach((chatroom)=>{
            chatroom.style.backgroundColor = 'rgb(255,255,255)';
        });
        e.style.backgroundColor = "rgb(231, 231, 231)";
        
    	//채팅 내용 가져오기
    	document.querySelector('.chatroom-info h3').innerHTML = title;
    	document.querySelector('.chatroom-info span').innerHTML = storeName;
    	
    	//전송버튼 chatroomId, 상대방 아이디 매핑
    	document.querySelector('#chatroom_id').value = chatroomId;
    	document.querySelector('#opponent').value = opponent;
    	document.querySelector('#storeName').value = storeName;
    	
    	//채팅 내용 가져오기
        $.ajax({
            url : "<%=request.getContextPath() %>/chat/content",
            method : "POST",
            data : {
                chatroomId : chatroomId
            },
            success(response){
                console.log(response);
                const ul = document.querySelector('.chat-container ul');
                ul.innerHTML = "";
                response.forEach((chat)=>{
                    // 구조분해 할당
                    const {no,senderId,receiverId, message, sendDate} = chat;
                    let html = "";
                    if(senderId === memberId){
                        html = `<li class="me"><p class="message">\${message}</p></li>`;
                    }else{
                        html = `<li class="you"><p class="who">\${storeName} : </p><p class="message">\${message}</p></li>`;
                    }
                    ul.insertAdjacentHTML('beforeend',html);
                    
            		// 스크롤해서 하단부 노출!
            		showRecentChat();
                });
            },
            error: console.log
        });
    }
    const addChat = (value) =>{
        const ul = document.querySelector('.chat-container ul');
        let html = `<li class="me"><p class="message">\${value}</p></li>`;
        ul.insertAdjacentHTML('beforeend',html);
        
		// 스크롤해서 하단부 노출!
		showRecentChat();
		
        document.querySelector('#chat_area').value = "";
    }
    const showRecentChat = () =>{
		const container = document.querySelector(".chat-container");
		container.scrollTop = container.scrollHeight;
    }
    

</script>
<%@ include file="/WEB-INF/views/common/footer.jsp" %>