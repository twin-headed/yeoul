package com.myapp.controller;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

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
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	//최초 진입시
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list() {
		return "board";
	}

	//페이지 요청시
	@ResponseBody
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Map<String, Object> list(@RequestBody BoardVO vo) {
		// PageRequest 구현체의 of 메서드를 사용해서 pageable 인터페이스 구현, 실제페이지에서 1을 뺀값과 페이지당 게시글수 12개를 넣는다.
		Pageable pageable = PageRequest.of(vo.getPageNum() - 1, 10);
		vo.setOffset((int)pageable.getOffset());
		vo.setPagePerNum(pageable.getPageSize());
		// 게시글 조회
		List<BoardVO> list = service.selectBoardlist(vo);
		// 총 게시글 수 조회
		int listCnt = service.selectBoardListCnt(vo);
		// 현재페이지 - 1 / 블록당 페이지수 -> 이걸로 0부터 시작하는 블록의 몇번째 블록인지 파악, 그 후 블록당 페이지수를 곱해주고 1을 더해주는것은 시작페이지는 1, 11, 21 등의 시작 숫자를 맞춰주기 위함임
		int startPage = (pageable.getPageNumber() / 10) * 10 + 1;
		// 마찬가지로 몇번째 블록인지 구한후 + 블록당 페이지수를 해주면 각 블록당 끝 페이지가 나온다. 다만 전체 페이지수가 계산된 끝 페이지수보다 작은 경우가 있을수있으니 둘중 작은값을 선택한다.
		int endPage = Math.min((pageable.getPageNumber()/10) * 10 + 10, listCnt/12 == 0? 1 : listCnt/10 + 1);
		// 결과값 담아주기
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageable.getPageNumber() + 1); // pageable.getPageNumber는 사용자가 인식하는 페이지 + 1값임 따라서 -1을 처리해서 실제 인식값으로 바꿔줌
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", listCnt/10 == 0? 1 : listCnt/10 + 1);	// page.getTotalPages는 사용자가 인식하는 실제 맨끝페이지 번호임
		map.put("list", list);
		return map;
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
