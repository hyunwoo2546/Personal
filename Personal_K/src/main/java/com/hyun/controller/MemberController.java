package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyun.service.MemberService;
import com.hyun.vo.MemberVO;

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
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		
		log.info("회원가입 데이터 전달");
		
		service.register(vo);
		
		return null;
	}
	
}
