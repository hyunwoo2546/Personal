package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyun.service.MemberService;
import com.hyun.vo.MemberVO;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	/* # 회원가입 GET*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() {
		log.info("회원가입 폼");
	}
	
	/* # 회원가입 POST */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		
		log.info("회원가입 데이터 전달");
		
		service.register(vo);
		
		return null;
	}
	
	/* # 로그인 GET */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void login() {
		log.info("로그인 폼");
	}
	
	/* # 로그인 POST */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo,HttpServletRequest req, RedirectAttributes rttr) throws Exception {
		
		HttpSession session = req.getSession();
		MemberVO login = service.login(vo);
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addAttribute("msg", false);
		} else {
			session.setAttribute("member", login);
		}
		
		return "redirect:/board/list";
	}
	
	/* # 로그아웃 GET */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/board/list";
	}
	
}
