package com.hyun.service;

import com.hyun.vo.MemberVO;

public interface MemberService {
	
	/* # 회원가입 */
	public void register(MemberVO vo) throws Exception;
	
}
