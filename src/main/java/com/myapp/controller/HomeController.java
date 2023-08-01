package com.myapp.controller;

import com.myapp.SessionListener;
import com.myapp.service.ManageService;
import com.myapp.vo.LinkVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

	private final ManageService manageService;

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest request) {
		logger.info("{}","enterd home");
		LinkVO link = manageService.selectLink();
		model.addAttribute("activeSessions", SessionListener.getActiveSessions());
		model.addAttribute("activeNames", SessionListener.getActiveNames());
		model.addAttribute("community", link.getCommunity());
		model.addAttribute("download", link.getDownload());
		return "home";
	}
}
