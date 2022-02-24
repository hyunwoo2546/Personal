package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyun.service.BoardService;
import com.hyun.vo.BoardVO;
import com.hyun.vo.PageMaker;
import com.hyun.vo.SearchCriteria;

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
	public String list(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		
		logger.info("게시글 목록 조회 GET");
		
		model.addAttribute("list", service.list(scri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));
		
		model.addAttribute("pageMaker", pageMaker);
		
		return "board/list";
	}
	
	/* # 특정 게시글 조회 */
	@RequestMapping(value = "/readView", method = RequestMethod.GET)
	public String read(BoardVO vo, Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		
		logger.info("특정 게시글 조회 GET");
		
		model.addAttribute("read",service.read(vo.getBno()));
		model.addAttribute("scri", scri);

		return "board/readView";
	}
	
	/* # 게시글 수정 폼 GET */
	@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	public String updateView(BoardVO vo,Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("게시글 수정 폼 GET");
		
		model.addAttribute("update",service.read(vo.getBno()));
		model.addAttribute("scri", scri);
		
		return "board/updateView";
	}
	
	/* # 게시글 수정 POST*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(BoardVO vo, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		logger.info("수정한 게시글 번호 : " + vo.getBno());
		
		service.update(vo);
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}
	
	/* # 게시글 삭제 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(BoardVO vo, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {

		logger.info("삭제된 게시글 번호 : " + vo.getBno());
		
		service.delete(vo.getBno());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/list";
	}
	
}
