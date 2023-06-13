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
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); 
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		//logger.info("/list 진입");
		

		List<BoardEntity> list = em.createQuery("select b from BoardEntity b", BoardEntity.class).setFirstResult(vo.getPageNum() * 12).setMaxResults(12).getResultList();
		int totalCnt = list.size(); // 총게시글수
		int page = vo.getPageNum(); // 현재 요청한 페이지
		int pageCnt = 10;		// 블럭의 페이지개수
		int cntPerPage = 12;	// 페이지당 게시글수
		int totalPage;	// 총 페이지수
		int startPage;	// 해당블럭의 첫페이지
		int endPage;		// 해당블럭의 마지막페이지
		
		totalPage = totalCnt / cntPerPage;
		if(totalCnt % cntPerPage > 0) {
			totalPage++;
		}
		if(page > totalPage) {
			page = totalPage;
		}
		
		startPage = ((page-1) / pageCnt) * 10 + 1;
		endPage = startPage + pageCnt - 1;
		
		if (endPage > totalPage) {
		    endPage = totalPage;
		}
		
		int endBlock = totalPage%pageCnt > 0? totalPage/pageCnt + 1 : totalPage/pageCnt;	// 마지막 페이지블록
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("list", list);
		map.put("totalCnt", totalCnt);
		map.put("totalPage", totalPage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("pageNum", page);
		map.put("endBlock", endBlock);
		/*
		StringWriter writer = new StringWriter();
		Map<String, Object> context = new HashMap<>();
		context.put("list", list);
		mustacheCompiler.compile("classpath:static/boardTemplate.html").execute(context,writer);
		String html = writer.toString();
		Map<String, Object> data = new HashMap<>();
		data.put("html", html);
		*/
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
