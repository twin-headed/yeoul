package com.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * Handles requests for the application home page.
 */
@Controller
public class MessageController {
	@RequestMapping(value = "/message", method = RequestMethod.POST)
	public void message(@RequestBody String message) {
		System.out.println("message : " + message);
	}
	
}
