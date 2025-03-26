package com.websocket.wstutorial;

import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class CustomWebSocketHandler extends TextWebSocketHandler  {

	
	@Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Map<String, Object> attributes = session.getAttributes();
        String deviceId = (String) attributes.get("deviceId");

        if (deviceId == null || !isValidDeviceId(deviceId)) {
            session.close(CloseStatus.NOT_ACCEPTABLE);
        } else {
            // Proceed with the connection
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // Handle messages
    }

    private boolean isValidDeviceId(String deviceId) {
        // Implement your deviceId validation logic here
        return true;
    }
    
}
