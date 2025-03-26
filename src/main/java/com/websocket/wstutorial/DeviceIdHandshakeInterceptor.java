package com.websocket.wstutorial;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class DeviceIdHandshakeInterceptor extends HttpSessionHandshakeInterceptor {

	@Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        String deviceId = request.getURI().getQuery().split("=")[1];
        attributes.put("deviceId", deviceId);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }
	
	
}
