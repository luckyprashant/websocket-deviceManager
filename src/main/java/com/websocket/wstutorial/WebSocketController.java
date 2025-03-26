package com.websocket.wstutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.websocket.wstutorial.dto.Message;
import com.websocket.wstutorial.dto.ResponseMessage;

@Controller
public class WebSocketController {

    @Autowired
    private WSService service;


    @MessageMapping("/private-message")
    public ResponseMessage sendPrivateMessage(Message message) {
    	service.handleResponse(message);
    	System.out.println("Hello: " + message);
        return new ResponseMessage("Server received your private message: " + HtmlUtils.htmlEscape(message.getMessageContent()));
        
    }
    
}
