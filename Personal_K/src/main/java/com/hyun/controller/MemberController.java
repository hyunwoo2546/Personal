package com.hyun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hyun.service.MemberService;
import com.hyun.vo.MemberVO;


import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	MemberService service;
	
	@Autowired
	BCryptPasswordEncoder pwdenEncoder;
	
	/* # 아이디 중복 체크 */
	@ResponseBody
	@RequestMapping(value = "/idChk", method = RequestMethod.POST) 
	public int idChK(MemberVO vo) throws Exception {
		int result = service.idChk(vo);
		return result;
	}
	
	/* # 회원가입 GET*/
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() {
		log.info("회원가입 폼");
	}
	
	/* # 회원가입 POST */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		
		log.info("회원가입 데이터 전달");
		
		int result = service.idChk(vo);
		try {
			if(result == 1) {
				return "/member/register"; // 아이디가 존재한다면 다시 회원가입 창으로...
			} else if (result == 0) {
				
				/*- 스프링 시큐리티*/
				String inputPass = vo.getUserPass();
				String pwd = pwdenEncoder.encode(inputPass);
				vo.setUserPass(pwd);
				
				service.register(vo); // 회원가입 성공.
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return "redirect:/board/list";
	}
	
	/* # 로그인 GET */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		log.info("로그인 폼");
		
		return "member/login";
	}
	
	/* # 로그인 POST */
	@ResponseBody
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(MemberVO vo,HttpSession session, RedirectAttributes rttr) throws Exception {
		
		session.getAttribute("member");
		MemberVO login = service.login(vo);
		//boolean pwdMatch = pwdenEncoder.matches(vo.getUserPass(), login.getUserPass());
		
		boolean pwdMatch;
		if(login != null) {
		pwdMatch = pwdenEncoder.matches(vo.getUserPass(), login.getUserPass());
		} else {
		log.info("로그인 실패");
		pwdMatch = false;
		return "redirect:/member/login";
		}
		
		if (login != null && pwdMatch == true) {
			session.setAttribute("member", login);
		} else {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
		}
		
		return "redirect:/board/list";
	}
	
	/* # 로그아웃 GET */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception {
		
		session.invalidate();
		
		return "redirect:/board/list";
	}
	
	/* # 회원정보 수정 GET */
	@RequestMapping(value = "memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception {
		return "member/memberUpdateView";
	}
	
	/* # 회원정보 수정 POST*/
	@RequestMapping(value = "memberUpdate", method = RequestMethod.POST)
	public String registerUpdate(MemberVO vo,HttpSession session) throws Exception {
		
		service.memberUpdate(vo);
		
		session.invalidate();
		
		return "redirect:/board/list";
	}
	
	/* # 회원탈퇴 GET */
	@RequestMapping(value = "/memberDeleteView", method = RequestMethod.GET)
	public String memberDeleteView() throws Exception{
		return "member/memberDeleteView";
	}
	
	/* # 회원탈퇴 POST */
	@RequestMapping(value = "/memberDelete", method = RequestMethod.POST)
	public String memberDelete(MemberVO vo, HttpSession session,RedirectAttributes rttr) throws Exception{
		service.memberDelete(vo);
		session.invalidate();
		return "redirect:/board/list";
	}
	
	/* # 패스워드 체크 */
	@ResponseBody
	@RequestMapping(value = "/passChk", method = RequestMethod.POST)
	public boolean passChk(MemberVO vo) throws Exception {
		log.info("패스워드 체크");
		MemberVO login = service.login(vo);
		boolean pwdChk = pwdenEncoder.matches(vo.getUserPass(), login.getUserPass());
		return pwdChk;
	}
	
}
