package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	/* # 게시글 작성 화면 GET */
	@RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("작성 화면");
	}
	
	/* # 게시글 작성 전송 POST */
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(BoardVO vo) throws Exception{
		
		logger.info("게시글 작성 전송 POST");
		
		service.write(vo);
		
		return "redirect:/board/list";
	}	
	
	/* # 게시글 목록 조회 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model) throws Exception {
		
		logger.info("게시글 목록 조회 GET");
		
		model.addAttribute("list", service.list());
		
		return "board/list";
	}
	
	/* # 특정 게시글 조회 */
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO vo, Model model) throws Exception {
		
		logger.info("특정 게시글 조회 GET");
		
		model.addAttribute("read",service.read(vo.getBno()));

		return "board/readView";
	}
	
	/* # 게시글 수정 폼 GET */
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(BoardVO vo,Model model) throws Exception {
		logger.info("게시글 수정 폼 GET");
		
		model.addAttribute("update",service.read(vo.getBno()));
		
		return "board/updateView";
	}
	
	/* # 게시글 수정 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO vo) throws Exception {
		
		logger.info("수정한 게시글 번호 : " + vo.getBno());
		
		service.update(vo);
		
		return "redirect:/board/list";
	}
	
	/* # 게시글 삭제 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(BoardVO vo) throws Exception {

		logger.info("삭제된 게시글 번호 : " + vo.getBno());
		
		service.delete(vo.getBno());
		
		return "redirect:/board/list";
	}
	
}
