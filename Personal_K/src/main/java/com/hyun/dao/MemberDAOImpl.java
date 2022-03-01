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
	
	/* # 로그인 */
	@Override
	public MemberVO login(MemberVO vo) throws Exception {
		return sql.selectOne("memberMapper.login",vo);
	}
	
	/* # 회원정보 수정 */
	@Override
	public void memberUpdate(MemberVO vo) throws Exception {
		sql.update("memberMapper.memberUpdate",vo);
	}
	
	/* # 회원탈퇴 */
	@Override
	public void memberDelete(MemberVO vo) throws Exception {
		sql.delete("memberMapper.memberDelete",vo);
	}
	
	/* # 패스워드 체크 */
	@Override
	public int passChk(MemberVO vo) throws Exception {
		int result = sql.selectOne("memberMapper.passChk",vo);
		return result;
	}
	
}
