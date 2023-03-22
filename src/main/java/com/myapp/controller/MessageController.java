package com.myapp.controller;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
/**
 * Handles requests for the application home page.
 */
@Slf4j
@ServerEndpoint("/websocket")
@Controller
public class MessageController {
	
	@Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private String message;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void setMessage(@RequestBody String message) {
    	log.info("message : " + message);
        this.message = message; // 전송할 메시지 저장
        sendMessage(); // 메시지 전송
    }

    private void sendMessage() {
        simpMessagingTemplate.convertAndSend("/topic/message", message); // 메시지 전송
    }
	
}
