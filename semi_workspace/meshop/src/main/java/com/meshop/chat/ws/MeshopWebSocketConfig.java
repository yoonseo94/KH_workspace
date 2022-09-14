package com.meshop.chat.ws;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import com.meshop.member.entity.Member;

public class MeshopWebSocketConfig extends Configurator{

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		HttpSession httpSession = (HttpSession) request.getHttpSession();
        Member loginMember = (Member) httpSession.getAttribute("loginMember");
        
        if(loginMember == null) {
        	
        }else {
            // @ServerEndPoint에서 접근할 수 있는 map 객체 : UserProperties
            Map<String, Object> userProps = sec.getUserProperties();
            userProps.put("memberId", loginMember.getMemberId());
        }
	}
}
