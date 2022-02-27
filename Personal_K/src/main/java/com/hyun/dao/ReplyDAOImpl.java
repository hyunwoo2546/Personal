package com.hyun.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyun.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO{

	@Autowired
	private SqlSession sqlSession;
	
	/* # 댓글 조회 */
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception {
		return sqlSession.selectList("replyMapper.readReply",bno);
	}
	
	/* # 댓글 작성 */
	@Override
	public void writeReply(ReplyVO vo) throws Exception {
		sqlSession.insert("replyMapper.writeReply",vo);
	}
	
	/* # 댓글 수정 */
	@Override
	public void updateReply(ReplyVO vo) throws Exception {
		sqlSession.update("replyMapper.updateReply",vo);
	}
	
	/* # 댓글 삭제 */
	@Override
	public void deleteReply(ReplyVO vo) throws Exception {
		sqlSession.delete("replyMapper.deleteReply",vo);
	}
	
	/* # 선택된 댓글 조회 */
	@Override
	public ReplyVO selectReply(int rno) throws Exception {
		return sqlSession.selectOne("replyMapper.selectReply",rno);
	}
	
}
