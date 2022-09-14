package customerservice.wc;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

@ServerEndpoint(
		value = "/chat/ws",
		configurator = SweetTalkWebSocketConfigurator.class
	)
public class SweetTalkWebSocket {
	
	public static Map<String, Session> clients = Collections.synchronizedMap(new HashMap<>());

	
	@OnOpen
	public void onOpen(EndpointConfig config, Session session) {
		System.out.println("[Open]");
		
		// 1. 사용자정보 복사 EndpointConfig -> Session
		Map<String, Object> configUserProps = config.getUserProperties();
		String memberId = (String) configUserProps.get("memberId");
		Map<String, Object> sessionUserProps = session.getUserProperties();
		sessionUserProps.put("memberId", memberId);
		
		// 2. clients에 추가
		clients.put(memberId, session);
		
		// 3. 사용자 입장 메세지 출력
		
		String payload = convertJsonPayload("welcome", memberId, "님이 입장했습니다.");
		onMessage(payload, session);
	}
	
	/**
	 * Map -> json 변환
	 * @param type
	 * @param memberId
	 * @param string2
	 * @return
	 */
	private String convertJsonPayload(String type, String sender, String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("type", type);
		map.put("sender", sender);
		map.put("msg", msg);
		map.put("time", System.currentTimeMillis());
		return new Gson().toJson(map);
	}

	@OnMessage
	public void onMessage(String payload, Session sess) {
		System.out.println("[Message] " + payload);
		
		Collection<Session> sessions = clients.values();
		for(Session session : sessions) {
			Basic basic = session.getBasicRemote();
			try {
				basic.sendText(payload);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
	@OnError
	public void onError(Throwable e) {
		e.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("[Close]");
		
		// 1. 사용자정보 가져오기
		Map<String, Object> sessionUserProps = session.getUserProperties();
		String memberId = (String) sessionUserProps.get("memberId");
		
		// 2. clients에서 제거
		clients.remove(memberId);
		
		// 3. 사용자 퇴장 메세지 출력
		String payload = convertJsonPayload("goodbye", memberId, "님이 퇴장했습니다.");
		onMessage(payload, session);
		
	}
}
