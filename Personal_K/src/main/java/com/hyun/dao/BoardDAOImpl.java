package com.hyun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.hyun.vo.BoardVO;
import com.hyun.vo.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO{

	@Autowired
	private SqlSession sqlSession;
	
	/* # 게시글 작성 */
	@Override
	public void write(BoardVO vo, MultipartHttpServletRequest mpRequest) throws Exception {
		sqlSession.insert("boardMapper.insert", vo);
	}
	
	/* # 게시물 목록 조회 */
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("boardMapper.listPage",scri);
	}
	
	/* # 게시물 총 갯수 */
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("boardMapper.listCount",scri);
	}
	
	/* # 특정 게시글 조회 */
	@Override
	public BoardVO read(int bno) throws Exception {
		
		return sqlSession.selectOne("boardMapper.read",bno);
	}
	
	/* # 게시글 수정 */
	@Override
	public void update(BoardVO vo) throws Exception {
		sqlSession.update("boardMapper.update", vo);
	}
	
	/* # 게시글 삭제 */
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("boardMapper.delete", bno);
	}
	
	/* # 첨부파일 업로드 */
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("boardMapper.insertFile",map);
	}
	
}
