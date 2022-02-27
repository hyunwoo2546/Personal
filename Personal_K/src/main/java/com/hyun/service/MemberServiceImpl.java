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
}
