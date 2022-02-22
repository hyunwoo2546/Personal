package com.hyun.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyun.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	/* # 게시글 작성 */
	@Override
	public void write(BoardVO vo) throws Exception {
		
		sqlSession.insert("boardMapper.insert", vo);
		
	}
	
}
