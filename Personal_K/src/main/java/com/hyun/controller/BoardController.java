package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyun.service.BoardService;
import com.hyun.service.ReplyService;
import com.hyun.vo.BoardVO;
import com.hyun.vo.PageMaker;
import com.hyun.vo.ReplyVO;
import com.hyun.vo.SearchCriteria;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardService service;
	
	@Autowired
	private ReplyService replyservice;

	/* # 게시글 작성 화면 GET */
	@RequestMapping(value = "/board/writeView", method = RequestMethod.GET)
	public void writeView() throws Exception{
		logger.info("작성 화면");
	}
	
	/* # 게시글 작성 전송 POST */
	@RequestMapping(value = "/board/write", method = RequestMethod.POST)
	public String write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception{
		
		logger.info("게시글 작성 전송 POST");
		
		service.write(vo,mpRequest);
		
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
		
		List<ReplyVO> replyList = replyservice.readReply(vo.getBno());
		model.addAttribute("replyList", replyList);
		
		List<Map<String, Object>> fileList = service.selectFileList(vo.getBno());
		model.addAttribute("file", fileList);

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
	
	/* # 댓글 작성 */
	@RequestMapping(value = "/replyWrite", method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		
		logger.info("댓글 작성");
		
		replyservice.writeReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
	
	/* # 댓글 수정 GET */
	@RequestMapping(value = "/replyUpdateView", method = RequestMethod.GET)
	public String replyUpdateView(ReplyVO vo,SearchCriteria scri, Model model) throws Exception {
	
		logger.info("댓글 수정 번호 : " + vo.getRno());
		
		model.addAttribute("replyUpdate", replyservice.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);
		
		return "board/replyUpdateView";
	}
	
	/* # 댓글 수정 POST */
	@RequestMapping(value = "/replyUpdate", method = RequestMethod.POST)
	public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception{
		
		logger.info("댓글 수정");
		
		replyservice.updateReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
	
	/* # 댓글 삭제 GET */
	@RequestMapping(value = "/replyDeleteView", method = RequestMethod.GET)
	public String replyDeleteView(ReplyVO vo,SearchCriteria scri, Model model) throws Exception {
		
		logger.info("댓글 삭제 폼");
		
		model.addAttribute("replyDelete", replyservice.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);
		
		return "board/replyDeleteView";
	}
	
	/* # 댓글 삭제 POST */
	@RequestMapping(value = "replyDelete", method = RequestMethod.POST)
	public String replyDelete(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		logger.info("댓글 삭제");
		
		replyservice.deleteReply(vo);
		
		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView";
	}
	
}
