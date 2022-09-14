package com.meshop.chat.ws;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/chat/ws",
	configurator = MeshopWebSocketConfig.class
)
public class MeshopWebSocket {
	// client map 객체를 동기화 처리(멀티 스레딩 환경에서 안전하게 사용할 수 있도록 함.)
	public static Map<String, Session> clientsMap = Collections.synchronizedMap(new HashMap<>());
	
	@OnOpen
    public void onOpen(EndpointConfig config, Session session){
        System.out.println("[Open]");
        
        // 1. 사용자 정보 복사 EndpointConfig -> Session
        //WebSocketConfig 에서 만든 userProps 객체
        Map<String, Object> configUserProps = config.getUserProperties();
        String memberId = (String) configUserProps.get("memberId");
        if(memberId != null) {
            Map<String, Object> sessionUserProps = session.getUserProperties();
            sessionUserProps.put("memberId", memberId);
            
            // 2. 클라이언트 맵에 추가
            clientsMap.put(memberId, session);
        }
	}
	
	@OnMessage
	public void onMessage(String payload, Session sess) {
		
	}
    @OnError
    public void onError(Throwable e){
        System.out.println("[Error]");
    }
    @OnClose
    public void onClose(Session session){
        System.out.println("[Close]");
        
        // 1. 사용자 정보 가져오기
        Map<String, Object> sessionUserProps = session.getUserProperties();
        String memberId = (String) sessionUserProps.get("memberId");

        // 2. clients에서 제거
        clientsMap.remove(memberId);
    }
}
