package com.websocket.wstutorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;


public class MyWebSocketHandlerDecorator extends WebSocketHandlerDecorator {

    private static final Logger LOG = LoggerFactory.getLogger(MyWebSocketHandlerDecorator.class);

    public MyWebSocketHandlerDecorator(WebSocketHandler delegate) {
        super(delegate);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
    	String deviceId = session.getPrincipal().getName();
        UserHandshakeHandler.removeDeviceFromCache(deviceId);
        super.afterConnectionClosed(session, closeStatus);
    }
}