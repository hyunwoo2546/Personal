package com.hyun.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyun.vo.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO{

	@Autowired
	SqlSession sql;
	
	/* # 회원가입 */
	@Override
	public void register(MemberVO vo) throws Exception {
		sql.insert("memberMapper.register",vo);
	}
	
}
