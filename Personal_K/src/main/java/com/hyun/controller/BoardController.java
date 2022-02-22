package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hyun.service.BoardService;
import com.hyun.vo.BoardVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;

	@RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("작성 화면");
	}
	
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(BoardVO vo) throws Exception{
		
		logger.info("write");
		
		service.write(vo);
		
		return "redirect:/";
	}	
	
}
