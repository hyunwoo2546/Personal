package com.hyun.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyun.dao.MemberDAO;
import com.hyun.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDAO dao;
	
	/* # 회원가입 */
	@Override
	public void register(MemberVO vo) throws Exception {
		dao.register(vo);
	}
	
	/* #로그인 */
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return dao.login(vo);
	}
	
	/* # 회원정보 수정 */
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		dao.memberUpdate(vo);
	}
	
	/* # 회원탈퇴 */
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		dao.memberDelete(vo);
	}
	
}
