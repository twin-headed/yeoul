package com.myapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.service.BoardService;
import com.myapp.service.SimpleChatClient;
import com.myapp.vo.BoardVO;
import com.samskivert.mustache.Mustache;
import javafx.scene.Parent;
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
