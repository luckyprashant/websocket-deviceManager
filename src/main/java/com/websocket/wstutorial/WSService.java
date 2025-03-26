package com.websocket.wstutorial;

import com.websocket.wstutorial.dto.Message;
import com.websocket.wstutorial.dto.ResponseMessage;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WSService {

    private final SimpMessagingTemplate messagingTemplate;
    
    private final ConcurrentHashMap<String, CompletableFuture<Message>> responseFutures = new ConcurrentHashMap<>();


    @Autowired
    public WSService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyUser(final String id, final String message) {
        ResponseMessage response = new ResponseMessage(message);

        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }

	public CompletableFuture<Message> sendMessage(Message message) {
		CompletableFuture<Message> future = new CompletableFuture<>();
        responseFutures.put(message.getDeviceId(), future);

        messagingTemplate.convertAndSendToUser(message.getDeviceId(), "/topic/private-messages", message);

        return future;
	}

	public void handleResponse(Message message) {
        CompletableFuture<Message> future = responseFutures.get(message.getDeviceId());
        if (future != null) {
            future.complete(message);
        }
    }
}
