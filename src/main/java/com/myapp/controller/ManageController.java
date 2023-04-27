package com.myapp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ManageController {
	
	private String message = "Hello, world!"; // 컨트롤러에서 읽을 변수
    
    public String sendMessage() {
        return this.message; // 변수의 값을 반환
    }
	
	
	
}
