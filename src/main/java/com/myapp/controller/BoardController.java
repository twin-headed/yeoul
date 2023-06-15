package com.myapp.controller;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.service.BoardService;
import com.myapp.vo.BoardEntity;
import com.myapp.vo.BoardVO;
import com.samskivert.mustache.Mustache;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private Mustache.Compiler mustacheCompiler;
	
	@Autowired
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(@RequestBody BoardVO vo) {
		// JPA트랜잭션처리
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); 
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		// PageRequest 구현체의 of 메서드를 사용해서 pageable 인터페이스 구현, 실제페이지에서 1을 뺀값과 페이지당 게시글수 12개를 넣는다.
		Pageable pageable = PageRequest.of(vo.getPageNum() - 1, 12);
		// Page 자료형으로 게시글 조회
		Page<BoardEntity> page = service.selectBoardlist(pageable);
		// List 자료형으로 변환
		List<BoardEntity> list = page.getContent();
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageable.getPageNumber());
		map.put("startPage", ((pageable.getPageNumber()-1) / 10) * 10 + 1); // 요청한페이지 -1 / 블럭당 페이지 * 10 + 1
		map.put("endPage", (((pageable.getPageNumber()-1) / 10) * 10 + 1) + 9); // 시작페이지 + 블럭당 페이지 - 1
		map.put("totalPage", page.getTotalPages());
		map.put("list", list);
		return map;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		
		return "board";
	}
	
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write() {
		
		return "write";
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public void insert(@RequestBody BoardVO vo) {
		
		service.insertBoard(vo);
	}
	/*
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(@RequestBody BoardVO vo) {
		
		service.updateBoard(vo);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestParam("seq") int seq) {
		
		service.deleteBoard(seq);
	}
	*/
	
	
	
	@RequestMapping(value = "/view/{seq}", method = RequestMethod.GET) 
	public String view(@PathVariable int seq, Model model) {
		
		BoardVO vo = service.selectBoardOne(seq);
		
		model.addAttribute("vo", vo);
		
		return "view";
	}
	
	
	
}
