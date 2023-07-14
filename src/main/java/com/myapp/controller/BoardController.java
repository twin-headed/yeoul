package com.myapp.controller;

import java.io.StringWriter;
import java.lang.reflect.Array;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.myapp.vo.CommentVO;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.myapp.service.BoardService;
import com.myapp.vo.BoardEntity;
import com.myapp.vo.BoardVO;
import com.samskivert.mustache.Mustache;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	private final BoardService service;

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
		System.out.println(list);
		// 총 게시글 수 조회
		int listCnt = service.selectBoardListCnt(vo);
		// 현재페이지 - 1 / 블록당 페이지수 -> 이걸로 0부터 시작하는 블록의 몇번째 블록인지 파악, 그 후 블록당 페이지수를 곱해주고 1을 더해주는것은 시작페이지는 1, 11, 21 등의 시작 숫자를 맞춰주기 위함임
		int startPage = (pageable.getPageNumber() / 10) * 10 + 1;
		// 마찬가지로 몇번째 블록인지 구한후 + 블록당 페이지수를 해주면 각 블록당 끝 페이지가 나온다. 다만 전체 페이지수가 계산된 끝 페이지수보다 작은 경우가 있을수있으니 둘중 작은값을 선택한다.
		int endPage = Math.min((pageable.getPageNumber()/10) * 10 + 10, listCnt/10 == 0? 1 : listCnt%10 == 0? listCnt/10 : listCnt/10 + 1);
		// 결과값 담아주기
		Map<String, Object> map = new HashMap<>();
		map.put("pageNum", pageable.getPageNumber() + 1); // pageable.getPageNumber는 사용자가 인식하는 페이지 + 1값임 따라서 -1을 처리해서 실제 인식값으로 바꿔줌
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("totalPage", (int) Math.ceil((double) listCnt / 10));    // page.getTotalPages는 사용자가 인식하는 실제 맨끝페이지 번호임
		map.put("list", list);
		return map;
	}

	// 등록 페이지 진입
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String write(Model model) {
		model.addAttribute("flag", "save");
		model.addAttribute("value", "저장");
		return "write";
	}

	// 등록 요청
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	@ResponseBody
	public void insert(@RequestBody BoardVO vo) {
		service.insertBoard(vo);
	}

	// 수정 페이지 진입
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(@RequestParam("data") String data, Model model) {
		JSONObject object = new JSONObject(data);
		model.addAttribute("seq", object.getInt("seq"));
		model.addAttribute("title", object.getString("title"));
		model.addAttribute("content", object.getString("content"));
		model.addAttribute("flag", "modify");
		model.addAttribute("value", "수정");
		return "write";
	}

	// 수정 요청
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public void update(@RequestBody BoardVO vo) {
		service.updateBoard(vo);
	}

	@RequestMapping(value = "/delete/{seq}", method = RequestMethod.GET)
	@ResponseBody
	public void delete(@PathVariable int seq) {
		service.deleteBoard(seq);
	}

	// 글보기 진입
	@RequestMapping(value = "/view/{seq}", method = RequestMethod.GET)
	public String view(@PathVariable int seq, Model model) {
		service.updateViewCount(seq);
		BoardVO bo = service.selectBoardOne(seq);
		List<CommentVO> co = service.selectComments(seq);
		List<CommentVO> so = service.selectSubComments(seq);

		for(CommentVO vo : co) {
			for(CommentVO vo2 : so) {
				if(vo.getSeq() == vo2.getFollowSeq()) {
					vo.getSubComment().add(vo2);
				}
			}
		}
		model.addAttribute("vo", bo);
		model.addAttribute("co", co);
		model.addAttribute("coCount", co.size()+so.size());
		return "view";
	}

	// 댓글 작성
	@RequestMapping(value = "/comment/write", method = RequestMethod.POST)
	@ResponseBody
	public void insertComment(@RequestBody CommentVO vo) {
		service.insertComment(vo);
	}

	// 대댓글 작성

	@RequestMapping(value = "/subComment/write", method = RequestMethod.POST)
	@ResponseBody
	public void insertSubComment(@RequestBody CommentVO vo) {
		System.out.println(vo);
		service.insertSubComment(vo);
	}

}
