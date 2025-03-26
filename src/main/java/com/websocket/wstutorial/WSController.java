package com.websocket.wstutorial;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.websocket.wstutorial.dto.Message;
import com.websocket.wstutorial.dto.ResponseMessage;

@RestController
public class WSController {	

    @Autowired
    private WSService service;


    @PostMapping("/send-private-message")
    public ResponseMessage sendPrivateMessage(@RequestBody final Message message) throws InterruptedException, ExecutionException, TimeoutException {
//        service.notifyUser(id, message.getMessageContent());
        
        CompletableFuture<Message> future = service.sendMessage(message);

        // Wait for the response from the client
        Message response = future.get(30, TimeUnit.SECONDS); // Wait for response with a timeout

        return new ResponseMessage("Response received: " + response.getMessageContent());
 
    }
}
