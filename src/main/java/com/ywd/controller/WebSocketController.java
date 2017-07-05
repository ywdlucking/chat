package com.ywd.controller;

import com.ywd.model.Message;
import com.ywd.model.WiselyMessage;
import com.ywd.model.WiselyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by admin on 2017/6/30.
 */
@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/chatRoom")
    @SendTo("/topic/getResponse")
    public Message sayAll(Principal principal, Message message) throws Exception {
        message.setName(principal.getName());
        return message;
    }

    @MessageMapping("/chat")
    public void handleChat(Principal principal, Message message) {
        String toUser = message.getName();
        message.setName(principal.getName());
        messagingTemplate.convertAndSendToUser(toUser, "/queue/notifications", message);
        System.out.println("to: " + toUser);
    }

}
