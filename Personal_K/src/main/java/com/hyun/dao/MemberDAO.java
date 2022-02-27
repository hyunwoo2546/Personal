package com.hyun.dao;

import com.hyun.vo.MemberVO;

public interface MemberDAO {

	/* # 회원가입 */
	public void register(MemberVO vo) throws Exception;
	
}
