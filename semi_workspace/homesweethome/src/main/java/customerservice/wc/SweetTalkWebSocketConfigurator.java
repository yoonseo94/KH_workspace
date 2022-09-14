package customerservice.wc;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import member.model.dto.Member;

public class SweetTalkWebSocketConfigurator extends Configurator {
	@Override
	public void modifyHandshake(
			ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		
		HttpSession httpSession = (HttpSession) request.getHttpSession();
		Member loginMember = (Member) httpSession.getAttribute("loginMember");
		
		// @ServerEndPoint에서 접근할수 있는 map객체 : UserProperties
		Map<String, Object> userProps = sec.getUserProperties();
		userProps.put("memberId", loginMember.getMemberId());

	}
}
