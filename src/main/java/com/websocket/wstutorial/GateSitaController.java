package com.websocket.wstutorial;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import com.websocket.wstutorial.dto.Message;
import com.websocket.wstutorial.dto.ResponseMessage;

@RestController
public class GateSitaController {	

    @Autowired
    private GateControlService service;


    @PostMapping("/action")
    public ResponseMessage sendPrivateMessage(@RequestBody final Message message) throws InterruptedException, ExecutionException, TimeoutException {
        CompletableFuture<Message> future = service.sendMessage(message);
        Message response = future.get(30, TimeUnit.SECONDS); // Wait for response with a timeout
        return new ResponseMessage("Response received: " + response.getMessageContent());
    }
    
    @MessageMapping("/device-message")
    public ResponseMessage handleDeviceMessage(Message message) {
    	service.handleResponse(message);
        return new ResponseMessage("Server received your private message: " + HtmlUtils.htmlEscape(message.getMessageContent()));
    }
}
