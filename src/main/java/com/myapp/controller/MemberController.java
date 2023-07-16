package com.myapp.controller;

import com.myapp.service.AccountService;
import com.myapp.service.BoardService;
import com.myapp.service.MemberService;
import com.myapp.vo.BoardVO;
import com.myapp.vo.CommentVO;
import com.myapp.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final AccountService accountService;
	private final AuthenticationManager authenticationManager;

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	//회원가입 페이지 진입시
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "signup";
	}

	//회원가입시
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> insertMember(@RequestBody MemberVO vo) {
		// 결과값
		Map<String, Object> map = new HashMap<>();
		// 아이디 중복확인
		MemberVO vo1 = memberService.checkId(vo.getId());
		// 인게임 아이디 중복확인
		MemberVO vo2 = memberService.checkGameId(vo.getGameId());

		if(vo1 != null) {
			map.put("result", "duplicateId");
			return map;
		}else if(vo2 != null) {
			map.put("result", "duplicateGameId");
			return map;
		}
		memberService.insertMember(vo);
		map.put("result", "success");
		return map;
	}

	// 로그인시
	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> login(@RequestParam("username") String username,@RequestParam("password") String password, Model model) {
		// 결과값
		Map<String, Object> map = new HashMap<>();
		System.out.println(username);
		System.out.println(password);
        try {
			// 사용자 인증 처리
			Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
			Authentication result = authenticationManager.authenticate(authentication);
			SecurityContextHolder.getContext().setAuthentication(result);
			// 인증 성공 후 처리
			map.put("result", "success");
		} catch(Exception e) {
			map.put("result", "fail");
		}
		return map;
	}
}
