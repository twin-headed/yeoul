package com.myapp.controller;

import com.myapp.service.BoardService;
import com.myapp.service.ManageService;
import com.myapp.service.MemberService;
import com.myapp.vo.LinkVO;
import com.myapp.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/manage")
@RequiredArgsConstructor
public class ManageController {

    private final BoardService boardService;

    private final MemberService memberService;

    private final ManageService manageService;

    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);


    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public String manage(Model model) {

        List<MemberVO> members = memberService.selectMember();
        LinkVO link = manageService.selectLink();
        model.addAttribute("members", members);
        model.addAttribute("link", link);
        return "manage";
    }

    @RequestMapping(value = "/update/community", method = RequestMethod.POST)
    @ResponseBody
    public void updateCommunity(@RequestParam("community") String community) {
        manageService.updateCommunity(community);
    }

    @RequestMapping(value = "/update/download", method = RequestMethod.POST)
    @ResponseBody
    public void updateDownload(@RequestParam("download") String download) {
        manageService.updateDownload(download);
    }

}
