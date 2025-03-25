package com.websocket.wstutorial;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.websocket.wstutorial.dto.Message;

@Controller
public class WebSocketController {

//    @Autowired
//    private WSService service;


    @MessageMapping("/hello")
    public void sendPrivateMessage(Message message) {
//        service.notifyUser(id, message.getMessageContent());
    	System.out.println("Hello: " + message);
    }
}
